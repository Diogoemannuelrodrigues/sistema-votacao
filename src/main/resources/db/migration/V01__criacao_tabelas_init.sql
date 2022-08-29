CREATE TABLE pauta (
	id serial4 NOT NULL,
	nome varchar(255) NULL,
	CONSTRAINT pauta_pkey PRIMARY KEY (id)
);

CREATE TABLE sessaovotacao (
	id serial4 NOT NULL,
	active bool NOT NULL,
	data_abertura timestamp NULL,
	data_fechamento timestamp NULL,
	id_pauta int4 NULL,
	CONSTRAINT sessaovotacao_pkey PRIMARY KEY (id),
	CONSTRAINT fk4770u9e1ii9as70wd483sl6cn FOREIGN KEY (id_pauta) REFERENCES pauta(id)
);

CREATE TABLE voto (
	cpf_eleitor varchar(255) NOT NULL,
	"data" timestamp NULL,
	tipo_voto varchar(255) NULL,
	id_sessao_votacao int4 NULL,
	CONSTRAINT voto_pkey PRIMARY KEY (cpf_eleitor),
	CONSTRAINT fklny4lt0duewlts18csfy4jryx FOREIGN KEY (id_sessao_votacao) REFERENCES sessaovotacao(id)
);