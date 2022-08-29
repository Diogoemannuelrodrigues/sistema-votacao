package br.com.desafio.votacao.votacao.resource;

import br.com.desafio.votacao.votacao.entity.dto.VotoDTO;
import br.com.desafio.votacao.votacao.entity.Voto;
import br.com.desafio.votacao.votacao.service.VotoService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/idPauta")
    public ResponseEntity votar(Integer idPauta, @RequestBody @Valid VotoDTO votoDTO) {
        var voto = objectMapper.convertValue(votoDTO, Voto.class);
        votoService.votar(idPauta, voto);
        log.info("Voto registrado com sucesso!");

        return ResponseEntity.ok().build();
    }
}
