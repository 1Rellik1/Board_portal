CREATE TABLE user_table
(
    user_name    varchar(256),
    trust_rating integer,
    email        varchar(256),
    id           varchar(64)
);

ALTER TABLE user_table
    ADD CONSTRAINT user_table_pk PRIMARY KEY (id);
ALTER TABLE user_table
    ADD CONSTRAINT unique_email UNIQUE (email);
ALTER TABLE user_table
    ADD CONSTRAINT unique_login UNIQUE (user_name);