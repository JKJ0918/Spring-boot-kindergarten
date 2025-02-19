package com.kinder.kindergarten.DTO;

import com.kinder.kindergarten.constant.BoardType;
import com.kinder.kindergarten.entity.BoardEntity;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class BoardDTO {

  //기본키
  private String boardId;

  //제목
  private String boardTitle;

  //게시글 내용
  private String boardContents;

  //게시글 유형
  private BoardType boardType;

  //작성자
  private String boardWriter;

  private int views;

  private int likes;

  private LocalDateTime regiDate;

  private LocalDateTime modiDate;

  private List<BoardFileDTO> BoardFileList = new ArrayList<>();

  private List<Long> FileIds = new ArrayList<>();

}
