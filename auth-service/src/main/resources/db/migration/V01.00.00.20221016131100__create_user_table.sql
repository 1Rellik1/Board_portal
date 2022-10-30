CREATE TABLE user_table (
    user_name varchar (256),
    password varchar,
    email varchar (256),
    id varchar (64),
    active boolean NOT NULL
);

ALTER TABLE user_table ADD CONSTRAINT user_table_pk PRIMARY KEY (id);
ALTER TABLE user_table ADD CONSTRAINT unique_email UNIQUE (email);
ALTER TABLE user_table ADD CONSTRAINT unique_login UNIQUE (user_name);