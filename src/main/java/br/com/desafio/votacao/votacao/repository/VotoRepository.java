package br.com.desafio.votacao.votacao.repository;

import br.com.desafio.votacao.votacao.entity.SessaoVotacao;
import br.com.desafio.votacao.votacao.entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotoRepository extends JpaRepository<Voto, String> {

        Boolean existsBySessaoVotacaoAndCpfEleitor(SessaoVotacao sessaoVotacao, String cpfEleitor);
}
