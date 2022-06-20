package com.kruger.employee.vaccination.inventory;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
		title = "Employee vaccination inventory",
		version = "1.0.0",
		description = "Aplicación para llevar un registro del inventario del estado de vacunación de los empleados."))
@SecurityScheme(
		name = "authBasic",
		scheme = "basic",
		type = SecuritySchemeType.HTTP)
public class EmployeeVaccinationInventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeVaccinationInventoryApplication.class, args);
	}

}
