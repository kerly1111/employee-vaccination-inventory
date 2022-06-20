package com.kruger.employee.vaccination.inventory.repository.custom.impl;

import com.kruger.employee.vaccination.inventory.model.Employee;
import com.kruger.employee.vaccination.inventory.model.QEmployee;
import com.kruger.employee.vaccination.inventory.model.QUser;
import com.kruger.employee.vaccination.inventory.model.QVaccine;
import com.kruger.employee.vaccination.inventory.model.User;
import com.kruger.employee.vaccination.inventory.model.Vaccine;
import com.kruger.employee.vaccination.inventory.repository.custom.CustomEmployeeRepository;
import com.kruger.employee.vaccination.inventory.vo.request.EmployeeRequestVO;
import com.kruger.employee.vaccination.inventory.vo.request.FilterEmployeeRequestVO;
import com.kruger.employee.vaccination.inventory.vo.request.UpdateEmployeeRequestVO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomEmployeeRepositoryImpl implements CustomEmployeeRepository {

    @PersistenceContext
    private EntityManager entityManager;
    private QEmployee qEmployee;
    private JPAQuery<Employee> query;

    private JPAQueryFactory queryUpdate;

    @Override
    public void editEmployee(Long id, EmployeeRequestVO request) {
        this.initQuery(Boolean.TRUE);

        this.queryUpdate
                .update(this.qEmployee)
                .set(this.qEmployee.identificationNumber, request.getIdentificationNumber())
                .set(this.qEmployee.name, request.getName())
                .set(this.qEmployee.lastName, request.getLastName())
                .set(this.qEmployee.email, request.getEmail())
                .set(this.qEmployee.updateUserId, request.getUpdateUser())
                .set(this.qEmployee.updateDate, new Timestamp(System.currentTimeMillis()))
                .where(this.qEmployee.id.eq(id))
                .execute();
    }

    @Override
    public Optional<Employee> findByIdentification(String identificationNumber) {
        this.initQuery(Boolean.FALSE);

        this.query.select(Projections.fields(Employee.class,
                this.qEmployee.id
        ));

        this.query.where(this.qEmployee.identificationNumber.eq(identificationNumber));

        return Optional.ofNullable(this.query.fetchFirst());
    }

    @Override
    public List<Employee> allEmployee() {
        this.initQuery(Boolean.FALSE);

        QVaccine qVaccine = QVaccine.vaccine;

        this.query.select(Projections.fields(Employee.class,
                qEmployee.id,
                qEmployee.identificationNumber,
                qEmployee.name,
                qEmployee.lastName,
                qEmployee.email,
                qEmployee.address,
                qEmployee.dateBirth,
                qEmployee.phone,
                qEmployee.vaccinationStatus,
                Projections.fields(Vaccine.class,
                        this.qEmployee.vaccine.id,
                        this.qEmployee.vaccine.type,
                        this.qEmployee.vaccine.vaccinationDate,
                        this.qEmployee.vaccine.numberDoses
                ).as(this.qEmployee.vaccine.getMetadata().getName())
                ));

        this.query.leftJoin(this.qEmployee.vaccine, qVaccine);

        this.query.where(this.qEmployee.isActive.eq(Boolean.TRUE));

        return this.query.fetch();
    }

    @Override
    public void updateEmployee(Long id, UpdateEmployeeRequestVO request) {
        this.initQuery(Boolean.TRUE);

        this.queryUpdate
                .update(this.qEmployee)
                .set(this.qEmployee.dateBirth, request.getDateBirth())
                .set(this.qEmployee.address, request.getAddress())
                .set(this.qEmployee.phone, request.getPhone())
                .set(this.qEmployee.vaccinationStatus, request.getVaccinationStatus())
                .set(this.qEmployee.updateUserId, request.getUpdateUser())
                .set(this.qEmployee.updateDate, new Timestamp(System.currentTimeMillis()))
                .where(this.qEmployee.id.eq(id))
                .execute();
    }

    @Override
    public Optional<Employee> findByUsername(String username) {
        this.initQuery(Boolean.FALSE);

        QUser qUser = QUser.user;
        QVaccine qVaccine = QVaccine.vaccine;

        this.query.select(Projections.fields(Employee.class,
                this.qEmployee.id,
                this.qEmployee.userId,
                Projections.fields(User.class,
                        this.qEmployee.user.username
                ).as(this.qEmployee.user.getMetadata().getName()),
                Projections.fields(Vaccine.class,
                        this.qEmployee.vaccine.id,
                        this.qEmployee.vaccine.type,
                        this.qEmployee.vaccine.vaccinationDate,
                        this.qEmployee.vaccine.numberDoses
                ).as(this.qEmployee.vaccine.getMetadata().getName())

        ));

        this.query.leftJoin(this.qEmployee.user, qUser);
        this.query.leftJoin(this.qEmployee.vaccine, qVaccine);

        this.query.where(this.qEmployee.user.username.eq(username).and(this.qEmployee.isActive.eq(Boolean.TRUE)));

        return Optional.ofNullable(this.query.fetchFirst());
    }

    @Override
    public Optional<Employee> viewMyData(String username) {
        this.initQuery(Boolean.FALSE);

        QUser qUser = QUser.user;
        QVaccine qVaccine = QVaccine.vaccine;

        this.query.select(Projections.fields(Employee.class,
                this.qEmployee.identificationNumber,
                this.qEmployee.name,
                this.qEmployee.lastName,
                this.qEmployee.email,
                this.qEmployee.dateBirth,
                this.qEmployee.address,
                this.qEmployee.phone,
                this.qEmployee.vaccinationStatus,
                Projections.fields(User.class,
                        this.qEmployee.user.username
                ).as(this.qEmployee.user.getMetadata().getName()),
                Projections.fields(Vaccine.class,
                        this.qEmployee.vaccine.type,
                        this.qEmployee.vaccine.vaccinationDate,
                        this.qEmployee.vaccine.numberDoses
                ).as(this.qEmployee.vaccine.getMetadata().getName())

        ));

        this.query.leftJoin(this.qEmployee.user, qUser);
        this.query.leftJoin(this.qEmployee.vaccine, qVaccine);

        this.query.where(this.qEmployee.user.username.eq(username).and(this.qEmployee.isActive.eq(Boolean.TRUE)));

        return Optional.ofNullable(this.query.fetchFirst());
    }

    @Override
    public List<Employee> findEmployeeByFilter(FilterEmployeeRequestVO request) {
        this.initQuery(Boolean.FALSE);

        QUser qUser = QUser.user;
        QVaccine qVaccine = QVaccine.vaccine;

        this.query.select(Projections.fields(Employee.class,
                this.qEmployee.identificationNumber,
                this.qEmployee.name,
                this.qEmployee.lastName,
                this.qEmployee.email,
                this.qEmployee.dateBirth,
                this.qEmployee.address,
                this.qEmployee.phone,
                this.qEmployee.vaccinationStatus,
                Projections.fields(User.class,
                        this.qEmployee.user.username
                ).as(this.qEmployee.user.getMetadata().getName()),
                Projections.fields(Vaccine.class,
                        this.qEmployee.vaccine.type,
                        this.qEmployee.vaccine.vaccinationDate,
                        this.qEmployee.vaccine.numberDoses
                ).as(this.qEmployee.vaccine.getMetadata().getName())

        ));

        this.query.leftJoin(this.qEmployee.user, qUser);
        this.query.leftJoin(this.qEmployee.vaccine, qVaccine);

        this.query.where(this.qEmployee.isActive.eq(Boolean.TRUE))
                .where(this.qEmployee.vaccinationStatus.eq(request.getVaccinationStatus()))
                .where(this.qEmployee.vaccine.type.eq(request.getVaccineType()))
                .where(this.qEmployee.vaccine.vaccinationDate.between(
                        request.getVaccinationDateStart(), request.getVaccinationDateEnd()
                ));

        return this.query.fetch();
    }

    private void initQuery(Boolean isUpdate) {
        this.qEmployee = QEmployee.employee;

        if (isUpdate) {
            this.queryUpdate = new JPAQueryFactory(this.entityManager);
        } else {
            this.query = new JPAQuery<>(this.entityManager);
            this.query.from(this.qEmployee);
        }
    }

}
