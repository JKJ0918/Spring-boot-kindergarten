package com.kinder.kindergarten.entity;

import jakarta.persistence.MappedSuperclass;

import java.time.LocalDate;

@MappedSuperclass
public abstract class RecordDateEntity {

    /*
     원아(학부모) 관련 테이블에서 공통으로 LocalDate 를 분리하였는데

     원아 출석 테이블 = 출석 날짜
     원아 건강 테이블 = 건강 날짜
     원아 성장 테이블 = 활동 날짜
     원아 반 배정 테이블 = 반 변경 날짜

     만 상위 클래스 정의 함
     */
    protected LocalDate recodeDate; // 공통 필드

    public LocalDate getDate() {
        return recodeDate;
    }


    public LocalDate setDate() {
         return recodeDate;
    }
}
