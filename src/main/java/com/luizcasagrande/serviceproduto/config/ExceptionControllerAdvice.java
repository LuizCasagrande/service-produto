package com.luizcasagrande.serviceproduto.config;

import com.luizcasagrande.serviceproduto.http.data.response.Error;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.persistence.NoResultException;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @Value("${springdoc.swagger-ui.path}")
    private String urlDocumentacao;

    @Hidden
    @ResponseBody
    @ExceptionHandler(NoResultException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error noResult(NoResultException e) {
        return new Error("X_10", e.getMessage(), urlDocumentacao);
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error methodArgumentTypeMismatch() {
        return new Error("X_20", "Parâmetro inválido", urlDocumentacao);
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Error methodArgumentNotValid(MethodArgumentNotValidException e) {
        String mensagem = e.getBindingResult().getFieldErrors().stream()
                .map(f -> f.getField() + " " + f.getDefaultMessage())
                .collect(Collectors.joining("; "));
        return new Error("X_30", mensagem, urlDocumentacao);
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Error other(Exception e) {
        return new Error("X_40", e.getMessage(), urlDocumentacao);
    }
}
