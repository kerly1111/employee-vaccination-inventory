package com.kruger.employee.vaccination.inventory.common.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class KrugerException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public KrugerException(String message){
        super(message);
    }
}
