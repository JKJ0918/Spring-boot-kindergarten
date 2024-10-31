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
@Table(name = "GrowthRecord")
public class GrowthRecord extends RecordDateEntity{

    // 원아의 성장 과정이나 일일 활동을 관리하는 엔티티

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long growth_id;

    @ManyToOne
    @JoinColumn(name = "children_id", nullable = false)
    private Children children;

/*    @Column(nullable = false)
    private LocalDate growth_date;  // 활동 날짜 */

    @Column(nullable = false)
    private String growth_activity;  // 활동 내용

    private String growth_notes;     // 성장 과정에 대한 추가 메모


    /*

    Hibernate:
    create table growth_record (
        growth_id bigint not null auto_increment,
        growth_activity varchar(255) not null,
        growth_date date not null,
        growth_notes varchar(255),
        children_id bigint not null,
        primary key (growth_id)
    ) engine=InnoDB
Hibernate:
    alter table if exists growth_record
       add constraint FKnh9gstykweriv1yp73f1hgdk8
       foreign key (children_id)
       references children (children_id)

     */

}
