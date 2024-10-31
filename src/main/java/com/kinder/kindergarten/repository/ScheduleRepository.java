package com.kinder.kindergarten.repository;

import com.kinder.kindergarten.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity,String> {

  //스케줄 가져오기
  List<ScheduleEntity> findAllBy();

  List<ScheduleEntity> findByScheduleId(String ScheduleId);
}
