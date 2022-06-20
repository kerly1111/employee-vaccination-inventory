package com.kruger.employee.vaccination.inventory.repository;

import com.kruger.employee.vaccination.inventory.model.Vaccine;
import com.kruger.employee.vaccination.inventory.repository.custom.CustomVaccineRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccineRepository extends JpaRepository<Vaccine, Long>, CustomVaccineRepository {
}
