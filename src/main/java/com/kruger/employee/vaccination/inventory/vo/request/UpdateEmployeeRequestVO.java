package com.kruger.employee.vaccination.inventory.vo.request;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UpdateEmployeeRequestVO {

    @NotNull(message = "The field dateBirth can not be null.")
    private Date dateBirth;

    @NotNull(message = "The field address can not be null.")
    private String address;

    @NotNull(message = "The field phone can not be null.")
    private String phone;

    @NotNull(message = "The field vaccinationStatus can not be null.")
    private Boolean vaccinationStatus;

    private String vaccineType;

    private Date vaccinationDate;

    private Integer numberDoses;

    private Long updateUser;

}
