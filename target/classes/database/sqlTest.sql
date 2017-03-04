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
  id   UUID NOT NULL DEFAULT uuid_generate_v4(),
  date DATE          DEFAULT NULL
);

DELETE FROM admins
WHERE uniqueid = '3a33af2c-9349-4441-9f16-f17b6b27db73';


SELECT *
FROM users;

SELECT *
FROM admins;

SELECT *
FROM categories;

UPDATE categories
SET (name, description)
= ('Accessories for laptops', 'This category contains accessories for laptops')
WHERE uniqueid = '5dde1350-2354-462d-aea5-4c77c3eae2b1';

INSERT INTO admins VALUES (DEFAULT, 'a663f44f-298c-43f2-850f-e06cafb28aa0', 4, 'test');


SELECT *
FROM users;
SELECT *
FROM admins;

SELECT *
FROM categories;

SELECT *
FROM subcategories;


SELECT *
FROM products;

SELECT *
FROM blacklist;




SELECT
  blacklist.uniqueid,
  blacklist.notes,
  blacklist.dateadded,
  blacklist.id_user,
  users.firstname,
  users.lastname,
  blacklist.id_adminadded
FROM blacklist
  INNER JOIN users ON blacklist.id_user = users.uniqueid
  INNER JOIN admins ON blacklist.id_adminadded = admins.uniqueid
WHERE blacklist.uniqueid = 'ed96f4df-bf7a-4967-8a6f-a0f3e9e29127';
