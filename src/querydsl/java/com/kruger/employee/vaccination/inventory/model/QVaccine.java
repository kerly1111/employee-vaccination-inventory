package com.kruger.employee.vaccination.inventory.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QVaccine is a Querydsl query type for Vaccine
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVaccine extends EntityPathBase<Vaccine> {

    private static final long serialVersionUID = -1941777104L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVaccine vaccine = new QVaccine("vaccine");

    public final QAuditFields _super;

    public final QEmployee employee;

    public final NumberPath<Long> employeId = createNumber("employeId", Long.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    //inherited
    public final BooleanPath isActive;

    public final NumberPath<Integer> numberDoses = createNumber("numberDoses", Integer.class);

    //inherited
    public final DateTimePath<java.sql.Timestamp> registrationDate;

    // inherited
    public final QUser registrationUser;

    //inherited
    public final NumberPath<Long> registrationUserId;

    public final StringPath type = createString("type");

    //inherited
    public final DateTimePath<java.sql.Timestamp> updateDate;

    // inherited
    public final QUser updateUser;

    //inherited
    public final NumberPath<Long> updateUserId;

    public final DateTimePath<java.util.Date> vaccinationDate = createDateTime("vaccinationDate", java.util.Date.class);

    public QVaccine(String variable) {
        this(Vaccine.class, forVariable(variable), INITS);
    }

    public QVaccine(Path<? extends Vaccine> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QVaccine(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QVaccine(PathMetadata metadata, PathInits inits) {
        this(Vaccine.class, metadata, inits);
    }

    public QVaccine(Class<? extends Vaccine> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QAuditFields(type, metadata, inits);
        this.employee = inits.isInitialized("employee") ? new QEmployee(forProperty("employee"), inits.get("employee")) : null;
        this.isActive = _super.isActive;
        this.registrationDate = _super.registrationDate;
        this.registrationUser = _super.registrationUser;
        this.registrationUserId = _super.registrationUserId;
        this.updateDate = _super.updateDate;
        this.updateUser = _super.updateUser;
        this.updateUserId = _super.updateUserId;
    }

}

