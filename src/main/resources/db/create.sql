SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS specials (
    id int PRIMARY KEY auto_increment,
    name VARCHAR,
    year INTEGER,
    comicId INTEGER,
    country VARCHAR,
    language VARCHAR,
    description VARCHAR
);