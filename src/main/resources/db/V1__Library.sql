-- ALTER TABLE books ALTER COLUMN name_ru SET DATA TYPE character varying(50) COLLATE "ru_RU.UTF-8";

CREATE DATABASE lib
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'ru_RU.UTF-8'
    LC_CTYPE = 'ru_RU.UTF-8'
    CONNECTION LIMIT = -1
    TEMPLATE template0;

---------------CREATE TABLE READERS---------------------------------

CREATE TABLE IF NOT EXISTS readers
(
    id_reader   SERIAL PRIMARY KEY NOT NULL,
    name        VARCHAR(30)        NOT NULL,
    surname     VARCHAR(30)        NOT NULL,
    middle_name VARCHAR(30),
    passport    VARCHAR(30) UNIQUE,
    birth_date  DATE               NOT NULL,
    email       VARCHAR(50)        NOT NULL,
    address     VARCHAR(50)
);

----------------CREATE TABLE BOOKS-------------------------------------------------------

CREATE TABLE IF NOT EXISTS books
(
    id_book       SERIAL PRIMARY KEY NOT NULL,
    name_ru       VARCHAR(50)        NOT NULL,
    name_origin   VARCHAR(50) DEFAULT NULL,
    publish_date  DATE,
    price         NUMERIC(10, 2)     NOT NULL,
    price_per_day NUMERIC(10, 2)     NOT NULL,
    quantity      INTEGER            NOT NULL,
    reg_date      DATE               NOT NULL,
    page_number   INTEGER
);

----------------CREATE TABLE AUTHORS-----------------------------------------------------

CREATE TABLE IF NOT EXISTS authors
(
    id_author SERIAL PRIMARY KEY NOT NULL,
    name      VARCHAR(30)        NOT NULL,
    photo     VARCHAR(200),
    img       bytea
);

-----------------CREATE BOOK_AUTHOR (MANY TO MANY)----------------------------------------

CREATE TABLE IF NOT EXISTS book_author
(
    id_book   INTEGER REFERENCES books (id_book),
    id_author INTEGER REFERENCES authors (id_author),

    CONSTRAINT book_author_pkey PRIMARY KEY (id_book, id_author)
);

----------------CREATE TABLE GENRES------------------------------------------------------

CREATE TABLE IF NOT EXISTS genres
(
    id_genre   SERIAL PRIMARY KEY NOT NULL,
    name_genre VARCHAR(50)        NOT NULL
);

----------------CREATE BOOK_GENRES (MANY TO MANY)-----------------------------------------

CREATE TABLE IF NOT EXISTS book_genre
(
    id_book  INTEGER REFERENCES books (id_book),
    id_genre INTEGER REFERENCES genres (id_genre),

    CONSTRAINT book_genre_pkey Primary key (id_book, id_genre)
);

----------------CREATE TABLE BOOK_IMG----------------------------------------------------

CREATE TABLE IF NOT EXISTS book_img
(
    id_image SERIAL PRIMARY KEY NOT NULL,
    name     VARCHAR(200),
    id_book  INTEGER            NOT NULL,
    img      bytea,
    CONSTRAINT fk_book_id FOREIGN KEY (id_book) REFERENCES books (id_book)
);

----------------CREATE TABLE COPY-----------------------------------------------------

CREATE TABLE IF NOT EXISTS book_copy
(
    id_copy SERIAL PRIMARY KEY NOT NULL,
    id_book INTEGER,
    status  VARCHAR(15),
    CONSTRAINT fk_book_copy FOREIGN KEY (id_book) REFERENCES books (id_book)
);

-----------------CREATE TABLE COPY_IMG (FOR DAMAGED)------------------------------------

CREATE TABLE IF NOT EXISTS copy_img
(
    id_image SERIAL PRIMARY KEY NOT NULL,
    name     VARCHAR(200),
    id_copy  INTEGER,
    img      bytea,
    CONSTRAINT fk_book_img_COPY FOREIGN KEY (id_copy) REFERENCES book_copy (id_copy)
);

----------------CREATE TABLE NOTES (FOR NOTES)-------------------------------------------

CREATE TABLE IF NOT EXISTS notes
(
    id_note SERIAL PRIMARY KEY NOT NULL,
    note    VARCHAR(150),
    id_copy INTEGER,
    CONSTRAINT fk_notes FOREIGN KEY (id_copy) REFERENCES book_copy (id_copy)
);

--------------------CREATE TABLE ORDERS--------------------------------------------------

CREATE TABLE IF NOT EXISTS orders
(
    id_order      SERIAL PRIMARY KEY NOT NULL,
    id_reader     INTEGER            NOT NULL,
    issue_date    DATE               NOT NULL,
    return_date   DATE               NOT NULL,
    expire_date   DATE,
    advance_price NUMERIC(10, 2)     NOT NULL,
    finish_price  NUMERIC(10, 2),
    discount      INTEGER,
    fine          NUMERIC(10, 2),
    status        VARCHAR(15),
    CONSTRAINT fk_id_reader FOREIGN KEY (id_reader) REFERENCES readers (id_reader)
);

--------------------CREATE TABLE ORDER_COPY-----------------------------------------------

CREATE TABLE IF NOT EXISTS order_copy
(
    id_copy  INTEGER REFERENCES book_copy (id_copy),
    id_order INTEGER REFERENCES orders (id_order),

    CONSTRAINT order_copy_pkey Primary key (id_copy, id_order)
);

------------------------------------------------------------------------------------------
