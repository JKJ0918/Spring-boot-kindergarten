package com.kinder.kindergarten.repository;

import com.kinder.kindergarten.entity.MaterialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface MaterialRepository extends JpaRepository<MaterialEntity, Long>,
        QuerydslPredicateExecutor<MaterialEntity>, MaterialRepositoryCustom {

    List<MaterialEntity> findByIdOrderByIdAsc(Long id);
}


