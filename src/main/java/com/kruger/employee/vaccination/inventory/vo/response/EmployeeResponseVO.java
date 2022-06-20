package com.kruger.employee.vaccination.inventory.vo.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class EmployeeResponseVO {

    private String identificationNumber;

    private String name;

    private String lastName;

    private String email;

    private Date dateBirth;

    private String address;

    private String phone;

    private Boolean vaccinationStatus;

    private String vaccineType;

    private Date vaccinationDate;

    private Integer numberDoses;

}
