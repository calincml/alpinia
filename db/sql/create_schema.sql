CREATE TABLE serie
(
    id SERIAL NOT NULL,
    nombre  varchar(100) NOT NULL,
    caratula  varchar(200) NOT NULL,
    plataforma  varchar(100) NOT NULL,
    sinopsis  text NOT NULL,
    valoracion_media  DECIMAL(2,1) DEFAULT 0.0,
    CONSTRAINT pk_serie PRIMARY KEY ( id )
);

CREATE TABLE usuario
(
    id SERIAL NOT NULL,
    nombre varchar(50) NOT NULL,
    email varchar(50) NOT NULL,
    password varchar(50) NOT NULL,
    CONSTRAINT pk_usuario PRIMARY KEY ( id )
);

CREATE TABLE valoracion (
    usuario_id int NOT NULL,
    serie_id int NOT NULL,
    valoracion int NOT NULL,
    CONSTRAINT pk_valoracion PRIMARY KEY (usuario_id, serie_id),
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE,
	CONSTRAINT fk_serie FOREIGN KEY (serie_id) REFERENCES serie(id) ON DELETE CASCADE
);