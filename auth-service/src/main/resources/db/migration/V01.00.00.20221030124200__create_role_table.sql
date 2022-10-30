CREATE TABLE role_table
(
    id character varying(255) NOT NULL,
    role_name varchar (256)
);

ALTER TABLE role_table ADD CONSTRAINT role_table_pk PRIMARY KEY (id);