INSERT INTO institutions VALUES
                             ('I001', 'MIT', 'USA'),
                             ('I002', 'Stanford University', 'USA'),
                             ('I003', 'University of Tokyo', 'Japan'),
                             ('I004', 'Industrial University of Ho Chi Minh City', 'Vietnam');

-- authors
INSERT INTO authors VALUES
                        ('A001', 'Nguyen Van A', 'vana@gmail.com', 'I004'),
                        ('A002', 'Tran Thi B', 'thib@gmail.com', 'I001'),
                        ('A003', 'Le Van C', 'vanc@gmail.com', 'I002'),
                        ('A004', 'Pham Thi D', 'thid@gmail.com', 'I003');

-- articles
INSERT INTO articles
(article_id, title, year, reviewSuggestion, author_id)
VALUES
    ('AR001', 'AI in Healthcare', 2024, 'ACCEPT', 'A001'),

    ('AR002', 'Cloud Computing Systems', 2023, 'REJECT', 'A002'),

    ('AR003', 'Machine Learning Trends', 2025, 'ACCEPT', 'A003'),

    ('AR004', 'Distributed Java Applications', 2024, 'ACCEPT', 'A001'),

    ('AR005', 'Blockchain Security', 2022, 'REJECT', 'A004');

-- reviews
INSERT INTO reviews
(review_id, comment, reviewer, review_days, review_date, article_id)
VALUES

    ('R001',
     'Excellent research with practical applications',
     'Dr. Smith',
     5,
     '2025-05-01',
     'AR001'),

    ('R002',
     'Need more experimental results',
     'Prof. David',
     8,
     '2025-05-02',
     'AR001'),

    ('R003',
     'The methodology is unclear',
     'Dr. Brown',
     12,
     '2025-05-03',
     'AR002'),

    ('R004',
     'Very innovative topic',
     'Prof. Wilson',
     4,
     '2025-05-05',
     'AR003'),

    ('R005',
     'Good implementation and testing',
     'Dr. John',
     6,
     '2025-05-06',
     'AR004'),

    ('R006',
     'Security analysis should be improved',
     'Dr. Lee',
     10,
     '2025-05-07',
     'AR005');