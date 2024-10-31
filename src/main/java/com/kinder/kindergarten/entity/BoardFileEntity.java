package com.kinder.kindergarten.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Time;

@Entity
@Table(name = "board_file")
@Getter
@Setter
@ToString
public class BoardFileEntity extends TimeEntity {

  @Id
  @Column(name = "file_id")
  private String fileId;

  @Column(nullable = false)
  private String originalName;

  @Column(nullable = false)
  private String modifiedName;

  @Column(nullable = false)
  private String filePath;

  @Column(nullable = false)
  private String mainFile;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="board_id", referencedColumnName = "board_id")
  private BoardEntity boardEntity;

}
