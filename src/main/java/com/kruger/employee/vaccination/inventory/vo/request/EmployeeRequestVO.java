package com.kruger.employee.vaccination.inventory.vo.request;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EmployeeRequestVO {

    private Long id;

    @NotNull(message = "The field identificationNumber can not be null.")
    private String identificationNumber;

    @NotNull(message = "The field name can not be null.")
    private String name;

    @NotNull(message = "The field lastName can not be null.")
    private String lastName;

    @NotNull(message = "The field email can not be null.")
    private String email;

    private Long updateUser;

}
