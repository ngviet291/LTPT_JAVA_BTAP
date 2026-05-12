INSERT INTO Doctor(id, name, phone, speciality)
VALUES
    ('D001', 'Nguyen Van A', '0909123456', 'Cardiology'),
    ('D002', 'Tran Thi B', '0911222333', 'Neurology'),
    ('D003', 'Le Van C', '0988777666', 'Orthopedic');

-- =========================
-- PATIENT
-- =========================
INSERT INTO Patient(id, name, phone, address, dateOfBirth, gender)
VALUES
    ('000-00-0001', 'Pham Minh Tuan', '0909000001', 'HCM City', '2001-05-12', 'Male'),
    ('000-00-0002', 'Nguyen Thi Lan', '0909000002', 'Dong Nai', '1999-08-20', 'Female'),
    ('000-00-0003', 'Tran Quoc Bao', '0909000003', 'Binh Duong', '2000-01-15', 'Male');

-- =========================
-- DEPARTMENT
-- =========================
INSERT INTO Department(id, name, location)
VALUES
    ('DEP01', 'Cardiology', 'Floor 1'),
    ('DEP02', 'Neurology', 'Floor 2'),
    ('DEP03', 'Orthopedic', 'Floor 3');

-- =========================
-- TREATMENT
-- =========================
INSERT INTO Treatment(startDate, diagnosis, endDate, patient_id, department_id, doctor_id)
VALUES
    ('2023-05-01', 'Heart Disease', '2023-05-10', '000-00-0001', 'DEP01', 'D001'),

    ('2023-05-03', 'Migraine', '2023-05-08', '000-00-0002', 'DEP02', 'D002'),

    ('2023-06-01', 'Broken Leg', '2023-06-20', '000-00-0003', 'DEP03', 'D003'),

    ('2023-06-15', 'Heart Checkup', '2023-06-18', '000-00-0002', 'DEP01', 'D001'),

    ('2023-07-01', 'Spinal Pain', '2023-07-12', '000-00-0001', 'DEP03', 'D003');