package br.com.desafio.votacao.votacao.resource;

import br.com.desafio.votacao.votacao.entity.Pauta;
import br.com.desafio.votacao.votacao.service.PautaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/pautas")
public class PautaResource {

    @Autowired
    private PautaService pautaService;

    @GetMapping("/{idPauta}")
    public ResponseEntity<Pauta> getByIdPautas(@PathVariable Integer idPauta) {

        log.info("Listando pautas...");
        var pauta = pautaService.findBy(idPauta);
        if (Objects.nonNull(pauta)) {
            return ResponseEntity
                    .ok(pauta);
        }
        return null;
    }

    @PostMapping
    public ResponseEntity<Pauta> criarPauta(@RequestBody Pauta pauta) {
        log.info("Criando pauta...");
        var pautaNew = pautaService.savePauta(pauta);
        log.info("Pauta criada com sucesso!");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pauta);
    }

    @DeleteMapping
    private ResponseEntity<?> deletePauta(Integer idPauta) {
        pautaService.deletePauta(idPauta);
        log.info("Pauta deletada com sucesso {}", idPauta);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @GetMapping
    public ResponseEntity getResultadoVotacao(Integer idPauta) {
        if (idPauta != null) {
            log.info("Apurando votação!");
            var resultado = pautaService.resultadoVotacao(idPauta);
            return ResponseEntity.ok().body(resultado);
        }
        log.info("Apurando votação!");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
