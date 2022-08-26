package br.com.desafio.votacao.votacao.exceptions;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class ErrorResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String message;
    private Integer httpStatusCode;
    private String path;
    private Instant timetamp;
    private String error;

}
