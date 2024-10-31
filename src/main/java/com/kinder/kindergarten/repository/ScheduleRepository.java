package com.kinder.kindergarten.repository;

import com.kinder.kindergarten.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity,String> {
}
