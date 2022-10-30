CREATE TABLE user_to_role
(
    user_id character varying(255) NOT NULL,
    role_id character varying(255) NOT NULL
);

ALTER TABLE user_to_role
    ADD CONSTRAINT user_to_role_user_fk FOREIGN KEY  (user_id) REFERENCES user_table(id);
ALTER TABLE user_to_role
    ADD CONSTRAINT user_to_role_role_fk FOREIGN KEY  (role_id) REFERENCES role_table(id);