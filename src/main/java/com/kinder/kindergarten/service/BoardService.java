package com.kinder.kindergarten.service;


import com.github.f4b6a3.ulid.Ulid;
import com.github.f4b6a3.ulid.UlidCreator;
import com.google.common.cache.LoadingCache;
import com.kinder.kindergarten.DTO.BoardDTO;
import com.kinder.kindergarten.DTO.BoardFileDTO;
import com.kinder.kindergarten.DTO.BoardFormDTO;
import com.kinder.kindergarten.constant.BoardType;
import com.kinder.kindergarten.entity.BoardEntity;
import com.kinder.kindergarten.entity.BoardFileEntity;
import com.kinder.kindergarten.repository.BoardFileRepository;
import com.kinder.kindergarten.repository.BoardRepository;
import com.kinder.kindergarten.repository.QueryDSL;
import com.kinder.kindergarten.util.Hangul;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class BoardService {

  private final BoardRepository boardRepository;

  private final BoardFileRepository boardFileRepository;

  private final QueryDSL queryDSL;

  private final ModelMapper modelMapper;

  private final FileService fileService;

  private final LoadingCache<String, Page<BoardDTO>> searchCache;

  //application.properties 에 있는 값
  @Value("${uploadPath1}")
  private String uploadPath;

  public Page<BoardDTO> getCommonBoards(Pageable pageable) {
    log.info("페이지 불러오기 - BoardService.getCommonBoards()실행. pageable 정보 : " +pageable);

    //페이징 처리
    Page<BoardEntity> boardPage = boardRepository.findByBoardType(BoardType.COMMON, pageable);

    return boardPage.map(boardEntity -> modelMapper.map(boardEntity, BoardDTO.class));
  }

  @Transactional
  public void saveBoard(BoardFormDTO boardFormDTO) throws Exception{
    Ulid ulid = UlidCreator.getUlid();
    String id = ulid.toString();
    boardFormDTO.setBoardId(id); // UUID대신 사용할 ULID
    BoardEntity board = boardFormDTO.wirteBoard();
    boardRepository.save(board);
    log.info("게시글+파일 저장 - BoardService.saveBoard() 실행" + boardFormDTO);
  }

  public void saveBoardWithFile(BoardFormDTO boardFormDTO, List<MultipartFile> boardFileList) throws Exception{
    log.info("게시글+파일 저장 - BoardService.saveBoardWithFile() 실행" + boardFormDTO);

    Ulid ulid = UlidCreator.getUlid();
    String id = ulid.toString();
    boardFormDTO.setBoardId(id); // UUID대신 사용할 ULID
    BoardEntity board = boardFormDTO.wirteBoard();

    //게시물 정보 먼저 저장
    boardRepository.save(board);

    if (boardFileList != null && !boardFileList.get(0).isEmpty()) {
      boolean isFirstImage = true;
      String mainFileName = null;

      for (MultipartFile file : boardFileList) {
        BoardFileEntity boardFile = new BoardFileEntity();

        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

        // FileService를 사용하여 파일 저장
        String savedFileName = fileService.uploadFile(uploadPath, originalFilename, file.getBytes());
        String filePath = fileService.getFullPath(savedFileName);

        // 이미지 파일인 경우 첫 번째 파일을 main_file로 지정
        if (isFirstImage && isImageFile(extension)) {
          mainFileName = savedFileName;
          isFirstImage = false;
        }

        // BoardFileEntity 설정
        boardFile.setOriginalName(originalFilename);
        boardFile.setModifiedName(savedFileName);
        boardFile.setFilePath(filePath);
        boardFile.setMainFile(mainFileName != null ? mainFileName : ""); // 메인 파일 이름 설정

        // 새로운 파일 ID 생성
        Ulid fileUlid = UlidCreator.getUlid();
        boardFile.setFileId(fileUlid.toString());

        // boardEntity 설정
        boardFile.setBoardEntity(board);

        //파일 정보 저장
        boardFileRepository.save(boardFile);
      }
    }

    log.info("게시글 저장 - BoardService.saveBoard : "+ boardFormDTO);
  }

  //이미지 파일인지 확인하는 method
  private boolean isImageFile(String extension) {
    return Arrays.asList(".jpg", ".jpeg", ".png", ".gif", ".bmp")
            .contains(extension.toLowerCase());
  }

  //페이지 상세보기 board_id로 찾아서 ModelMapper사용(entity -> DTO 로)
  @Transactional(readOnly = true)
  public BoardDTO getBoard(String board_id){
    Optional<BoardDTO> optionalBoardDTO = boardRepository.findById(board_id).map(boardEntity -> modelMapper.map(boardEntity,BoardDTO.class));
    BoardDTO boardDTO = optionalBoardDTO.get();

    List<BoardFileEntity> fileEntities = boardFileRepository.findByBoardEntity_BoardId(board_id);
    log.info("파일정보1 : " + fileEntities);
    if (!fileEntities.isEmpty()) {
      List<BoardFileDTO> fileDTOs = fileEntities.stream()
              .map(fileEntity -> {
                BoardFileDTO fileDTO = new BoardFileDTO();
                fileDTO.setFileId(fileEntity.getFileId());
                fileDTO.setOrignalName(fileEntity.getOriginalName());
                fileDTO.setModifiedName(fileEntity.getModifiedName());
                fileDTO.setFilePath(fileEntity.getFilePath());
                fileDTO.setMainFile(fileEntity.getMainFile());
                fileDTO.setBoardId(board_id);
                return fileDTO;
              })
              .collect(Collectors.toList());

      boardDTO.setBoardFileList(fileDTOs);
      log.info("파일정보 : " + fileDTOs);
    }

    return boardDTO;
  }

  //첨부파일이 있는경우 불러오기(다운로드용)
  public BoardFileDTO getFile(String fileId) {
    BoardFileEntity fileEntity = boardFileRepository.findById(fileId)
            .orElseThrow(() -> new RuntimeException("파일을 찾을 수 없습니다."));

    BoardFileDTO fileDTO = new BoardFileDTO();
    fileDTO.setFileId(fileEntity.getFileId());
    fileDTO.setOrignalName(fileEntity.getOriginalName());
    fileDTO.setFilePath(fileEntity.getFilePath());

    return fileDTO;
  }

  public String uploadSummernoteImage(MultipartFile file) throws Exception {
    String originalFileName = file.getOriginalFilename();
    String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
    String savedFileName = UUID.randomUUID().toString() + extension;

    String uploadDir = uploadPath + "/summernote/";
    File uploadPath = new File(uploadDir);

    if (!uploadPath.exists()) {
      uploadPath.mkdirs();
    }

    String savePath = uploadDir + savedFileName;
    file.transferTo(new File(savePath));

    return "/images/summernote/" + savedFileName;
  }

  @Transactional
  public void updateBoard(String boardId, BoardFormDTO boardFormDTO) {
    BoardEntity board = boardRepository.findById(boardId)
            .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

    board.setBoardTitle(boardFormDTO.getBoardTitle());
    board.setBoardContents(boardFormDTO.getBoardContents());
    board.setBoardType(boardFormDTO.getBoardType());
  }

  @Transactional
  public void updateBoardWithFile(String boardId, BoardFormDTO boardFormDTO,
                                  List<MultipartFile> boardFileList) throws Exception {
    // 기존 게시글 조회
    BoardEntity board = boardRepository.findById(boardId)
            .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

    // 기존 파일 삭제
    List<BoardFileEntity> existingFiles = boardFileRepository.findByBoardEntity(board);
    for (BoardFileEntity file : existingFiles) {
      fileService.deleteFile(file.getFilePath());
      boardFileRepository.delete(file);
    }

    // 새로운 파일 업로드
    if (boardFileList != null && !boardFileList.get(0).isEmpty()) {
      boolean isFirstImage = true;
      String mainFileName = null;

      for (MultipartFile file : boardFileList) {
        BoardFileEntity boardFile = new BoardFileEntity();
        String originalFilename = file.getOriginalFilename();

        // FileService를 사용하여 파일 저장
        String savedFileName = fileService.uploadFile(uploadPath, originalFilename, file.getBytes());
        String filePath = fileService.getFullPath(savedFileName);

        // 첫 번째 이미지 파일을 메인 파일로 설정
        if (isFirstImage) {
          mainFileName = savedFileName;
          isFirstImage = false;
        }

        // BoardFileEntity 설정
        boardFile.setFileId(UUID.randomUUID().toString());
        boardFile.setOriginalName(originalFilename);
        boardFile.setModifiedName(savedFileName);
        boardFile.setFilePath(filePath);
        boardFile.setMainFile(mainFileName != null ? mainFileName : "");
        boardFile.setBoardEntity(board);

        boardFileRepository.save(boardFile);
      }
    }

    // 게시글 정보 업데이트
    board.setBoardTitle(boardFormDTO.getBoardTitle());
    board.setBoardContents(boardFormDTO.getBoardContents());
    board.setBoardType(boardFormDTO.getBoardType());
  }

//게시글 삭제
  public void deleteBoard(String boardId){
    boardRepository.deleteByBoardId(boardId);
  }
//게시글 검색
public Page<BoardDTO> searchBoards(String keyword, Pageable pageable) {
  try {
    // 캐시 키 생성 (검색어와 페이지 정보 조합)
    String cacheKey = keyword + "_" + pageable.getPageNumber();

    // 캐시에서 결과 조회 시도
    return searchCache.get(cacheKey, () -> {
      // 초성 검색인지 확인
      boolean isChosung = keyword.matches("^[ㄱ-ㅎ]+$");

      if (isChosung) {
        // 초성 검색 로직
        List<BoardEntity> allBoards = boardRepository.findAll();
        List<BoardDTO> matchedBoards = allBoards.stream()
                .filter(entity ->
                        Hangul.matchesChosung(entity.getBoardTitle(), keyword) ||
                                Hangul.matchesChosung(entity.getBoardContents(), keyword) ||
                                Hangul.matchesChosung(entity.getBoardWriter(), keyword))
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        // List를 Page로 변환
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), matchedBoards.size());

        return new PageImpl<>(
                matchedBoards.subList(start, end),
                pageable,
                matchedBoards.size()
        );
      } else {
        // 일반 검색 로직
        return boardRepository
                .findByBoardTitleContainingOrBoardContentsContainingOrBoardWriterContaining(
                        keyword, keyword, keyword, pageable)
                .map(this::convertToDTO);
      }
    });
  } catch (Exception e) {
    throw new RuntimeException("검색 중 오류가 발생했습니다.", e);
  }
}

  private BoardDTO convertToDTO(BoardEntity entity) {
    BoardDTO dto = new BoardDTO();
    dto.setBoardId(entity.getBoardId());
    dto.setBoardTitle(entity.getBoardTitle());
    dto.setBoardContents(entity.getBoardContents());
    dto.setBoardType(entity.getBoardType());
    dto.setBoardWriter(entity.getBoardWriter());
    dto.setViews(entity.getViews());
    dto.setLikes(entity.getLikes());
    dto.setRegiDate(entity.getRegiDate());
    dto.setModiDate(entity.getModiDate());
    return dto;
  }

}//class end
