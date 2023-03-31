CREATE TABLE chance_card
(
    id               varchar(255),
    description      varchar(255) NOT NULL,
    money_difference bigint,
    step             integer,
    type             varchar(255) NOT NULL,
    CONSTRAINT chance_card_pkey PRIMARY KEY (id)
);

CREATE TABLE common_card
(
    id         varchar(255),
    image      text         not null,
    image_type varchar(50)  not null,
    price      bigint,
    card_type  varchar(255) NOT NULL,
    CONSTRAINT common_card_pkey PRIMARY KEY (id)
);

CREATE TABLE company_card
(
    id                varchar(255),
    collection_number integer      NOT NULL,
    price             bigint       NOT NULL,
    sale_price        bigint       NOT NULL,
    region            varchar(255) NOT NULL,
    star_price        bigint       NOT NULL,
    title             varchar(255) NOT NULL,
    common_card_id    varchar(255) NOT NULL,
    CONSTRAINT company_card_pkey PRIMARY KEY (id),
    CONSTRAINT company_card_common_crad_fk FOREIGN KEY (common_card_id) REFERENCES common_card (id)
);

CREATE TABLE card_state
(
    id           varchar(255),
    current_fine bigint,
    level        integer      NOT NULL,
    owner_name   varchar(255),
    card_id      varchar(255) NOT NULL,
    CONSTRAINT card_state_pkey PRIMARY KEY (id),
    CONSTRAINT card_state_car_fk FOREIGN KEY (card_id) REFERENCES company_card (id)
);


CREATE TABLE level_fine
(
    id    varchar(255),
    value bigint NOT NULL,
    CONSTRAINT level_fine_pkey PRIMARY KEY (id)
);


CREATE TABLE company_card_fines
(
    company_card_id varchar(255) NOT NULL,
    fines_id        varchar(255) NOT NULL,
    CONSTRAINT company_card_fines_level_fine_fk FOREIGN KEY (fines_id) REFERENCES level_fine (id),
    CONSTRAINT company_card_fines_company_card_fk FOREIGN KEY (company_card_id) REFERENCES company_card (id)
);

CREATE TABLE message
(
    id       varchar(255),
    content  varchar(255),
    receiver varchar(255),
    sender   varchar(255),
    type     varchar(255) NOT NULL,
    CONSTRAINT message_pkey PRIMARY KEY (id)
);

CREATE TABLE player
(
    id         varchar(255),
    balance    bigint       NOT NULL,
    colour     varchar(255),
    position   integer      NOT NULL,
    role       varchar(255) NOT NULL,
    status     varchar(255) NOT NULL,
    name       varchar(255) unique,
    session_id varchar(255),
    CONSTRAINT player_pkey PRIMARY KEY (id)
);

CREATE TABLE session
(
    id             varchar(255),
    current_player varchar(255),
    move_status    varchar(255),
    state          varchar(255) NOT NULL,
    CONSTRAINT session_pkey PRIMARY KEY (id)
);

Select * from game_info where string_to_array(game_info.style,',') && gameStyle and game_info.max_number_of_players >= maxNumberOfPlayers and string_to_array(game_info.game_type,',')  && gameType;