package com.kruger.employee.vaccination.inventory.repository.custom.impl;

import com.kruger.employee.vaccination.inventory.model.QRole;
import com.kruger.employee.vaccination.inventory.model.Role;
import com.kruger.employee.vaccination.inventory.repository.custom.CustomRolRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class CustomRoleRepositoryImpl implements CustomRolRepository {

    @PersistenceContext
    private EntityManager entityManager;
    private QRole qRole;
    private JPAQuery<Role> query;

    @Override
    public Optional<Role> findByName(String name) {
        this.initQuery();

        this.query.select(Projections.fields(Role.class,
                this.qRole.id,
                this.qRole.name));

        this.query.where(this.qRole.name.eq(name));

        return Optional.ofNullable(this.query.fetchFirst());
    }

    private void initQuery() {
        this.qRole = QRole.role;
        this.query = new JPAQuery<>(this.entityManager);
        this.query.from(this.qRole);
    }
}
