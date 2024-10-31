package com.kinder.kindergarten.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name="schedule")
@Getter @Setter
@ToString
public class ScheduleEntity {

  @Id
  @Column(name="schedule_id")
  @GeneratedValue(strategy = GenerationType.UUID)
  private String schedule_id;

  @Column(nullable = false)
  private String schedule_title;

  @Column(nullable = false)
  private String schedule_content;

  @Column(nullable = false)
  private String location;

  @Column
  private LocalDateTime schedule_time;
}
