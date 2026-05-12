-- =============================================
-- SAMPLE DATA FOR MOVIE SERVER DATABASE
-- =============================================

-- Insert Types
INSERT INTO TYPE (type) VALUES ('VIP');
INSERT INTO TYPE (type) VALUES ('STANDARD');

-- =============================================
-- MOVIES
-- =============================================
INSERT INTO MOVIES (id, title, genre, release_year, director, duration) VALUES
('M001', 'Avatar', 'Sci-Fi', 2009, 'James Cameron', 162),
('M002', 'Inception', 'Thriller', 2010, 'Christopher Nolan', 148),
('M003', 'The Dark Knight', 'Action', 2008, 'Christopher Nolan', 152),
('M004', 'Interstellar', 'Sci-Fi', 2014, 'Christopher Nolan', 169),
('M005', 'The Matrix', 'Sci-Fi', 1999, 'Lana Wachowski', 136);

-- =============================================
-- MOVIE ACTORS
-- =============================================
INSERT INTO MOVIE_ACTORS (movie_id, actors) VALUES
('M001', 'Sam Worthington'),
('M001', 'Zoe Saldana'),
('M001', 'Sigourney Weaver'),
('M002', 'Leonardo DiCaprio'),
('M002', 'Marion Cotillard'),
('M002', 'Joseph Gordon-Levitt'),
('M003', 'Christian Bale'),
('M003', 'Heath Ledger'),
('M003', 'Aaron Eckhart'),
('M004', 'Matthew McConaughey'),
('M004', 'Anne Hathaway'),
('M004', 'Jessica Chastain'),
('M005', 'Keanu Reeves'),
('M005', 'Laurence Fishburne'),
('M005', 'Carrie-Anne Moss');

-- =============================================
-- CUSTOMERS
-- =============================================
INSERT INTO CUSTOMERS (id, name, year_of_birth, phone, address) VALUES
('C001', 'Nguyễn Văn A', 1990, '0912345678', '123 Nguyễn Hue, Hà Nội'),
('C002', 'Trần Thị B', 1995, '0987654321', '456 Pasteur, TP HCM'),
('C003', 'Phạm Văn C', 2000, '0901234567', '789 Hùng Vương, Đà Nẵng'),
('C004', 'Hoàng Thị D', 1992, '0908765432', '321 Lý Tự Trọng, TP HCM'),
('C005', 'Vũ Văn E', 1998, '0913579246', '654 Bà Triệu, Hà Nội');

-- =============================================
-- SHOWS
-- =============================================
INSERT INTO SHOWS (id, show_date_time, hall_name, movie_id) VALUES
('S001', '2026-05-12 18:00:00', 'Hall A', 'M001'),
('S002', '2026-05-12 20:30:00', 'Hall B', 'M002'),
('S003', '2026-05-13 19:00:00', 'Hall A', 'M003'),
('S004', '2026-05-13 21:00:00', 'Hall C', 'M004'),
('S005', '2026-05-14 17:30:00', 'Hall B', 'M005'),
('S006', '2026-05-14 20:00:00', 'Hall A', 'M001'),
('S007', '2026-05-15 18:00:00', 'Hall C', 'M002');

-- =============================================
-- TICKETS
-- =============================================
INSERT INTO TICKETS (ticket_number, seat, type, price, booking_date, show_id, customer_id) VALUES
('T001', 'A1', 'VIP', 150000, '2026-05-10', 'S001', 'C001'),
('T002', 'A2', 'VIP', 150000, '2026-05-10', 'S001', 'C002'),
('T003', 'B5', 'STANDARD', 100000, '2026-05-11', 'S002', 'C001'),
('T004', 'C3', 'STANDARD', 100000, '2026-05-11', 'S003', 'C003'),
('T005', 'A5', 'VIP', 150000, '2026-05-11', 'S004', 'C004'),
('T006', 'B2', 'STANDARD', 100000, '2026-05-11', 'S005', 'C005'),
('T007', 'A3', 'VIP', 150000, '2026-05-12', 'S006', 'C002'),
('T008', 'C1', 'STANDARD', 100000, '2026-05-12', 'S007', 'C003'),
('T009', 'D4', 'STANDARD', 100000, '2026-05-12', 'S001', 'C004'),
('T010', 'B8', 'VIP', 150000, '2026-05-12', 'S002', 'C005');

