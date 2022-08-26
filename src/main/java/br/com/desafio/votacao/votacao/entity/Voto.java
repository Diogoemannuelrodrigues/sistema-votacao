package br.com.desafio.votacao.votacao.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@RequiredArgsConstructor
public class Voto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    private String cpfEleitor;

    @Column(name = "data")
    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "id_sessao_votacao")
    private SessaoVotacao sessaoVotacao;

    @Column(name = "tipo_voto")
    @Enumerated(EnumType.STRING)
    @NonNull
    private TipoVoto tipoVoto;
}
