package com.kruger.employee.vaccination.inventory.repository.custom.impl;

import com.kruger.employee.vaccination.inventory.model.Employee;
import com.kruger.employee.vaccination.inventory.model.QVaccine;
import com.kruger.employee.vaccination.inventory.model.Vaccine;
import com.kruger.employee.vaccination.inventory.repository.custom.CustomVaccineRepository;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;

@Repository
public class CustomVaccineRepositoryImpl implements CustomVaccineRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private QVaccine qVaccine;

    private JPAQuery<Employee> query;

    private JPAQueryFactory queryUpdate;

    @Override
    public void updateVaccine(Vaccine vaccine) {
        this.initQuery(Boolean.TRUE);

        this.queryUpdate
                .update(this.qVaccine)
                .set(this.qVaccine.type, vaccine.getType())
                .set(this.qVaccine.vaccinationDate, vaccine.getVaccinationDate())
                .set(this.qVaccine.numberDoses, vaccine.getNumberDoses())
                .set(this.qVaccine.updateDate, vaccine.getUpdateDate())
                .set(this.qVaccine.updateUserId, vaccine.getUpdateUserId())
                .where(this.qVaccine.employee.id.eq(vaccine.getEmployeId()))
                .execute();
    }

    private void initQuery(Boolean isUpdate) {
        this.qVaccine = QVaccine.vaccine;

        if (isUpdate) {
            this.queryUpdate = new JPAQueryFactory(this.entityManager);
        } else {
            this.query = new JPAQuery<>(this.entityManager);
            this.query.from(this.qVaccine);
        }
    }
}
