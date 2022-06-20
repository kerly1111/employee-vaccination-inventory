package com.kruger.employee.vaccination.inventory.controller;

import com.kruger.employee.vaccination.inventory.service.interfaces.IEmployeeService;
import com.kruger.employee.vaccination.inventory.vo.request.EmployeeRequestVO;
import com.kruger.employee.vaccination.inventory.vo.response.CreateEmployeeResponseVO;
import com.kruger.employee.vaccination.inventory.vo.response.EmployeeResponseVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class EmployeeControllerTest {

    @Mock
    private IEmployeeService employeeService;

    @InjectMocks
    private  EmployeeController employeeController;

    private EmployeeRequestVO employeeRequestVO;

    private EmployeeResponseVO employeeResponseVO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        employeeRequestVO = EmployeeRequestVO.builder()
                .identificationNumber("1205361619")
                .name("Kerly")
                .lastName("Martinez")
                .email("krmc_94@hotmail.com")
                .build();

        employeeResponseVO = EmployeeResponseVO.builder()
                .identificationNumber("1205361619")
                .name("Kerly")
                .lastName("Martinez")
                .email("krmc_94@hotmail.com")
                .dateBirth(new Date(1994, 11,11))
                .address("urdesa")
                .phone("0988207967")
                .vaccinationStatus(Boolean.TRUE)
                .vaccineType("Sputnik,")
                .vaccinationDate(new Date(2022, 02,20))
                .numberDoses(3)
                .build();
    }

    @Test
    void createEmployee() {
        when(employeeService.createEmployee(employeeRequestVO)).thenReturn(new CreateEmployeeResponseVO());
    }

    @Test
    void allEmployee() {
        when(employeeService.allEmployee()).thenReturn(Arrays.asList(employeeResponseVO));
    }

    @Test
    void viewEmployee() {
        when(employeeService.viewEmployee()).thenReturn(employeeResponseVO);
        assertNotNull(employeeController.viewEmployee());
    }
}