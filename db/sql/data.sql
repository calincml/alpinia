INSERT INTO serie (id, nombre, caratula, plataforma, sinopsis, valoracion_media)
VALUES
    (1, 'Breaking Bad', 'https://pics.filmaffinity.com/breaking_bad-504442815-msmall.jpg', 'Netflix', 'Un profesor de química se convierte en productor de metanfetaminas.', '9.5'),
    (2, 'Stranger Things', 'https://pics.filmaffinity.com/stranger_things-875025085-mmed.jpg', 'Netflix', 'Un grupo de amigos investiga la desaparición de un niño en un pequeño pueblo.', '8.9'),
    (3, 'Chernobyl', 'https://pics.filmaffinity.com/chernobyl-183665235-msmall.jpg', 'HBO', 'Serie que narra el desastre nuclear en Chernobyl y sus consecuencias.', '9.3'),
    (4, 'The Mandalorian', 'https://pics.filmaffinity.com/the_mandalorian-299892082-mmed.jpg', 'Disney+', 'Un cazarrecompensas protege a un misterioso niño en el universo de Star Wars.', '9.0'),
    (5, 'The Boys', 'https://pics.filmaffinity.com/the_boys-969809291-mmed.jpg', 'Prime Video', 'Superhéroes corruptos enfrentan a un grupo que busca justicia.', '8.7');

INSERT INTO usuario (id, nombre)
VALUES
    (1, 'Juan Perez'),
    (2, 'Roberto Hernandez'),
    (3, 'Maria Gonzales'),
    (4, 'Ana Lopez'),
    (5, 'Jesus Rodriguez');

INSERT INTO valoracion (usuario_id ,serie_id ,valoracion)
VALUES
    (1, 1, 7),
    (2, 2, 4),
    (3, 3,10),
    (4, 4, 6),
    (5, 5, 8);