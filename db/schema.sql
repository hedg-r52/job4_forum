CREATE TABLE posts (
    id SERIAL PRIMARY KEY,
    name VARCHAR(2000),
    description text,
    created timestamp without time zone not null default now()
);