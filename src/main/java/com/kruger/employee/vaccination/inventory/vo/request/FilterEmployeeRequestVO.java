package com.kruger.employee.vaccination.inventory.vo.request;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class FilterEmployeeRequestVO {

    private Boolean vaccinationStatus;

    private String vaccineType;

    private Date vaccinationDateStart;

    private Date vaccinationDateEnd;
}
