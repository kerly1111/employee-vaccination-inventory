package com.kruger.employee.vaccination.inventory.repository.custom;

import com.kruger.employee.vaccination.inventory.model.Employee;
import com.kruger.employee.vaccination.inventory.vo.request.EmployeeRequestVO;
import com.kruger.employee.vaccination.inventory.vo.request.FilterEmployeeRequestVO;
import com.kruger.employee.vaccination.inventory.vo.request.UpdateEmployeeRequestVO;
import com.kruger.employee.vaccination.inventory.vo.response.EmployeeResponseVO;

import java.util.List;
import java.util.Optional;

public interface CustomEmployeeRepository {

    void editEmployee(Long id, EmployeeRequestVO request);

    Optional<Employee> findByIdentification(String identificationNumber);

    List<Employee> allEmployee();

    void updateEmployee(Long id, UpdateEmployeeRequestVO request);

    Optional<Employee> findByUsername(String username);

    Optional<Employee> viewMyData(String username);

    List<Employee> findEmployeeByFilter(FilterEmployeeRequestVO request);

}
