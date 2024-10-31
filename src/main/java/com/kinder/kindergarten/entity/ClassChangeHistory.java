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
@Table(name = "class_change_history")
public class ClassChangeHistory extends RecordDateEntity {

    // 원아가 반 변경할 때 그 내역을 기록하는 엔티티

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cch_id;    // 변경 기록 ID

    @ManyToOne
    @JoinColumn(name = "children_id", nullable = false)
    private Children children;  // 원아 객체

    @ManyToOne
    @JoinColumn(name = "previous_class_id", referencedColumnName = "class_Room_id", nullable = false)
    private ClassRoom previousClass;    // 이전 반

    @ManyToOne
    @JoinColumn(name = "new_class_id", referencedColumnName = "class_Room_id", nullable = false)
    private ClassRoom newClass; // 새로 배정된 반

 /*   @JoinColumn(nullable = false)
    private LocalDate cch_changeDate;  // 반 변경 날짜 */

    /*

    Hibernate:
    create table class_change_history (
        cch_id bigint not null auto_increment,
        cch_change_date date,
        children_id bigint not null,
        new_class_id bigint not null,
        previous_class_id bigint not null,
        primary key (cch_id)
    ) engine=InnoDB
Hibernate:
    alter table if exists class_change_history
       add constraint FK2i94p3j490ccx22e5mmvshgjg
       foreign key (children_id)
       references children (children_id)
Hibernate:
    alter table if exists class_change_history
       add constraint FKpxpkq4rjxp4487n0m4gib7xp9
       foreign key (new_class_id)
       references class_room (class_room_id)
Hibernate:
    alter table if exists class_change_history
       add constraint FKl94bg0xoalu59rwl87c72buyb
       foreign key (previous_class_id)
       references class_room (class_room_id)


     */

}

