INSERT INTO client (name) VALUES
                              ('Neil Armstrong'), ('Buzz Aldrin'), ('Elon Musk'), ('Yuri Gagarin'),
                              ('John Doe'), ('Jane Smith'), ('Alice Cooper'), ('Bob Marley'),
                              ('Charlie Brown'), ('David Bowie');

INSERT INTO planet (id, name) VALUES
                                  ('MERCURY', 'Mercury'), ('VENUS', 'Venus'), ('EARTH', 'Earth'),
                                  ('MARS', 'Mars'), ('JUPITER', 'Jupiter');

INSERT INTO ticket (client_id, from_planet_id, to_planet_id) VALUES
                                                                 (1, 'EARTH', 'MARS'), (2, 'EARTH', 'VENUS'), (3, 'MARS', 'EARTH'),
                                                                 (4, 'EARTH', 'JUPITER'), (5, 'VENUS', 'MERCURY'), (6, 'JUPITER', 'MARS'),
                                                                 (7, 'EARTH', 'MERCURY'), (8, 'MARS', 'VENUS'), (9, 'VENUS', 'EARTH'),
                                                                 (10, 'JUPITER', 'EARTH');