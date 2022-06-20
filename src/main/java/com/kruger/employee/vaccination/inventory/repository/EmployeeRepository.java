package com.kruger.employee.vaccination.inventory.repository;

import com.kruger.employee.vaccination.inventory.model.Employee;
import com.kruger.employee.vaccination.inventory.repository.custom.CustomEmployeeRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> , CustomEmployeeRepository {
}
