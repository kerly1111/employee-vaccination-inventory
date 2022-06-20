package com.kruger.employee.vaccination.inventory.common.handler;

import com.kruger.employee.vaccination.inventory.common.exception.KrugerException;
import com.kruger.employee.vaccination.inventory.vo.response.KrugerErrorResponeVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;


@ControllerAdvice
public class CustomGlobalExceptionHandler {

    @ExceptionHandler(KrugerException.class)
    public ResponseEntity<KrugerErrorResponeVO> methodArgumentNotValidException(KrugerException e) {
        KrugerErrorResponeVO exception;

        exception = KrugerErrorResponeVO.builder()
                .typeMessage("Error")
                .message(e.getMessage())
                .build();
        return ResponseEntity.badRequest().body(exception);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<KrugerErrorResponeVO> methodArgumentNotValidException(ConstraintViolationException e) {
        KrugerErrorResponeVO exception;

        exception = KrugerErrorResponeVO.builder()
                .typeMessage("Error de restricciones")
                .message(e.getConstraintViolations().iterator().next().getMessage())
                .build();
        return ResponseEntity.badRequest().body(exception);
    }
}
