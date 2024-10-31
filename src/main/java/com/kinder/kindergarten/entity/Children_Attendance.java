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
@Table(name = "Children_Attendance")
public class Children_Attendance extends RecordDateEntity{

    // 원아의 일일 출석 기록을 관리하는 엔티티

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cdAttend_id;

    @ManyToOne
    @JoinColumn(name = "children_id", nullable = false)
    private Children children;

   /* @Column(nullable = false)
    private LocalDate cdAttend_date;  // 출석 날짜 */

    @Column(nullable = false)
    private String cdAttend_status;   // 출석 상태 (출석, 지각, 결석 등)

    /*

    Hibernate:
    create table children_attendance (
        cd_attend_id bigint not null auto_increment,
        cd_attend_date date not null,
        cd_attend_status varchar(255) not null,
        children_id bigint not null,
        primary key (cd_attend_id)
    ) engine=InnoDB
Hibernate:
    alter table if exists children_attendance
       add constraint FKiqfgv236l3dgrm1llmu08outw
       foreign key (children_id)
       references children (children_id)

     */

}
