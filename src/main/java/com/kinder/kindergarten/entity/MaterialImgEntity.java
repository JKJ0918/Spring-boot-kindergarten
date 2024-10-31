package com.kinder.kindergarten.entity;

import com.kinder.kindergarten.DTO.MaterialFormDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Table(name="material_imgg")
@Getter
@Setter
public class MaterialImgEntity extends TimeEntity{

    @Id
    @Column(name="material_img_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String material_imgName; // 이미지 파일명

    private String material_orgImgName; // 원본 이미지 파일명

    private String material_ImgUrl; // 이미지 조회 경로

    private String material_repimgYn; // 대표 이미지 여부


    @OneToOne
    @JoinColumn(name = "material_id") // 참조를 명확히 지정
    private MaterialEntity materialEntity;

    public void updateMaterialImg(String material_orgImgName, String material_imgName, String material_ImgUrl) {
        this.material_orgImgName = material_orgImgName;
        this.material_imgName = material_imgName;
        this.material_ImgUrl = material_ImgUrl;
    }




}
