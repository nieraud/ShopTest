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

DELETE FROM admins WHERE uniqueid = '3a33af2c-9349-4441-9f16-f17b6b27db73';

SELECT * from users;

SELECT  * FROM admins;

drop TABLE test;

INSERT INTO admins VALUES (DEFAULT , '61b39f1b-77ae-4cf8-94f4-4b6c2143a7f7', 3, 'Takes orders, responds to customer''s questions.');

SELECT admins.uniqueid, admins.id_user, admins.degree,
admins.roledescription, users.firstname, users.lastname, users.birthday,
users.phonenumber, users.datereg, users.useremail
FROM admins
INNER JOIN users ON admins.id_user = users.uniqueid
WHERE admins.uniqueid = '25faaa9f-7be9-4c5a-aded-dc6310517a35';


SELECT admins.uniqueid, admins.id_user, admins.degree,
admins.roledescription, users.firstname, users.lastname, users.birthday,
users.phonenumber, users.datereg, users.useremail
FROM admins
INNER JOIN users ON admins.id_user = users.uniqueid
WHERE admins.uniqueid = '25faaa9f-7be9-4c5a-aded-dc6310517a35';