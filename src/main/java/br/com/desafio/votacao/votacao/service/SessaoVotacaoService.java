package br.com.desafio.votacao.votacao.service;

import br.com.desafio.votacao.votacao.entity.Pauta;
import br.com.desafio.votacao.votacao.entity.SessaoVotacao;
import br.com.desafio.votacao.votacao.repository.SessaoVotacaoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
public class SessaoVotacaoService {

    @Autowired
    private SessaoVotacaoRepository sessaoVotacaoRepository;

    @Value("${tempo.sessao.votacao.segundos}")
    private static Integer tempoSessaoPadrao;

    public void createSession(Pauta pauta, String closeDate) {
        LocalDateTime localDateTime = getLocalDateTime(closeDate);

        SessaoVotacao sessaoVotacao = SessaoVotacao.builder()
                .dataAbertura(LocalDateTime.now())
                .dataFechamento(dataFechamento(localDateTime))
                .pauta(pauta)
                .build();

        sessaoVotacaoRepository.save(sessaoVotacao);
    }

    private LocalDateTime getLocalDateTime(String closeDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        String date = closeDate;

        LocalDate localDate = LocalDate.parse(date, formatter);
        LocalDateTime localDateTime = localDate.atStartOfDay();

        log.info("Date time {}", localDate);
        return localDateTime;
    }

    public void createSessionWithoutPauta(LocalDateTime closeDate) {
        SessaoVotacao sessaoVotacao = SessaoVotacao.builder()
                .dataAbertura(LocalDateTime.now())
                .dataFechamento(dataFechamento(closeDate))
                .build();

        sessaoVotacaoRepository.save(sessaoVotacao);
    }



    public String deleteSession(Integer id){
        if (id == null){
            return "O id nao pode ser nulo";
        }
        sessaoVotacaoRepository.deleteById(id);
        return "Sessao deletada com sucesso {}"+ id;
    }

    private LocalDateTime dataFechamento(LocalDateTime dataFechamento) {
        return dataFechamento == null ? LocalDateTime.now().plusSeconds(tempoSessaoPadrao) : dataFechamento;
    }

    public List<SessaoVotacao> getSessoes() {
        return sessaoVotacaoRepository.findAll();
    }

    public SessaoVotacao findBy(Integer idSessao) {
        var sessao = sessaoVotacaoRepository.findById(idSessao);
        if (sessao.isPresent()){
            return sessao.get();
        }
        return null;
    }
}
