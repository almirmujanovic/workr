INSERT INTO users (username, password, role)
SELECT 'admin', '$2a$10$/8FJ8upHL8g3b8K6wr4AuOKPJu9eEvVwq6JC5fSeLjOCA0r/g7ouW', 'ADMIN'
    WHERE NOT EXISTS (SELECT 1 FROM users WHERE username = 'admin');

INSERT INTO users (username, password, role)
SELECT 'user', '$2a$10$lv7boL6MSPmxqOIefuLjveujGruA3CEnM1BEjs983vfpdJn5x6oNi', 'USER'
    WHERE NOT EXISTS (SELECT 1 FROM users WHERE username = 'user');
