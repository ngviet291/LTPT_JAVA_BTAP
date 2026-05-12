-- Insert thêm shows cho ngày hôm nay (2026-05-11) với các khung giờ khác nhau
INSERT INTO shows (show_id, hall_name, show_date_time, movie_id) VALUES
('s018','Hall A','2026-05-11 09:00:00','m001'),
('s019','Hall B','2026-05-11 10:00:00','m002'),
('s020','Hall C','2026-05-11 11:30:00','m003'),
('s021','Hall D','2026-05-11 13:00:00','m004'),
('s022','Hall A','2026-05-11 14:30:00','m005'),
('s023','Hall B','2026-05-11 16:00:00','m006'),
('s024','Hall C','2026-05-11 18:00:00','m007'),
('s025','Hall D','2026-05-11 20:00:00','m008'),
('s026','Hall A','2026-05-11 21:30:00','m001');

-- Insert vé cho các shows hôm nay (một số show có vé đã bán, một số chưa)
INSERT INTO tickets (ticket_number, seat, type, price, booking_date, customer_id, show_id) VALUES
('t0026','A1','VIP',150000.0,'2026-05-11','c001','s018'),
('t0027','A2','STANDARD',100000.0,'2026-05-11','c002','s018'),
('t0028','B1','STANDARD',100000.0,'2026-05-11','c003','s019'),
('t0029','B2','STUDENT',70000.0,'2026-05-11','c004','s019'),
('t0030','C1','VIP',150000.0,'2026-05-11','c005','s020'),
-- Show s021 (Lion King) chưa có vé (để test update)
-- Show s022 (Interstellar) chưa có vé (để test update)
('t0031','D1','STANDARD',100000.0,'2026-05-11','c006','s023'),
('t0032','D2','STUDENT',70000.0,'2026-05-11','c007','s023'),
('t0033','E1','VIP',150000.0,'2026-05-11','c008','s024'),
('t0034','E2','STANDARD',100000.0,'2026-05-11','c009','s024');

