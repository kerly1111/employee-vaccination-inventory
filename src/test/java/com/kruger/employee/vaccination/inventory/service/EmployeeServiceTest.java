package com.kruger.employee.vaccination.inventory.service;

import com.kruger.employee.vaccination.inventory.model.Employee;
import com.kruger.employee.vaccination.inventory.model.Vaccine;
import com.kruger.employee.vaccination.inventory.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        employee = Employee.builder()
                .identificationNumber("1205361619")
                .name("Kerly")
                .lastName("Martinez")
                .email("krmc_94@hotmail.com")
                .dateBirth(new Date(1994, 11,11))
                .address("urdesa")
                .phone("0988207967")
                .vaccinationStatus(Boolean.TRUE)
                .vaccine(Vaccine.builder()
                        .type("Sputnik,")
                        .vaccinationDate(new Date(2022, 02,20))
                        .numberDoses(3)
                        .build()
                )
                .build();

    }

    @Test
    void allEmployee() {
        when(employeeRepository.allEmployee()).thenReturn(Arrays.asList(employee));
        assertNotNull(employeeService.allEmployee());
    }
}