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

CREATE TABLE tb_cliente(
	id_cliente SERIAL PRIMARY KEY,
	nome_cliente VARCHAR (70) NOT NULL,
	senha_cliente VARCHAR(64) NOT NULL,
	cpf_cliente VARCHAR(11) NOT NULL UNIQUE,
	telefone_cliente VARCHAR(11) NOT NULL,
	id_endereco INTEGER NOT NULL,
	CONSTRAINT fk_cliente_id_endereco FOREIGN KEY (id_endereco)
	REFERENCES tb_endereco(id_endereco) MATCH SIMPLE
	ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE tb_aviao(
	id_aviao SERIAL PRIMARY KEY,
	modelo_aviao VARCHAR(50) NOT NULL
);

CREATE TABLE tb_voo(
	id_voo SERIAL PRIMARY KEY,
	id_cidade_origem INTEGER NOT NULL,
	id_cidade_destino INTEGER NOT NULL,
	horario_partida TIME NOT NULL,
	horario_chegada TIME NOT NULL,
	data_saida DATE NOT NULL,
	data_chegada DATE NOT NULL
	poltronas_total INTEGER NOT NULL,
	poltronas_vagas INTEGER NOT NULL,
	poltronas_reservadas INTEGER NOT NULL,
	poltronas_compradas INTEGER NOT NULL,
	CONSTRAINT fk_voo_id_cidade_origem FOREIGN KEY (id_cidade_origem)
	REFERENCES tb_cidade(id_cidade) MATCH SIMPLE
	ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT fk_voo_id_cidade_destino FOREIGN KEY (id_cidade_destino)
	REFERENCES tb_cidade(id_cidade) MATCH SIMPLE
	ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE tb_reserva(
	id_reserva SERIAL PRIMARY KEY,
	boleto_reserva INTEGER NOT NULL,
	boleto_pago BOOLEAN NOT NULL,
	hora_reserva TIME NOT NULL,
	localizador_reserva VARCHAR(50) NOT NULL,
	id_voo INTEGER NOT NULL,
	id_cliente INTEGER NOT NULL,
	CONSTRAINT fk_reserva_id_voo FOREIGN KEY (id_voo)
	REFERENCES tb_voo(id_voo) MATCH SIMPLE
	ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT fk_reserva_id_cliente FOREIGN KEY (id_cliente)
	REFERENCES tb_cliente(id_cliente) MATCH SIMPLE
	ON UPDATE NO ACTION ON DELETE NO ACTION
);