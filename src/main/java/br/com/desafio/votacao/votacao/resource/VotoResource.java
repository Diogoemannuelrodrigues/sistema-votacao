package br.com.desafio.votacao.votacao.resource;

import br.com.desafio.votacao.votacao.entity.Voto;
import br.com.desafio.votacao.votacao.service.VotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/voto")
public class VotoResource {

    @Autowired
    private VotoService votoService;

    @PostMapping("/idPauta")
    public ResponseEntity votar(Integer idPauta, @RequestBody @Valid Voto voto) {
        votoService.votar(idPauta, voto);
        log.info("Voto registrado com sucesso!");

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity getResultadoVotacao(Integer idPauta) {
        if (idPauta != null) {
            log.info("Apurando votação!");
            var resultado = votoService.resultadoVotacao(idPauta);
            return ResponseEntity.ok().body(resultado);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
