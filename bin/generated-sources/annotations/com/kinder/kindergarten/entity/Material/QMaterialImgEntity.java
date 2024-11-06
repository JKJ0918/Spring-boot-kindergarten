package com.kinder.kindergarten.entity.Material;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMaterialImgEntity is a Querydsl query type for MaterialImgEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMaterialImgEntity extends EntityPathBase<MaterialImgEntity> {

    private static final long serialVersionUID = 90651259L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMaterialImgEntity materialImgEntity = new QMaterialImgEntity("materialImgEntity");

    public final com.kinder.kindergarten.entity.QTimeEntity _super = new com.kinder.kindergarten.entity.QTimeEntity(this);

    public final StringPath id = createString("id");

    public final StringPath material_imgName = createString("material_imgName");

    public final StringPath material_orgImgName = createString("material_orgImgName");

    public final StringPath material_repimgYn = createString("material_repimgYn");

    public final QMaterialEntity materialEntity;

    public final StringPath materialImgUrl = createString("materialImgUrl");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modiDate = _super.modiDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regiDate = _super.regiDate;

    public QMaterialImgEntity(String variable) {
        this(MaterialImgEntity.class, forVariable(variable), INITS);
    }

    public QMaterialImgEntity(Path<? extends MaterialImgEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMaterialImgEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMaterialImgEntity(PathMetadata metadata, PathInits inits) {
        this(MaterialImgEntity.class, metadata, inits);
    }

    public QMaterialImgEntity(Class<? extends MaterialImgEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.materialEntity = inits.isInitialized("materialEntity") ? new QMaterialEntity(forProperty("materialEntity")) : null;
    }

}

