package com.kinder.kindergarten.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity // 테이블 담당한다.
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Table(name = "class_room")
public class ClassRoom {

    // 해당 반의 정보와 담당 교사를 관리하는 엔티티

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long class_Room_id;  // 반 ID

    @Column(nullable = false)
    private String class_Room_name;  // 반 이름

    @Column(nullable = false)
    private String employee_name;// 담당 교사 이름

    private String class_Room_description; // 반 설명 및 비고

    @OneToMany(mappedBy = "assignedClass")
    private List<Children> children;  // 반에 배정된 원아들

    /*

    Hibernate:
    create table class_room (
        class_room_id bigint not null auto_increment,
        class_room_description varchar(255),
        class_room_name varchar(255) not null,
        employee_name varchar(255) not null,
        primary key (class_room_id)
    ) engine=InnoDB

     */




}
