CREATE TABLE tb_estado(
	id_estado SERIAL PRIMARY KEY,
	nome_estado VARCHAR(70) NOT NULL, 
	sigla_estado VARCHAR(2) NOT NULL
);

CREATE TABLE tb_cidade(
	id_cidade SERIAL PRIMARY KEY,
	nome_cidade VARCHAR(70) NOT NULL,
	id_estado INTEGER NOT NULL,
	CONSTRAINT fk_cidade_id_estado FOREIGN KEY (id_estado)
	REFERENCES tb_estado(id_estado) MATCH SIMPLE
	ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE tb_cidade_aeroporto(
	id_cidade_aeroporto SERIAL PRIMARY KEY,
	nome_cidade_aeroporto VARCHAR(70) NOT NULL,
	sigla_cidade_aeroporto VARCHAR(3) NOT NULL,
	id_estado INTEGER NOT NULL,
	CONSTRAINT fk_cidade_aeroporto_id_estado FOREIGN KEY (id_estado)
	REFERENCES tb_estado(id_estado) MATCH SIMPLE
	ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE tb_endereco(
	id_endereco SERIAL PRIMARY KEY,
	rua_endereco VARCHAR (100) NOT NULL,
	numero_endereco INTEGER NOT NULL,
	complemento_endereco VARCHAR(50) NOT NULL,
	id_cidade INTEGER NOT NULL,
	id_estado INTEGER NOT NULL,
	CONSTRAINT fk_endereco_id_estado FOREIGN KEY (id_estado)
	REFERENCES tb_estado(id_estado) MATCH SIMPLE
	ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT fk_endereco_id_cidade FOREIGN KEY (id_cidade)
	REFERENCES tb_cidade(id_cidade) MATCH SIMPLE
	ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE tb_piloto(
	id_piloto SERIAL PRIMARY KEY,
	nome_piloto VARCHAR(70) NOT NULL,
	cpf_piloto VARCHAR(11) NOT NULL,
	email_piloto VARCHAR(100) NOT NULL,
	telefone_piloto VARCHAR(11) NOT NULL,
	id_endereco INTEGER NOT NULL,
	CONSTRAINT fk_piloto_id_endereco FOREIGN KEY (id_endereco)
	REFERENCES tb_endereco(id_endereco) MATCH SIMPLE
	ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE tb_voo(
	id_voo SERIAL PRIMARY KEY,
	id_cidade_origem INTEGER NOT NULL,
	id_cidade_destino INTEGER NOT NULL,
	horario_partida TIME NOT NULL,
	horario_chegada TIME NOT NULL,
	data_saida DATE NOT NULL,
	data_chegada DATE NOT NULL,
	poltronas_total INTEGER NOT NULL,
	poltronas_vagas INTEGER NOT NULL,
	poltronas_reservadas INTEGER NOT NULL,
	poltronas_compradas INTEGER NOT NULL,
	preco_primeira_classe FLOAT NOT NULL,
	preco_classe_econnomica FLOAT NOT NULL,
	CONSTRAINT fk_voo_id_cidade_origem FOREIGN KEY (id_cidade_origem)
	REFERENCES tb_cidade_aeroporto(id_cidade_aeroporto) MATCH SIMPLE
	ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT fk_voo_id_cidade_destino FOREIGN KEY (id_cidade_destino)
	REFERENCES tb_cidade_aeroporto(id_cidade_aeroporto) MATCH SIMPLE
	ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE tb_funcionario(
	id_funcionario SERIAL PRIMARY KEY,
	nome_funcionario VARCHAR(100) NOT NULL,
	cpf_funcionario VARCHAR(11) NOT NULL,
	email_funcionario VARCHAR(100) NOT NULL,
	senha_funcionario VARCHAR(64) NOT NULL,
	tipo_funcionario CHAR NOT NULL,
	id_endereco INTEGER NOT NULL,
	CONSTRAINT fk_funcionario_id_endereco FOREIGN KEY (id_endereco)
	REFERENCES tb_endereco(id_endereco) MATCH SIMPLE
	ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE tb_checkin(
	id_checkin SERIAL NOT NULL,
	assento_checkin VARCHAR(3) NOT NULL,
	data_checkin DATE NOT NULL,
	hora_checkin TIME NOT NULL,
	status_checkin BOOLEAN NOT NULL,
	id_funcionario INTEGER NOT NULL,
	id_voo INTEGER NOT NULL,
	CONSTRAINT fk_checkin_id_funcionario FOREIGN KEY (id_funcionario)
	REFERENCES tb_funcionario(id_funcionario) MATCH SIMPLE
	ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT fk_checkin_id_voo FOREIGN KEY (id_voo)
	REFERENCES tb_voo(id_voo) MATCH SIMPLE
	ON UPDATE NO ACTION ON DELETE NO ACTION
);