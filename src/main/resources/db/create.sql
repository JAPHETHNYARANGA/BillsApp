SET MODE PostgreSQL;

CREATE TABLE if NOT EXISTS bills(
    id int PRIMARY KEY auto_increment,
    billName VARCHAR,
    amount INTEGER,
    billEntryDate timestamp,
    dueDate BIGINT
)

CREATE TABLE if NOT EXISTS users(
    id int PRIMARY KEY auto_increment,
    password VARCHAR,
    confirmPassword VARCHAR
)