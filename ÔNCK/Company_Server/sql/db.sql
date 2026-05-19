INSERT INTO companies (company_id, name, industry) VALUES
                                                       ('C001', 'FPT Software',            'Công nghệ thông tin'),
                                                       ('C002', 'VNG Corporation',         'Công nghệ thông tin'),
                                                       ('C003', 'Vingroup',                'Tập đoàn đa ngành'),
                                                       ('C004', 'Techcombank',             'Tài chính - Ngân hàng'),
                                                       ('C005', 'Grab Vietnam',            'Công nghệ - Vận tải'),
                                                       ('C006', 'Momo',                    'Fintech'),
                                                       ('C007', 'Tiki Corporation',        'Thương mại điện tử'),
                                                       ('C008', 'VietinBank',              'Tài chính - Ngân hàng'),
                                                       ('C009', 'Viettel Digital',         'Viễn thông - Công nghệ'),
                                                       ('C010', 'Shopee Vietnam',          'Thương mại điện tử');


INSERT INTO skills (skill_id, name) VALUES
                                        ('SK001', 'Java'),
                                        ('SK002', 'Python'),
                                        ('SK003', 'JavaScript'),
                                        ('SK004', 'TypeScript'),
                                        ('SK005', 'React'),
                                        ('SK006', 'Spring Boot'),
                                        ('SK007', 'Node.js'),
                                        ('SK008', 'SQL'),
                                        ('SK009', 'MongoDB'),
                                        ('SK010', 'Docker'),
                                        ('SK011', 'Kubernetes'),
                                        ('SK012', 'AWS'),
                                        ('SK013', 'Git'),
                                        ('SK014', 'C++'),
                                        ('SK015', 'Data Analysis'),
                                        ('SK016', 'Machine Learning'),
                                        ('SK017', 'Project Management'),
                                        ('SK018', 'UI/UX Design'),
                                        ('SK019', 'Figma'),
                                        ('SK020', 'Linux');


INSERT INTO jobs (job_id, title, description, salary, status, company_id) VALUES
                                                                              ('J001', 'Backend Developer Java',
                                                                               'Phát triển và bảo trì hệ thống backend sử dụng Java Spring Boot, REST API.',
                                                                               25000000, 'OPEN', 'C001'),

                                                                              ('J002', 'Frontend Developer React',
                                                                               'Xây dựng giao diện web hiện đại với React, TypeScript và Tailwind CSS.',
                                                                               22000000, 'OPEN', 'C001'),

                                                                              ('J003', 'Senior Python Developer',
                                                                               'Phát triển hệ thống dữ liệu lớn, machine learning pipeline với Python.',
                                                                               35000000, 'OPEN', 'C002'),

                                                                              ('J004', 'DevOps Engineer',
                                                                               'Quản lý hạ tầng cloud, CI/CD pipeline, Docker, Kubernetes trên AWS.',
                                                                               40000000, 'OPEN', 'C002'),

                                                                              ('J005', 'Mobile Developer (React Native)',
                                                                               'Phát triển ứng dụng di động cross-platform cho iOS và Android.',
                                                                               28000000, 'CLOSED', 'C003'),

                                                                              ('J006', 'Data Analyst',
                                                                               'Phân tích dữ liệu kinh doanh, xây dựng dashboard báo cáo và dự báo.',
                                                                               20000000, 'OPEN', 'C004'),

                                                                              ('J007', 'Full Stack Developer',
                                                                               'Phát triển toàn bộ hệ thống từ backend Node.js đến frontend React.',
                                                                               30000000, 'OPEN', 'C005'),

                                                                              ('J008', 'Machine Learning Engineer',
                                                                               'Nghiên cứu và triển khai các mô hình ML/AI vào sản phẩm thực tế.',
                                                                               45000000, 'OPEN', 'C006'),

                                                                              ('J009', 'UI/UX Designer',
                                                                               'Thiết kế giao diện sản phẩm số, nghiên cứu người dùng và tạo prototype.',
                                                                               18000000, 'OPEN', 'C007'),

                                                                              ('J010', 'Software Architect',
                                                                               'Thiết kế kiến trúc hệ thống phân tán, microservices, định hướng kỹ thuật.',
                                                                               55000000, 'OPEN', 'C008'),

                                                                              ('J011', 'Backend Developer Node.js',
                                                                               'Xây dựng API và hệ thống realtime với Node.js, MongoDB.',
                                                                               23000000, 'CLOSED', 'C009'),

                                                                              ('J012', 'Junior Java Developer',
                                                                               'Hỗ trợ phát triển ứng dụng web Java, học hỏi công nghệ mới trong môi trường Agile.',
                                                                               12000000, 'OPEN', 'C010'),

                                                                              ('J013', 'Project Manager IT',
                                                                               'Quản lý dự án phần mềm theo Agile/Scrum, điều phối team phát triển.',
                                                                               38000000, 'OPEN', 'C003'),

                                                                              ('J014', 'Database Administrator',
                                                                               'Quản trị CSDL MySQL, MariaDB, tối ưu query và đảm bảo hiệu năng hệ thống.',
                                                                               27000000, 'OPEN', 'C009'),

                                                                              ('J015', 'Cybersecurity Engineer',
                                                                               'Bảo mật hệ thống, kiểm thử xâm nhập, phân tích lỗ hổng bảo mật.',
                                                                               42000000, 'OPEN', 'C010');


INSERT INTO jobs_skills (job_id, skill_id) VALUES
-- J001 Backend Java
('J001', 'SK001'), ('J001', 'SK006'), ('J001', 'SK008'), ('J001', 'SK013'),
-- J002 Frontend React
('J002', 'SK003'), ('J002', 'SK004'), ('J002', 'SK005'), ('J002', 'SK013'),
-- J003 Python
('J003', 'SK002'), ('J003', 'SK016'), ('J003', 'SK015'), ('J003', 'SK013'),
-- J004 DevOps
('J004', 'SK010'), ('J004', 'SK011'), ('J004', 'SK012'), ('J004', 'SK020'),
-- J005 Mobile
('J005', 'SK003'), ('J005', 'SK005'), ('J005', 'SK013'),
-- J006 Data Analyst
('J006', 'SK015'), ('J006', 'SK008'), ('J006', 'SK002'),
-- J007 Full Stack
('J007', 'SK007'), ('J007', 'SK005'), ('J007', 'SK009'), ('J007', 'SK013'),
-- J008 ML Engineer
('J008', 'SK002'), ('J008', 'SK016'), ('J008', 'SK015'), ('J008', 'SK012'),
-- J009 UI/UX
('J009', 'SK018'), ('J009', 'SK019'),
-- J010 Architect
('J010', 'SK001'), ('J010', 'SK006'), ('J010', 'SK010'), ('J010', 'SK011'), ('J010', 'SK012'),
-- J011 Node.js
('J011', 'SK007'), ('J011', 'SK009'), ('J011', 'SK013'),
-- J012 Junior Java
('J012', 'SK001'), ('J012', 'SK008'), ('J012', 'SK013'),
-- J013 PM
('J013', 'SK017'),
-- J014 DBA
('J014', 'SK008'), ('J014', 'SK020'), ('J014', 'SK013'),
-- J015 Security
('J015', 'SK020'), ('J015', 'SK012'), ('J015', 'SK014');


INSERT INTO candidates (cand_id, name, email, experience) VALUES
                                                              ('CA001', 'Nguyen Van An',      'an.nguyen@gmail.com',       3),
                                                              ('CA002', 'Tran Thi Bich',      'bich.tran@gmail.com',       5),
                                                              ('CA003', 'Le Van Cuong',       'cuong.le@yahoo.com',        7),
                                                              ('CA004', 'Pham Thi Dung',      'dung.pham@outlook.com',     2),
                                                              ('CA005', 'Hoang Van Em',       'em.hoang@gmail.com',        10),
                                                              ('CA006', 'Nguyen Thi Phuong',  'phuong.nguyen@gmail.com',   1),
                                                              ('CA007', 'Vo Thanh Hung',      'hung.vo@gmail.com',         6),
                                                              ('CA008', 'Do Thi Lan',         'lan.do@gmail.com',          4),
                                                              ('CA009', 'Bui Van Minh',       'minh.bui@gmail.com',        8),
                                                              ('CA010', 'Dang Thi Ngoc',      'ngoc.dang@outlook.com',     0),
                                                              ('CA011', 'Trinh Van Phuc',     'phuc.trinh@gmail.com',      3),
                                                              ('CA012', 'Ly Thi Quynh',       'quynh.ly@gmail.com',        5),
                                                              ('CA013', 'Ngo Van Son',        'son.ngo@yahoo.com',         9),
                                                              ('CA014', 'Ha Thi Thu',         'thu.ha@gmail.com',          2),
                                                              ('CA015', 'Dinh Van Uy',        'uy.dinh@gmail.com',         11);

INSERT INTO candidates_skills (candidate_id, skill_id) VALUES
-- CA001 (Java dev, 3yr)
('CA001', 'SK001'), ('CA001', 'SK006'), ('CA001', 'SK008'), ('CA001', 'SK013'),
-- CA002 (Python/ML, 5yr)
('CA002', 'SK002'), ('CA002', 'SK016'), ('CA002', 'SK015'), ('CA002', 'SK013'),
-- CA003 (DevOps senior, 7yr)
('CA003', 'SK010'), ('CA003', 'SK011'), ('CA003', 'SK012'), ('CA003', 'SK020'), ('CA003', 'SK013'),
-- CA004 (Junior React, 2yr)
('CA004', 'SK003'), ('CA004', 'SK005'), ('CA004', 'SK013'),
-- CA005 (Architect, 10yr)
('CA005', 'SK001'), ('CA005', 'SK006'), ('CA005', 'SK010'), ('CA005', 'SK011'), ('CA005', 'SK012'),
-- CA006 (Fresher JS, 1yr)
('CA006', 'SK003'), ('CA006', 'SK004'), ('CA006', 'SK013'),
-- CA007 (Full Stack, 6yr)
('CA007', 'SK007'), ('CA007', 'SK005'), ('CA007', 'SK009'), ('CA007', 'SK013'), ('CA007', 'SK002'),
-- CA008 (Data Analyst, 4yr)
('CA008', 'SK015'), ('CA008', 'SK008'), ('CA008', 'SK002'), ('CA008', 'SK013'),
-- CA009 (ML Engineer, 8yr)
('CA009', 'SK002'), ('CA009', 'SK016'), ('CA009', 'SK015'), ('CA009', 'SK012'), ('CA009', 'SK013'),
-- CA010 (Fresher Designer, 0yr)
('CA010', 'SK018'), ('CA010', 'SK019'),
-- CA011 (TypeScript/React, 3yr)
('CA011', 'SK003'), ('CA011', 'SK004'), ('CA011', 'SK005'), ('CA011', 'SK013'),
-- CA012 (Node.js Backend, 5yr)
('CA012', 'SK007'), ('CA012', 'SK009'), ('CA012', 'SK013'), ('CA012', 'SK008'),
-- CA013 (Senior Java, 9yr)
('CA013', 'SK001'), ('CA013', 'SK006'), ('CA013', 'SK010'), ('CA013', 'SK008'), ('CA013', 'SK013'),
-- CA014 (Junior Python, 2yr)
('CA014', 'SK002'), ('CA014', 'SK008'), ('CA014', 'SK013'),
-- CA015 (DBA/DevOps, 11yr)
('CA015', 'SK008'), ('CA015', 'SK020'), ('CA015', 'SK010'), ('CA015', 'SK012'), ('CA015', 'SK013');


INSERT INTO applications (candidate_id, job_id, applied_date, status) VALUES
-- CA001 ứng tuyển Java jobs
('CA001', 'J001', '2024-11-01', 'ACCEPTED'),
('CA001', 'J012', '2024-11-15', 'REJECTED'),

-- CA002 ứng tuyển Python/ML
('CA002', 'J003', '2024-10-20', 'ACCEPTED'),
('CA002', 'J008', '2024-11-05', 'PENDING'),
('CA002', 'J006', '2024-11-10', 'ACCEPTED'),

-- CA003 ứng tuyển DevOps
('CA003', 'J004', '2024-09-15', 'ACCEPTED'),

-- CA004 ứng tuyển Frontend
('CA004', 'J002', '2024-11-20', 'PENDING'),
('CA004', 'J005', '2024-10-01', 'REJECTED'),

-- CA005 ứng tuyển Architect / Senior
('CA005', 'J010', '2024-10-10', 'ACCEPTED'),
('CA005', 'J001', '2024-11-01', 'REJECTED'),

-- CA006 Fresher ứng tuyển nhiều nơi
('CA006', 'J002', '2024-11-22', 'PENDING'),
('CA006', 'J007', '2024-11-22', 'PENDING'),
('CA006', 'J012', '2024-11-23', 'PENDING'),

-- CA007 Full Stack
('CA007', 'J007', '2024-10-05', 'ACCEPTED'),
('CA007', 'J011', '2024-09-20', 'REJECTED'),

-- CA008 Data Analyst
('CA008', 'J006', '2024-11-12', 'ACCEPTED'),
('CA008', 'J003', '2024-11-18', 'PENDING'),

-- CA009 ML Engineer senior
('CA009', 'J008', '2024-10-25', 'ACCEPTED'),
('CA009', 'J003', '2024-10-01', 'REJECTED'),

-- CA010 Fresher Designer
('CA010', 'J009', '2024-11-25', 'PENDING'),

-- CA011 React/TS
('CA011', 'J002', '2024-11-08', 'ACCEPTED'),
('CA011', 'J005', '2024-10-15', 'REJECTED'),

-- CA012 Node.js
('CA012', 'J011', '2024-09-10', 'ACCEPTED'),
('CA012', 'J007', '2024-11-01', 'PENDING'),

-- CA013 Senior Java / Architect
('CA013', 'J001', '2024-10-05', 'REJECTED'),
('CA013', 'J010', '2024-10-20', 'ACCEPTED'),

-- CA014 Junior Python
('CA014', 'J006', '2024-11-28', 'PENDING'),
('CA014', 'J012', '2024-11-28', 'PENDING'),

-- CA015 DBA / DevOps senior
('CA015', 'J014', '2024-10-30', 'ACCEPTED'),
('CA015', 'J004', '2024-11-05', 'PENDING'),
('CA015', 'J015', '2024-11-10', 'PENDING');
