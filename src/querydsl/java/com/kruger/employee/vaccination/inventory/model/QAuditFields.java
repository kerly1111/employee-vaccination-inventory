package com.kruger.employee.vaccination.inventory.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAuditFields is a Querydsl query type for AuditFields
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QAuditFields extends EntityPathBase<AuditFields> {

    private static final long serialVersionUID = 1006961263L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAuditFields auditFields = new QAuditFields("auditFields");

    public final BooleanPath isActive = createBoolean("isActive");

    public final DateTimePath<java.sql.Timestamp> registrationDate = createDateTime("registrationDate", java.sql.Timestamp.class);

    public final QUser registrationUser;

    public final NumberPath<Long> registrationUserId = createNumber("registrationUserId", Long.class);

    public final DateTimePath<java.sql.Timestamp> updateDate = createDateTime("updateDate", java.sql.Timestamp.class);

    public final QUser updateUser;

    public final NumberPath<Long> updateUserId = createNumber("updateUserId", Long.class);

    public QAuditFields(String variable) {
        this(AuditFields.class, forVariable(variable), INITS);
    }

    public QAuditFields(Path<? extends AuditFields> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAuditFields(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAuditFields(PathMetadata metadata, PathInits inits) {
        this(AuditFields.class, metadata, inits);
    }

    public QAuditFields(Class<? extends AuditFields> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.registrationUser = inits.isInitialized("registrationUser") ? new QUser(forProperty("registrationUser"), inits.get("registrationUser")) : null;
        this.updateUser = inits.isInitialized("updateUser") ? new QUser(forProperty("updateUser"), inits.get("updateUser")) : null;
    }

}

