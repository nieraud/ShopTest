INSERT INTO users
VALUES (DEFAULT,
        'Inna',
        'Rudenko',
        'Wed Sep 24 00:00:00 EEST 1997',
        380969635486,
        DEFAULT,
        'inusinka20@gmail.com',
        '$2a$12$L5K9h1B9bRFy6hfy29j7PuTl86xqonss5zpw628lJ3N04tcZZlw.i',
        '$2a$12$L5K9h1B9bRFy6hfy29j7Pu',
        DEFAULT);

CREATE TABLE test (
  id   UUID        NOT NULL DEFAULT uuid_generate_v4(),
  date DATE DEFAULT NULL
);

DELETE FROM users;

SELECT * from users;

drop TABLE test;

INSERT INTO test VALUES (DEFAULT , '1997-09-24');


