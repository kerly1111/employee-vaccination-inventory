package com.kruger.employee.vaccination.inventory.vo.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class KrugerErrorResponeVO {

    private String typeMessage;

    private  String message;
}
