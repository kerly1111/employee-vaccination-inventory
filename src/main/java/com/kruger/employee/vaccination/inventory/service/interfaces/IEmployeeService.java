package com.kruger.employee.vaccination.inventory.service.interfaces;

import com.kruger.employee.vaccination.inventory.vo.request.FilterEmployeeRequestVO;
import com.kruger.employee.vaccination.inventory.vo.request.UpdateEmployeeRequestVO;
import com.kruger.employee.vaccination.inventory.vo.response.CreateEmployeeResponseVO;
import com.kruger.employee.vaccination.inventory.vo.request.EmployeeRequestVO;
import com.kruger.employee.vaccination.inventory.vo.response.EmployeeResponseVO;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface IEmployeeService {

    CreateEmployeeResponseVO createEmployee(EmployeeRequestVO request);

    void editEmployee(EmployeeRequestVO request);

    void deleteEmployee(String identificationNumber);

    List<EmployeeResponseVO> allEmployee();

    void updateEmployee(UpdateEmployeeRequestVO request);

    EmployeeResponseVO viewEmployee();

    List<EmployeeResponseVO> findEmployeeByFilter(FilterEmployeeRequestVO request);
}
