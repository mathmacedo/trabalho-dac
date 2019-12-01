CREATE TABLE tb_cliente(
	id_cliente SERIAL PRIMARY KEY,
	email_cliente VARCHAR(100) NOT NULL,
	nome_cliente VARCHAR (70) NOT NULL,
	senha_cliente VARCHAR(64) NOT NULL,
	cpf_cliente VARCHAR(11) NOT NULL UNIQUE,
	telefone_cliente VARCHAR(11) NOT NULL,
	id_endereco INTEGER NOT NULL
);
