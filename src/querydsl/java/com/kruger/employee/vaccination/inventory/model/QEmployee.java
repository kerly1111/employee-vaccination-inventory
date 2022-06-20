package com.kruger.employee.vaccination.inventory.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEmployee is a Querydsl query type for Employee
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEmployee extends EntityPathBase<Employee> {

    private static final long serialVersionUID = -1482730349L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEmployee employee = new QEmployee("employee");

    public final QAuditFields _super;

    public final StringPath address = createString("address");

    public final DateTimePath<java.util.Date> dateBirth = createDateTime("dateBirth", java.util.Date.class);

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath identificationNumber = createString("identificationNumber");

    //inherited
    public final BooleanPath isActive;

    public final StringPath lastName = createString("lastName");

    public final StringPath name = createString("name");

    public final StringPath phone = createString("phone");

    //inherited
    public final DateTimePath<java.sql.Timestamp> registrationDate;

    // inherited
    public final QUser registrationUser;

    //inherited
    public final NumberPath<Long> registrationUserId;

    //inherited
    public final DateTimePath<java.sql.Timestamp> updateDate;

    // inherited
    public final QUser updateUser;

    //inherited
    public final NumberPath<Long> updateUserId;

    public final QUser user;

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public final BooleanPath vaccinationStatus = createBoolean("vaccinationStatus");

    public final QVaccine vaccine;

    public QEmployee(String variable) {
        this(Employee.class, forVariable(variable), INITS);
    }

    public QEmployee(Path<? extends Employee> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEmployee(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEmployee(PathMetadata metadata, PathInits inits) {
        this(Employee.class, metadata, inits);
    }

    public QEmployee(Class<? extends Employee> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QAuditFields(type, metadata, inits);
        this.isActive = _super.isActive;
        this.registrationDate = _super.registrationDate;
        this.registrationUser = _super.registrationUser;
        this.registrationUserId = _super.registrationUserId;
        this.updateDate = _super.updateDate;
        this.updateUser = _super.updateUser;
        this.updateUserId = _super.updateUserId;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
        this.vaccine = inits.isInitialized("vaccine") ? new QVaccine(forProperty("vaccine"), inits.get("vaccine")) : null;
    }

}

