package br.com.desafio.votacao.votacao.repository;

import br.com.desafio.votacao.votacao.entity.SessaoVotacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessaoVotacaoRepository extends JpaRepository<SessaoVotacao, Integer> {

    List<SessaoVotacao> findByActiveTrue();

}
