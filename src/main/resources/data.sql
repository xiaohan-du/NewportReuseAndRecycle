INSERT INTO `users` (`username`,`password`,`role`,`enabled`)
VALUES ('nam',
        '$2a$10$XptfskLsT1l/bRTLRiiCgejHqOpgXFreUnNUa35gJdCr2v2QbVFzu',
        'user', 1);

INSERT INTO `users` (`username`,`password`,`role`,`enabled`)
VALUES ('admin',
        '$2a$04$VwoWUzjRYC3eodssFgcO6.HLQXjzOj4p33y4Afmp4hdrONYG4kut2',
        'admin', 1);


INSERT INTO listing (title, content, approved)
VALUES ('test listing 1', 'This is text listing 1 content', 1),
       ('test listing 2', 'This is text listing 2 content', 1);