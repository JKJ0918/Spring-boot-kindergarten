package com.kinder.kindergarten.DTO;

import lombok.Data;

@Data
public class ScheduleDTO {

  //기본키
  private String schedule_id;

  //일정 제목
  private String schedule_title;

  //일정 내용
  private String schedule_content;

  //장소
  private String location;

  //시간
  private String schedule_time;
}
