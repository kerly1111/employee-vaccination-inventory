package com.kruger.employee.vaccination.inventory.controller;

import com.kruger.employee.vaccination.inventory.service.interfaces.IEmployeeService;
import com.kruger.employee.vaccination.inventory.vo.request.FilterEmployeeRequestVO;
import com.kruger.employee.vaccination.inventory.vo.request.UpdateEmployeeRequestVO;
import com.kruger.employee.vaccination.inventory.vo.response.CreateEmployeeResponseVO;
import com.kruger.employee.vaccination.inventory.vo.request.EmployeeRequestVO;
import com.kruger.employee.vaccination.inventory.vo.response.EmployeeResponseVO;
import lombok.Setter;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Setter
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Resource
    private IEmployeeService employeeService;

    @PostMapping(value ="/adm/createEmployee")
    public CreateEmployeeResponseVO createEmployee(@RequestBody EmployeeRequestVO request) {
        return this.employeeService.createEmployee(request);
    }

    @PostMapping(value ="/adm/editEmployee")
    public String editEmployee(@RequestBody EmployeeRequestVO request) {
        this.employeeService.editEmployee(request);
        return "Usuario actualizado exitosamente";
    }

    @DeleteMapping("/adm/deleteEmployee/{identificationNumber}")
    public String deleteEmployee(@PathVariable(value = "identificationNumber") String identificationNumber) {
        this.employeeService.deleteEmployee(identificationNumber);
        return "Empleado eliminado exitosamente";
    }

    @GetMapping(value ="/adm/allEmployee")
    public List<EmployeeResponseVO> allEmployee() {
        return this.employeeService.allEmployee();
    }

    @PostMapping(value ="/adm/findEmployeeByFilter")
    public List<EmployeeResponseVO> findEmployeeByFilter(@RequestBody FilterEmployeeRequestVO request) {
        return this.employeeService.findEmployeeByFilter(request);
    }

    @PostMapping(value ="/emp/updateEmployee")
    public String updateEmployee(@RequestBody UpdateEmployeeRequestVO request) {
        this.employeeService.updateEmployee(request);
        return "Datos actualizados exitoadm/findEmployeeByFiltersamente";
    }

    @GetMapping(value ="/emp/viewEmployee")
    public EmployeeResponseVO viewEmployee() {
        return this.employeeService.viewEmployee();
    }
}
