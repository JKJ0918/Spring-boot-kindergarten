package com.kinder.kindergarten.repository.Material;

import com.kinder.kindergarten.entity.Material.MaterialImgEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterialImgRepository extends JpaRepository<MaterialImgEntity, String> {

    List<MaterialImgEntity> findByIdOrderByIdAsc(String id);
    //List<MaterialImgEntity> findById(Long id);

    List<MaterialImgEntity> findByMaterialEntityId(String id);
}