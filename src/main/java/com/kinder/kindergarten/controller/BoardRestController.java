package com.kinder.kindergarten.controller;

import com.kinder.kindergarten.DTO.BoardDTO;
import com.kinder.kindergarten.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/board")
public class BoardRestController {

  private final BoardService boardService;

  @GetMapping("/sort")
  public ResponseEntity<Page<BoardDTO>> getSortedBoards(
          @RequestParam(defaultValue = "0") int page,
          @RequestParam String sortBy) {

    Sort sort;
    switch (sortBy) {
      case "regiDate":
        sort = Sort.by(Sort.Direction.DESC, "regiDate");
        break;
      case "views":
        sort = Sort.by(Sort.Direction.DESC, "views");
        break;
      case "likes":
        sort = Sort.by(Sort.Direction.DESC, "likes");
        break;
      default:
        sort = Sort.by(Sort.Direction.DESC, "regiDate");
    }

    Pageable pageable = PageRequest.of(page, 10, sort);
    Page<BoardDTO> boards = boardService.getCommonBoards(pageable);

    return ResponseEntity.ok(boards);
  }

  @PostMapping("/uploadImage")
  public ResponseEntity<Map<String, String>> uploadImage(@RequestParam("file") MultipartFile file) {
    try {
      String imageUrl = boardService.uploadSummernoteImage(file);
      Map<String, String> response = new HashMap<>();
      response.put("url", imageUrl);
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }



}
