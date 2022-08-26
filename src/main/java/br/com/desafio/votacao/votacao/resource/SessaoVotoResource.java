package br.com.desafio.votacao.votacao.resource;

import br.com.desafio.votacao.votacao.entity.SessaoVotacao;
import br.com.desafio.votacao.votacao.service.PautaService;
import br.com.desafio.votacao.votacao.service.SessaoVotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/sessao")
public class SessaoVotoResource {

    @Autowired
    private SessaoVotacaoService sessaoService;
    @Autowired
    private PautaService pautaService;

    @PostMapping("/criar-sessao-com-pauta")
    public ResponseEntity criarSessaoComPauta(Integer idPauta, String dataFechamento) {

        var pauta = pautaService.findBy(idPauta);

        if (pauta != null){
            sessaoService.createSession(pauta, dataFechamento);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/criar-sessao-sem-pauta")
    public ResponseEntity criarSessaoSemPauta(@RequestBody SessaoVotacao sessao) {
        if (sessao == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        sessaoService.createSessionWithoutPauta(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(Integer iPauta){
        sessaoService.deleteSession(iPauta);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public List<SessaoVotacao> getSessoes(){
        var sessoes = sessaoService.getSessoes();
        if (sessoes.isEmpty()){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return sessoes;
    }
}
