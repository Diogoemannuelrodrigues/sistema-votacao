package br.com.desafio.votacao.votacao.resource;

import br.com.desafio.votacao.votacao.entity.SessaoVotacao;
import br.com.desafio.votacao.votacao.service.PautaService;
import br.com.desafio.votacao.votacao.service.SessaoVotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sessao")
public class SessaoVotoResource {

    @Autowired
    private SessaoVotacaoService sessaoService;

    @Autowired
    private PautaService pautaService;

    @Value("${tempo.sessao.votacao.segundos}")
    private static Integer tempoSessaoPadrao;

    @PostMapping("/criar-sessao")
    public ResponseEntity<SessaoVotacao> criarSessao(Integer idPauta, String dataFechamento) {
        return new ResponseEntity<>(sessaoService.createSession(idPauta, dataFechamento), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(Integer idSessao) {
        sessaoService.deleteSession(idSessao);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public List<SessaoVotacao> getSessoes() {
        var sessoes = sessaoService.getSessoes();
        if (sessoes.isEmpty()) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return sessoes;
    }
}
