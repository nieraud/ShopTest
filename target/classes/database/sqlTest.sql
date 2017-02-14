INSERT INTO users
VALUES (DEFAULT,
        'Inna',
        'Rudenko',
        '1997-09-24',
        380969635486,
        DEFAULT,
        'inusinka20@gmail.com',
        '$2a$12$L5K9h1B9bRFy6hfy29j7PuTl86xqonss5zpw628lJ3N04tcZZlw.i',
        '$2a$12$L5K9h1B9bRFy6hfy29j7Pu',
        DEFAULT);

CREATE TABLE test (
  id   UUID        NOT NULL DEFAULT uuid_generate_v4(),
  firstname      VARCHAR(20) NOT NULL
    CONSTRAINT first_name_check CHECK (firstname ~* '^[A-Za-z]{1,20}$'),
  name VARCHAR(80) NOT NULL UNIQUE
    CONSTRAINT username_check CHECK (name ~*
                                     '^([a-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$')
);

DELETE from users where uniqueid ='ce79be58-6213-432d-ae11-55e56a4609ad';

INSERT INTO test
VALUES (DEFAULT ,'i9L','ijj@gmail.com');

select * from users;