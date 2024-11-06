package com.kinder.kindergarten.repository.Material;

import com.kinder.kindergarten.entity.Material.MaterialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface MaterialRepository extends JpaRepository<MaterialEntity, String>,
        QuerydslPredicateExecutor<MaterialEntity>, MaterialRepositoryCustom {

    List<MaterialEntity> findByIdOrderByIdAsc(String id);
}


