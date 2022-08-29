package br.com.desafio.votacao.votacao.service;

import br.com.desafio.votacao.votacao.entity.Pauta;
import br.com.desafio.votacao.votacao.entity.SessaoVotacao;
import br.com.desafio.votacao.votacao.entity.Voto;
import br.com.desafio.votacao.votacao.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PautaService {

    @Autowired
    private PautaRepository pautaRepository;
    @Autowired
    private SessaoVotacaoService service;

    @Value("${tempo.sessao.votacao.segundos}")
    private Integer tempoSessaoPadrao;

    public Pauta savePauta(Pauta pauta) {
        pautaRepository.save(pauta);
        return pauta;
    }

    public void deletePauta(Integer id){
        if (id == null) {
            throw new RuntimeException("Deve conter o id da Pauta");
        }
        pautaRepository.deleteById(id);
    }

    public Pauta findBy(Integer idPauta) {
        var pauta =  pautaRepository.findById(idPauta);
        if (pauta.isPresent()){
        return pauta.get();
        }
        return null;
    }

    public List<Pauta> pautaList(){
        return pautaRepository.findAll();
    }

    public SessaoVotacao getSessao(Integer idSessao){
        var sessao = service.findBy(idSessao);
        return sessao;
    }

    public Map<String, Long> resultadoVotacao(Integer idPauta) {

        List<Voto> votos = getSessao(idPauta).getVotos();
        if(!votos.isEmpty()){

            Map<String, Long> result = new HashMap<>();
            result.put("SIM", votos.stream().filter(v -> v.getTipoVoto().toString().equalsIgnoreCase("SIM")).count());
            result.put("NAO", votos.stream().filter(v -> v.getTipoVoto().toString().equalsIgnoreCase("NAO")).count());

            return result;
        }
        return null;
    }
}
