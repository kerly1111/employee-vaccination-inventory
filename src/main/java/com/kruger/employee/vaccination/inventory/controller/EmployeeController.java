package com.kruger.employee.vaccination.inventory.controller;

import com.kruger.employee.vaccination.inventory.service.interfaces.IEmployeeService;
import com.kruger.employee.vaccination.inventory.vo.request.FilterEmployeeRequestVO;
import com.kruger.employee.vaccination.inventory.vo.request.UpdateEmployeeRequestVO;
import com.kruger.employee.vaccination.inventory.vo.response.CreateEmployeeResponseVO;
import com.kruger.employee.vaccination.inventory.vo.request.EmployeeRequestVO;
import com.kruger.employee.vaccination.inventory.vo.response.EmployeeResponseVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Setter;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Setter
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Resource
    private IEmployeeService employeeService;

    @Tag(name = "adm", description = "Administración de empleados, el administrador puede registrar, editar, listar y eliminar a los empleados. Se requiere se usuario administrador para acceder")
    @Operation(
            summary = "Registrar un nuevo empleado",
            description = "Se deben proporcionar los datos del empleado a registrar",
            security = @SecurityRequirement(name = "authBasic"),
            responses= {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @PostMapping(value ="/adm/createEmployee")
    public CreateEmployeeResponseVO createEmployee(@RequestBody EmployeeRequestVO request) {
        return this.employeeService.createEmployee(request);
    }

    @Tag(name = "adm")
    @Operation(
            summary = "Editar datos del empleado.",
            description = "Se deben procionar los datos a moficiar sin faltar el dato de la cedula.",
            security = @SecurityRequirement(name = "authBasic"),
            responses= {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @PostMapping(value ="/adm/editEmployee")
    public String editEmployee(@RequestBody EmployeeRequestVO request) {
        this.employeeService.editEmployee(request);
        return "Usuario actualizado exitosamente";
    }

    @Tag(name = "adm")
    @Operation(
            summary = "Eliminar a un empleado",
            description = "Se debe proporcionar la cédula del empleado para eliminarlo",
            security = @SecurityRequirement(name = "authBasic"),
            responses= {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @DeleteMapping("/adm/deleteEmployee/{identificationNumber}")
    public String deleteEmployee(@PathVariable(value = "identificationNumber") String identificationNumber) {
        this.employeeService.deleteEmployee(identificationNumber);
        return "Empleado eliminado exitosamente";
    }

    @Tag(name = "adm")
    @Operation(
            summary = "Listar empleados registrados",
            security = @SecurityRequirement(name = "authBasic"),
            responses= {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @GetMapping(value ="/adm/allEmployee")
    public List<EmployeeResponseVO> allEmployee() {
        return this.employeeService.allEmployee();
    }

    @Tag(name = "adm")
    @Operation(
            summary = "Listar empleados registrados segun filtros",
            security = @SecurityRequirement(name = "authBasic"),
            responses= {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @PostMapping(value ="/adm/findEmployeeByFilter")
    public List<EmployeeResponseVO> findEmployeeByFilter(@RequestBody FilterEmployeeRequestVO request) {
        return this.employeeService.findEmployeeByFilter(request);
    }

    @Tag(name = "emp", description = "Un empleado puede ingresar al sistema para visualizar y actualizar mi información. Se requiere se un empleado para acceder.")
    @Operation(
            summary = "El empleado puede actualizar su información",
            security = @SecurityRequirement(name = "authBasic"),
            responses= {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @PostMapping(value ="/emp/updateEmployee")
    public String updateEmployee(@RequestBody UpdateEmployeeRequestVO request) {
        this.employeeService.updateEmployee(request);
        return "Datos actualizados exitoadm/findEmployeeByFiltersamente";
    }

    @Tag(name = "emp")
    @Operation(
            summary = "El empleado puede visualizar su información",
            security = @SecurityRequirement(name = "authBasic"),
            responses= {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @GetMapping(value ="/emp/viewEmployee")
    public EmployeeResponseVO viewEmployee() {
        return this.employeeService.viewEmployee();
    }
}
