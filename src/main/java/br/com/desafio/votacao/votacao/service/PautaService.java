package br.com.desafio.votacao.votacao.service;

import br.com.desafio.votacao.votacao.entity.Pauta;
import br.com.desafio.votacao.votacao.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PautaService {

    @Autowired
    private PautaRepository pautaRepository;

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
}
