package com.kinder.kindergarten.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity // 테이블 담당한다.
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Table(name = "Children")
public class Children {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long children_id;   // 원아의 고유ID(PK)

    private String children_name;   // 원아 이름

    private LocalDate birthDate;// 생년월일

    private String parent_name;// 학부모 성함

    private String parent_phone;// 학부모 연락처

    private String children_emergencyPhone;// 긴급 연락처

    private String children_allergies;// 알레르기 정보

    private String children_medicalHistory;// 병력 정보

    private String children_notes;  // 기타 사항


    @ManyToOne
    @JoinColumn(name = "class_room_id")
    private ClassRoom assignedClass; // 반 배정

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Parent parent; // 부모 정보 참조

    /*
    Hibernate:
    create table children (
        children_id bigint not null auto_increment,
        birth_date date,
        children_allergies varchar(255),
        children_emergency_phone varchar(255),
        children_medical_history varchar(255),
        children_name varchar(255),
        parent_name varchar(255),
        parent_phone varchar(255),
        classroom_id bigint,
        parent_id bigint,
        primary key (children_id)
    ) engine=InnoDB


    Hibernate:
    alter table if exists children
       add column class_room_id bigint
Hibernate:
    alter table if exists children
       add constraint FKd0c9woqafnv8awh2ws30n5kly
       foreign key (class_room_id)
       references class_room (class_room_id)

     */
}
