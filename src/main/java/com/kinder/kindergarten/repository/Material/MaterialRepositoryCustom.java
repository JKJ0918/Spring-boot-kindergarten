package com.kinder.kindergarten.repository.Material;

import com.kinder.kindergarten.DTO.Material.MaterialSearchDTO;
import com.kinder.kindergarten.entity.Material.MaterialEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MaterialRepositoryCustom {

    Page<MaterialEntity> getMaterialPage(MaterialSearchDTO materialSearchDTO, Pageable pageable);

}
