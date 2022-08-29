package br.com.desafio.votacao.votacao.service;

import br.com.desafio.votacao.votacao.entity.Pauta;
import br.com.desafio.votacao.votacao.entity.SessaoVotacao;
import br.com.desafio.votacao.votacao.entity.TipoMensagem;
import br.com.desafio.votacao.votacao.entity.Voto;
import br.com.desafio.votacao.votacao.exceptions.RegraException;
import br.com.desafio.votacao.votacao.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VotoService {

    @Autowired
    private VotoRepository votoRepository;
    @Autowired
    private PautaService pautaService;
    @Autowired
    private SessaoVotacaoService sessaoVotacaoService;

    public Pauta getPauta(Integer idPauta){
        var pauta = pautaService.findBy(idPauta);
        return pauta;
    }

    public SessaoVotacao getSessao(Integer idSessao){
        var sessao = sessaoVotacaoService.findBy(idSessao);
        return sessao;
    }

    @Transactional
    public void votar(Integer idPauta, Voto voto) {
        SessaoVotacao sessaoVotacao = getSessao(idPauta);

        if (LocalDateTime.now().isAfter(sessaoVotacao.getDataFechamento())) {
            throw new RegraException(TipoMensagem.SESSAO_FECHADA.getDescricao());
        }

        voto.setSessaoVotacao(sessaoVotacao);
        voto.setDataHora(LocalDateTime.now());

        if(votoRepository.existsBySessaoVotacaoAndCpfEleitor(sessaoVotacao, voto.getCpfEleitor())) {
            throw new RegraException(TipoMensagem.VOTO_JA_REGISTRADO.getDescricao());
        }

        votoRepository.save(voto);
    }

//    public Map<String, Long> resultadoVotacao(Integer idPauta) {
//
//        List<Voto> votos = getSessao(idPauta).getVotos();
//        if(!votos.isEmpty()){
//
//        Map<String, Long> result = new HashMap<>();
//        result.put("SIM", votos.stream().filter(v -> v.getTipoVoto().toString().equalsIgnoreCase("SIM")).count());
//        result.put("NAO", votos.stream().filter(v -> v.getTipoVoto().toString().equalsIgnoreCase("NAO")).count());
//
//        return result;
//        }
//        return null;
//    }
}
