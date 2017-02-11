/*CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE TYPE degree_admin AS ENUM ('1', '2', '3');

*/

DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users (
  uniqueId       UUID        NOT NULL DEFAULT uuid_generate_v4() PRIMARY KEY,
  firstname      VARCHAR(20) NOT NULL
    CONSTRAINT first_name_check CHECK (firstname ~* '^[a-zA-Z]{2-20}$'),
  lastname       VARCHAR(30) NOT NULL
    CONSTRAINT second_name_check CHECK (lastname ~* '^[a-zA-Z]{2-30}$'),
  birthday       DATE                 DEFAULT NULL,
  phonenumber    NUMERIC(15) NOT NULL,
  datereg        TIMESTAMP   NOT NULL DEFAULT now(),

  useremail      VARCHAR(80) NOT NULL UNIQUE
    CONSTRAINT username_check CHECK (useremail ~* '^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$'),
  hashpassword   BYTEA       NOT NULL,
  encryptionsalt BYTEA       NOT NULL,
  accesstoken    BYTEA                DEFAULT NULL
);


DROP TABLE IF EXISTS admins CASCADE;
CREATE TABLE admins (
  uniqueId        UUID         NOT NULL DEFAULT uuid_generate_v4() PRIMARY KEY,
  id_user         UUID         NOT NULL REFERENCES users (uniqueId),
  degree          degree_admin NOT NULL,
  roledescription TEXT         NOT NULL
);

DROP TABLE IF EXISTS sendemail CASCADE;
CREATE TABLE sendemail (
  uniqueId UUID           NOT NULL DEFAULT uuid_generate_v4() PRIMARY KEY,
  id_user  UUID           NOT NULL REFERENCES users (uniqueId),
  message  VARCHAR(10000) NOT NULL,
  status   BOOLEAN        NOT NULL DEFAULT FALSE
);

DROP TABLE IF EXISTS categories CASCADE;
CREATE TABLE categories (
  uniqueId    UUID        NOT NULL DEFAULT uuid_generate_v4() PRIMARY KEY,
  name        VARCHAR(30) NOT NULL,
  description TEXT                 DEFAULT NULL
);

DROP TABLE IF EXISTS subcategories CASCADE;
CREATE TABLE subcategories (
  uniqueId       UUID        NOT NULL DEFAULT uuid_generate_v4() PRIMARY KEY,
  name           VARCHAR(30) NOT NULL,
  description    TEXT                 DEFAULT NULL,
  id_subcategory UUID        NOT NULL REFERENCES categories (uniqueId)
);


DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (
  uniqueId    UUID        NOT NULL DEFAULT uuid_generate_v4() PRIMARY KEY,
  name        VARCHAR(30) NOT NULL,
  photo       TEXT,
  description TEXT        NOT NULL,
  price       INTEGER     NOT NULL,
  instock     BOOLEAN     NOT NULL DEFAULT FALSE,
  dateadded   TIMESTAMP   NOT NULL DEFAULT now(),
  id_subcategory UUID REFERENCES subcategories (uniqueId)
);

DROP TABLE IF EXISTS basket CASCADE;
CREATE TABLE basket (
  uniqueId     UUID    NOT NULL DEFAULT uuid_generate_v4() PRIMARY KEY,
  id_user      UUID REFERENCES users (uniqueId),
  id_product   UUID REFERENCES products (uniqueId),
  count        INTEGER NOT NULL,
  confirmation BOOLEAN NOT NULL DEFAULT FALSE
);