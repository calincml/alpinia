CREATE TABLE serie
(
    id            int NOT NULL,
    nombre       varchar(100) NOT NULL,
    caratula    varchar(200) NOT NULL,
    plataforma   varchar(100) NOT NULL,
    sinopsis     text NOT NULL,
    valoracion_media  varchar(20) NULL,
    CONSTRAINT pk_serie PRIMARY KEY ( id )
);

CREATE TABLE usuario
(
    id            int NOT NULL,
    nombre       varchar(50) NOT NULL,
    CONSTRAINT pk_usuario PRIMARY KEY ( id )
);

CREATE TABLE valoracion (
    usuario_id int NOT NULL,
    serie_id int NOT NULL,
    valoracion int NOT NULL,
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE,
	CONSTRAINT fk_serie FOREIGN KEY (serie_id) REFERENCES serie(id) ON DELETE CASCADE
);