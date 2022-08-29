package br.com.desafio.votacao.votacao.service;

import br.com.desafio.votacao.votacao.entity.SessaoVotacao;
import br.com.desafio.votacao.votacao.exceptions.SessaoVotacaoException;
import br.com.desafio.votacao.votacao.repository.PautaRepository;
import br.com.desafio.votacao.votacao.repository.SessaoVotacaoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class SessaoVotacaoService {

    private final SessaoVotacaoRepository sessaoVotacaoRepository;

    private final PautaRepository pautaRepository;

    @Value("${tempo.sessao.votacao.segundos}")
    private Integer tempoSessaoPadrao;

    public SessaoVotacao createSession(Integer idPauta, String closeDate) {
        var pauta = pautaRepository.findById(idPauta);
        if (pauta.isEmpty()) throw new SessaoVotacaoException("Não possivel criar Sessao");
        LocalDateTime closeDateDefault = LocalDateTime.now().plusSeconds(tempoSessaoPadrao);
        LocalDateTime localDateTime = Objects.nonNull(closeDate) ? getLocalDateTime(closeDate) : closeDateDefault;
        SessaoVotacao sessaoVotacao = SessaoVotacao.builder()
                .dataAbertura(LocalDateTime.now())
                .dataFechamento(dataFechamento(localDateTime))
                .pauta(pauta.get())
                .active(true)
                .build();
        return sessaoVotacaoRepository.save(sessaoVotacao);
    }

    private LocalDateTime getLocalDateTime(String closeDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        String date = closeDate;

        LocalDate localDate = LocalDate.parse(date, formatter);
        LocalDateTime localDateTime = localDate.atStartOfDay();

        log.info("Date time {}", localDate);
        return localDateTime;
    }

    public String deleteSession(Integer id) {

        if (id == null) {
            return "O id nao pode ser nulo";
        }
        var sessao = sessaoVotacaoRepository.findById(id);

        if (sessao.isPresent()) {
            sessao.get().setActive(false);
            sessaoVotacaoRepository.save(sessao.get());
            return "Sessao deletada com sucesso {}" + id;
        }
        return "Sessao nao encontrada";
    }

    private LocalDateTime dataFechamento(LocalDateTime dataFechamento) {
        return dataFechamento == null ? LocalDateTime.now().plusSeconds(tempoSessaoPadrao) : dataFechamento;
    }

    public List<SessaoVotacao> getSessoes() {
        List<SessaoVotacao> sessoes = sessaoVotacaoRepository.findByActiveTrue();
        return sessoes;
    }

    public SessaoVotacao findBy(Integer idSessao) {
        var sessao = sessaoVotacaoRepository.findById(idSessao);
        if (sessao.isPresent()) {
            return sessao.get();
        }
        return null;
    }
}
