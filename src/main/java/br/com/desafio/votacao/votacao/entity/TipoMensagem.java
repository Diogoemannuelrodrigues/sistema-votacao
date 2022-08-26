package br.com.desafio.votacao.votacao.entity;

public enum TipoMensagem {

    SESSAO_FECHADA(1, "Sessão Fechada"),
    SESSAO_NAO_ENCONTRADA(2, "Sessão não Encontrada"),
    SESSAO_JA_EXISTE(3, "Sessão Cancelada"),
    PAUTA_NAO_ENCONTRADA(4, "Pauta Cancelada"),
    VOTO_JA_REGISTRADO(5, "Voto já Registrado");

    private int cod;
    private String descricao;

    private TipoMensagem(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoMensagem toEnum(Integer cod) {

        if (cod == null) {
            return null;
        }

        for (TipoMensagem x : TipoMensagem.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }
}
