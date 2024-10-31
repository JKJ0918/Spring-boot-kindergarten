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
@Table(name = "HealthRecord")
public class HealthRecord extends RecordDateEntity{

    // 원아의 건강 정보를 기록하는 테이블

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long health_id; // 건강 기록 ID

    @ManyToOne
    @JoinColumn(name = "children_id", nullable = false)
    private Children children;  // 유아 객체

 /*   @Column(nullable = false)
    private LocalDate health_date;  // 기록 날짜    */

    private Double health_temperature;  // 체온

    private String health_mealStatus;  // 식사 상태

    private String health_sleepStatus;  // 수면 상태


    /*

    Hibernate:
    create table health_record (
        health_id bigint not null auto_increment,
        health_date date not null,
        health_meal_status varchar(255),
        health_sleep_status varchar(255),
        health_temperature float(53),
        children_id bigint not null,
        primary key (health_id)
    ) engine=InnoDB
Hibernate:
    alter table if exists health_record
       add constraint FKpalm39r16l7almohxufmw3x4g
       foreign key (children_id)
       references children (children_id)

     */
}
