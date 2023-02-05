CREATE TABLE game_info
(
    name    varchar(256),
    url varchar(256),
    type varchar(50),
    image        text,
    id           varchar(64)
);

ALTER TABLE game_info
    ADD CONSTRAINT game_info_pk PRIMARY KEY (id);