package br.com.desafio.votacao.votacao.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sessaovotacao")
public class SessaoVotacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "data_abertura")
    private LocalDateTime dataAbertura;

    @Column(name = "data_fechamento")
    private LocalDateTime dataFechamento;

    @OneToOne
    @JoinColumn(name = "id_pauta")
    private Pauta pauta;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "sessaoVotacao", cascade = CascadeType.ALL)
    private List<Voto> votos = new ArrayList<>();

    private boolean active;
}
