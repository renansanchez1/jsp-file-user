CREATE TABLE usuario
(
    id bigserial NOT NULL,
    ativo boolean,
    cpf character varying(30),
    data_nascimento date,
    email character varying(50),
    login character varying(50),
    nome character varying(255),
    password character varying(50),
    CONSTRAINT usuario_pkey PRIMARY KEY (id)
);

CREATE TABLE papel
(
    id bigserial NOT NULL,
    tipo_papel character varying(50),
    CONSTRAINT papel_pkey PRIMARY KEY (id)
);

CREATE TABLE usuario_papel
(
    usuario_id bigint NOT NULL,
    papel_id bigint NOT NULL,	  
    CONSTRAINT fk_usuario_papel_papel_id FOREIGN KEY (papel_id)
        REFERENCES papel (id) ON DELETE CASCADE,
    CONSTRAINT fk_usuario_papel_usuario_id FOREIGN KEY (usuario_id)
        REFERENCES usuario (id) ON DELETE CASCADE 
);

INSERT INTO papel (tipo_papel) VALUES ('ADMIN');
INSERT INTO papel (tipo_papel) VALUES ('USER');
INSERT INTO papel (tipo_papel) VALUES ('BIBLIO');
