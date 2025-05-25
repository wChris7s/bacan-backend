CREATE SCHEMA bacan
CREATE TABLE bacan.country
(
    id         bigserial    NOT NULL,
    "name"     varchar(100) NOT NULL,
    phone_code varchar(10)  NOT NULL,
    lang_code  bpchar(2) NOT NULL,
    CONSTRAINT country_pk PRIMARY KEY (id)
);

CREATE TABLE bacan."role"
(
    id         bigserial   NOT NULL,
    "name"     varchar(20) NOT NULL,
    created_at timestamp   NOT NULL,
    updated_at timestamp   NOT NULL,
    enabled    bool        NOT NULL,
    CONSTRAINT role_pk PRIMARY KEY (id),
    CONSTRAINT role_unique UNIQUE (name)
);

CREATE TABLE bacan."user"
(
    document_id            varchar(20)  NOT NULL,
    "name"                 varchar(100) NOT NULL,
    lastname               varchar(100) NOT NULL,
    birth_date             date         NOT NULL,
    phone                  varchar(20)  NOT NULL,
    email                  varchar(254) NOT NULL,
    "password"             varchar(255) NOT NULL,
    profile_photo          bpchar(40) NOT NULL,
    created_at             timestamp    NOT NULL,
    updated_at             timestamp    NOT NULL,
    enabled                bool         NOT NULL,
    password_modified_date timestamp    NOT NULL,
    CONSTRAINT user_pk PRIMARY KEY (document_id)
);

CREATE TABLE bacan.permissions
(
    user_id varchar(20) NOT NULL,
    role_id int8        NOT NULL,
    CONSTRAINT permissions_pk PRIMARY KEY (user_id, role_id),
    CONSTRAINT permissions_role_fk FOREIGN KEY (role_id) REFERENCES bacan."role" (id),
    CONSTRAINT permissions_user_fk FOREIGN KEY (user_id) REFERENCES bacan."user" (document_id)
);

CREATE TABLE bacan.state
(
    id         bigserial    NOT NULL,
    "name"     varchar(100) NOT NULL,
    country_id int8         NOT NULL,
    CONSTRAINT state_pk PRIMARY KEY (id),
    CONSTRAINT state_country_fk FOREIGN KEY (country_id) REFERENCES bacan.country (id) ON DELETE CASCADE
);

CREATE TABLE bacan.province
(
    id       bigserial    NOT NULL,
    "name"   varchar(100) NOT NULL,
    state_id int8         NOT NULL,
    CONSTRAINT province_pk PRIMARY KEY (id),
    CONSTRAINT province_state_fk FOREIGN KEY (state_id) REFERENCES bacan.state (id) ON UPDATE CASCADE
);

CREATE TABLE bacan.district
(
    id          bigserial    NOT NULL,
    "name"      varchar(100) NOT NULL,
    province_id int8         NOT NULL,
    CONSTRAINT district_pk PRIMARY KEY (id),
    CONSTRAINT district_province_fk FOREIGN KEY (province_id) REFERENCES bacan.province (id) ON DELETE CASCADE
);

CREATE TABLE bacan.address
(
    id          uuid        NOT NULL,
    user_id     varchar(20) NOT NULL,
    country_id  int8        NOT NULL,
    state_id    int8        NOT NULL,
    province_id int8        NOT NULL,
    district_id int8        NOT NULL,
    street      varchar(255) NULL,
    postal_code varchar(20) NULL,
    "number"    varchar(20) NULL,
    reference   varchar(255) NULL,
    created_at  timestamp   NOT NULL,
    updated_at  timestamp   NOT NULL,
    CONSTRAINT address_pk PRIMARY KEY (id),
    CONSTRAINT address_country_fk FOREIGN KEY (country_id) REFERENCES bacan.country (id) ON DELETE CASCADE,
    CONSTRAINT address_district_fk FOREIGN KEY (district_id) REFERENCES bacan.district (id) ON DELETE CASCADE,
    CONSTRAINT address_province_fk FOREIGN KEY (province_id) REFERENCES bacan.province (id) ON DELETE CASCADE,
    CONSTRAINT address_state_fk FOREIGN KEY (state_id) REFERENCES bacan.state (id) ON DELETE CASCADE,
    CONSTRAINT address_user_fk FOREIGN KEY (user_id) REFERENCES bacan."user" (document_id) ON DELETE CASCADE
);