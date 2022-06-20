package com.kruger.employee.vaccination.inventory.repository.custom.impl;

import com.kruger.employee.vaccination.inventory.model.Employee;
import com.kruger.employee.vaccination.inventory.model.QUser;
import com.kruger.employee.vaccination.inventory.model.User;
import com.kruger.employee.vaccination.inventory.repository.custom.CustomUserRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class CustomUserRespositoryImpl implements CustomUserRepository {

    @PersistenceContext
    private EntityManager entityManager;
    private QUser qUser;
    private JPAQuery<User> query;

    @Override
    public Optional<User> findByUsername(String username){
        this.initQuery();

        this.query.select(Projections.fields(User.class,
                this.qUser.id,
                this.qUser.username,
                this.qUser.role
        ));

        this.query.where(this.qUser.username.eq(username).and(this.qUser.isActive.eq(Boolean.TRUE)));

        return Optional.ofNullable(this.query.fetchFirst());
    }

    private void initQuery() {
        this.qUser = QUser.user;
        this.query = new JPAQuery<>(this.entityManager);
        this.query.from(this.qUser);
    }
}
