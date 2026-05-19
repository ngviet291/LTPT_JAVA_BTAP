
INSERT INTO customers (customer_id, name, phone, address, year_of_birth) VALUES
                                                                             ('C001', 'Nguyen Van A', '0901234567', 'HCM', 1995),
                                                                             ('C002', 'Tran Thi B', '0912345678', 'Hanoi', 1998),
                                                                             ('C003', 'Le Van C', '0923456789', 'Da Nang', 2000);
INSERT INTO movies (movie_id, title, director, duration, genre, release_year) VALUES
                                                                                  ('M001', 'Avengers', 'Russo Brothers', 180, 'Action', 2019),
                                                                                  ('M002', 'Inception', 'Christopher Nolan', 148, 'Sci-Fi', 2010),
                                                                                  ('M003', 'Parasite', 'Bong Joon-ho', 132, 'Drama', 2019);
INSERT INTO movie_actors (movie_id, actor) VALUES
                                                ('M001', 'Robert Downey Jr'),
                                                ('M001', 'Chris Evans'),
                                                ('M001', 'Scarlett Johansson'),

                                                ('M002', 'Leonardo DiCaprio'),
                                                ('M002', 'Joseph Gordon-Levitt'),

                                                ('M003', 'Song Kang-ho'),
                                                ('M003', 'Lee Sun-kyun');
INSERT INTO shows (show_id, movie_id, hall_name, show_date_time) VALUES
                                                                     ('S001', 'M001', 'Hall 1', '2026-05-12 18:00:00'),
                                                                     ('S002', 'M002', 'Hall 2', '2026-05-12 20:00:00'),
                                                                     ('S003', 'M003', 'Hall 3', '2026-05-13 19:30:00');

INSERT INTO tickets (ticket_number, customer_id, show_id, booking_date, seat, price, type) VALUES
                                                                                               ('T001', 'C001', 'S001', '2026-05-01', 'A1', 75000, 'STANDARD'),
                                                                                               ('T002', 'C001', 'S002', '2026-05-01', 'B2', 80000, 'VIP'),
                                                                                               ('T003', 'C002', 'S001', '2026-05-01', 'A2', 75000, 'STANDARD'),
                                                                                               ('T004', 'C003', 'S003', '2026-05-01', 'C3', 90000, 'VIP');

INSERT INTO shows (show_id, movie_id, hall_name, show_date_time)
VALUES
    ('S004', 'M001', 'Hall 1', CURRENT_TIMESTAMP),
    ('S005', 'M002', 'Hall 2', CURRENT_TIMESTAMP);