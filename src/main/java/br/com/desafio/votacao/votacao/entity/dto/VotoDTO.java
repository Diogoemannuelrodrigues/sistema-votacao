package br.com.desafio.votacao.votacao.entity.dto;

import br.com.desafio.votacao.votacao.entity.TipoVoto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode
@ToString
public class VotoDTO {

    @NotNull(message = "CPF do eleitor obrigatório.")
    private String cpfEleitor;

    @NotNull(message = "Mensagem de voto é obrigatório e precisa seguir o padrão: SIM/NAO")
    private TipoVoto tipoVoto;
}
