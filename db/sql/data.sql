INSERT INTO serie (nombre, caratula, plataforma, sinopsis, valoracion_media)
VALUES
    ('Breaking Bad', 'https://pics.filmaffinity.com/breaking_bad-504442815-msmall.jpg', 'Netflix', 'Un profesor de química se convierte en productor de metanfetaminas.', 0.0),
    ('Stranger Things', 'https://pics.filmaffinity.com/stranger_things-875025085-mmed.jpg', 'Netflix', 'Un grupo de amigos investiga la desaparición de un niño en un pequeño pueblo.', 1.1),
    ('Chernobyl', 'https://pics.filmaffinity.com/chernobyl-183665235-msmall.jpg', 'HBO', 'Serie que narra el desastre nuclear en Chernobyl y sus consecuencias.', 9.3),
    ('The Mandalorian', 'https://pics.filmaffinity.com/the_mandalorian-299892082-mmed.jpg', 'Disney+', 'Un cazarrecompensas protege a un misterioso niño en el universo de Star Wars.', 9.0),
    ('The Boys', 'https://pics.filmaffinity.com/the_boys-969809291-mmed.jpg', 'Prime Video', 'Superhéroes corruptos enfrentan a un grupo que busca justicia.', 8.7);

INSERT INTO usuario (nombre, email, password)
VALUES
    ('Juan Perez', 'juan.perez@example.com', 'password123'),
    ('Roberto Hernandez', 'roberto.hernandez@example.com', 'password456'),
    ('Maria Gonzales', 'maria.gonzales@example.com', 'password789'),
    ('Ana Lopez', 'ana.lopez@example.com', 'password101'),
    ('Jesus Rodriguez', 'jesus.rodriguez@example.com', 'password202');

INSERT INTO valoracion (usuario_id ,serie_id ,valoracion)
VALUES
    (1, 1, 7),
    (2, 2, 4),
    (3, 3,10),
    (4, 4, 6),
    (5, 5, 8);