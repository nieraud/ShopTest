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

INSERT INTO categories VALUES (DEFAULT, 'ololo', 'viv;v; r');

INSERT INTO admins VALUES (DEFAULT, '28af5675-e563-431a-b565-91965e54fe09', 1, 'Owner');

SELECT
  admins.uniqueid,
  admins.id_user,
  admins.degree,
  admins.roledescription,
  users.firstname,
  users.lastname,
  users.birthday,
  users.phonenumber,
  users.datereg,
  users.useremail
FROM admins
  INNER JOIN users ON admins.id_user = users.uniqueid
WHERE admins.uniqueid = '25faaa9f-7be9-4c5a-aded-dc6310517a35';

SELECT
  subcategories.uniqueid,
  subcategories.name,
  subcategories.description,
  subcategories.id_category,
  categories.name,
  categories.description
FROM subcategories
  INNER JOIN categories ON subcategories.id_category = categories.uniqueid
WHERE subcategories.uniqueid = '43643d3f-b454-4197-9bbe-622ea16813ed';


SELECT
  admins.uniqueid,
  admins.id_user,
  admins.degree,
  admins.roledescription,
  users.firstname,
  users.lastname,
  users.birthday,
  users.phonenumber,
  users.datereg,
  users.useremail
FROM admins
  INNER JOIN users ON admins.id_user = users.uniqueid
WHERE admins.uniqueid = '25faaa9f-7be9-4c5a-aded-dc6310517a35';

DELETE FROM admins
WHERE degree = 3;

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


