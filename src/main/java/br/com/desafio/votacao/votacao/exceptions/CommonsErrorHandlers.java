package br.com.desafio.votacao.votacao.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@Slf4j
@ControllerAdvice
public class CommonsErrorHandlers {

    @ExceptionHandler(RegraException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> regrasDeNegocioException(RegraException e, HttpServletRequest request) {
        log.error("RegraException: message -> {} ", e.getMessage());

        ErrorResponse error = new ErrorResponse();
        error.setHttpStatusCode(HttpStatus.NOT_FOUND.value());
        error.setMessage(e.getMessage());
        error.setTimetamp(Instant.now());
        error.setPath(request.getRequestURI());
        error.setError("Recurso nao encontrado");

        return ResponseEntity.status(error.getHttpStatusCode()).body(error);
    }
}
