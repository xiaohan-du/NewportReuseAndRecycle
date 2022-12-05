INSERT INTO users (username, password, role, enabled)
VALUES ('nam',
        '$2a$10$XptfskLsT1l/bRTLRiiCgejHqOpgXFreUnNUa35gJdCr2v2QbVFzu',
        'user', 1),
        ('admin',
        '$2a$04$VwoWUzjRYC3eodssFgcO6.HLQXjzOj4p33y4Afmp4hdrONYG4kut2',
        'admin', 1);


INSERT INTO listing (title, description, price, user_id)
VALUES ('test listing 1', 'This is text listing 1 content', 3.99, 12345),
       ('test listing 2', 'This is text listing 2 content', 13.99, 12345),
       ('test listing 3', 'This is text listing 3 content', 200.00, 12345),
       ('test listing 4', 'This is text listing 4 content', 4.50, 12345);