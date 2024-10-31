package com.kinder.kindergarten.repository;

import com.kinder.kindergarten.DTO.MaterialSearchDTO;
import com.kinder.kindergarten.entity.MaterialEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MaterialRepositoryCustom {

    Page<MaterialEntity> getMaterialPage(MaterialSearchDTO materialSearchDTO, Pageable pageable);

}
