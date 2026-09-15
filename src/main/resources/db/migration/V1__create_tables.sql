CREATE TABLE clientes (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    email VARCHAR(150),

    CONSTRAINT pk_clientes PRIMARY KEY (id),
    CONSTRAINT uk_clientes_email UNIQUE (email)
);

CREATE TABLE servicos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(255),
    preco DECIMAL(10, 2) NOT NULL,
    duracao_minutos INT NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,

    CONSTRAINT pk_servicos PRIMARY KEY (id)
);

CREATE TABLE agendamentos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    cliente_id BIGINT NOT NULL,
    servico_id BIGINT NOT NULL,
    data_hora DATETIME NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'AGENDADO',
    preco_registrado DECIMAL(10, 2) NOT NULL,
    observacao VARCHAR(255),

    CONSTRAINT pk_agendamentos PRIMARY KEY (id),

    CONSTRAINT fk_agendamentos_clientes
        FOREIGN KEY (cliente_id)
        REFERENCES clientes(id),

    CONSTRAINT fk_agendamentos_servicos
        FOREIGN KEY (servico_id)
        REFERENCES servicos(id),

    CONSTRAINT uk_agendamentos_data_hora
        UNIQUE (data_hora)
);