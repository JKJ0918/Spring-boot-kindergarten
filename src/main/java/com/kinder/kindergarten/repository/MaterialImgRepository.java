package com.kinder.kindergarten.repository;

import com.kinder.kindergarten.entity.MaterialImgEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterialImgRepository extends JpaRepository<MaterialImgEntity, Long> {

    List<MaterialImgEntity> findByIdOrderByIdAsc(Long id);

    List<MaterialImgEntity> findByMaterialEntityId(Long id);
}