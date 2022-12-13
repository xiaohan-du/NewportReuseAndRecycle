INSERT INTO users (username, password, role, enabled)
VALUES ('user',
        '$2a$12$k5uDRF2hMLDvrWoeHbdD0uEM/gcdECX.Vs5inhhr3dFkR7OeRvP2m',
        'user', 1),
        ('admin',
        '$2a$04$VwoWUzjRYC3eodssFgcO6.HLQXjzOj4p33y4Afmp4hdrONYG4kut2',
        'admin', 1);


INSERT INTO listing (title, description, price, image_url, user_id, category, latitude, longitude)
VALUES ('test listing 1', 'This is text listing 1 content', 3.99, 'http://www.clker.com/cliparts/f/Z/G/4/h/Q/no-image-available-hi.png', 1, 'food', 51.4902713, -3.1766767),
       ('test listing 2', 'This is text listing 2 content', 13.99, 'http://www.clker.com/cliparts/f/Z/G/4/h/Q/no-image-available-hi.png', 1, 'food', 51.4902713, -3.1766767),
       ('test listing 3', 'This is text listing 3 content', 200.00, 'http://www.clker.com/cliparts/f/Z/G/4/h/Q/no-image-available-hi.png', 1, 'electronics', 51.4902713, -3.1766767),
       ('test listing 4', 'This is text listing 4 content', 4.50, 'http://www.clker.com/cliparts/f/Z/G/4/h/Q/no-image-available-hi.png', 1, 'furniture', 51.4902713, -3.1766767);

INSERT INTO category (category)
VALUES ('food'),
       ('electronics'),
       ('furniture');


INSERT INTO report (user_id, listing_id, reason)
VALUES(1,2,'This is inappropriate.')