CREATE TABLE bacan.country
(
    id         bigserial    NOT NULL,
    "name"     varchar(100) NOT NULL,
    phone_code varchar(10)  NOT NULL,
    lang_code  bpchar(2) NOT NULL,
    CONSTRAINT country_pk PRIMARY KEY (id)
);
COMMENT
ON TABLE bacan.country IS 'País';

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

CREATE TABLE bacan."type"
(
    id         bigserial   NOT NULL,
    "name"     varchar(40) NOT NULL,
    created_at timestamp   NOT NULL,
    updated_at timestamp   NOT NULL,
    CONSTRAINT type_pk PRIMARY KEY (id)
);

CREATE TABLE bacan."user"
(
    document_id            varchar(20)  NOT NULL,
    "name"                 varchar(100) NOT NULL,
    lastname               varchar(100) NOT NULL,
    birth_date             timestamp    NOT NULL,
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

CREATE TABLE bacan.state
(
    id         varchar(2)   NOT NULL,
    "name"     varchar(100) NOT NULL,
    country_id int8         NOT NULL,
    CONSTRAINT state_pk PRIMARY KEY (id),
    CONSTRAINT state_country_fk FOREIGN KEY (country_id) REFERENCES bacan.country (id) ON DELETE CASCADE
);
COMMENT
ON TABLE bacan.state IS 'Departamento';

CREATE TABLE bacan.user_role
(
    user_id varchar(20) NOT NULL,
    role_id int8        NOT NULL,
    CONSTRAINT permissions_pk PRIMARY KEY (user_id, role_id),
    CONSTRAINT permissions_role_fk FOREIGN KEY (role_id) REFERENCES bacan."role" (id),
    CONSTRAINT permissions_user_fk FOREIGN KEY (user_id) REFERENCES bacan."user" (document_id)
);

CREATE TABLE bacan.province
(
    id       varchar(4)   NOT NULL,
    "name"   varchar(100) NOT NULL,
    state_id varchar(2)   NOT NULL,
    CONSTRAINT province_pk PRIMARY KEY (id),
    CONSTRAINT province_state_fk FOREIGN KEY (state_id) REFERENCES bacan.state (id)
);
COMMENT
ON TABLE bacan.province IS 'Provincia';

CREATE TABLE bacan.district
(
    id          varchar(6)   NOT NULL,
    "name"      varchar(100) NOT NULL,
    province_id varchar(4)   NOT NULL,
    state_id    varchar(2)   NOT NULL,
    CONSTRAINT district_pk PRIMARY KEY (id),
    CONSTRAINT district_province_fk FOREIGN KEY (province_id) REFERENCES bacan.province (id),
    CONSTRAINT district_state_fk FOREIGN KEY (state_id) REFERENCES bacan.state (id)
);
COMMENT
ON TABLE bacan.district IS 'Distrito';

CREATE TABLE bacan.address
(
    id          uuid        NOT NULL,
    user_id     varchar(20) NOT NULL,
    country_id  int8        NOT NULL,
    state_id    varchar(2)  NOT NULL,
    province_id varchar(4)  NOT NULL,
    district_id varchar(6)  NOT NULL,
    street      varchar(255) NULL,
    postal_code varchar(20) NULL,
    "number"    varchar(20) NULL,
    reference   varchar(255) NULL,
    created_at  timestamp   NOT NULL,
    updated_at  timestamp   NOT NULL,
    CONSTRAINT address_pk PRIMARY KEY (id),
    CONSTRAINT address_country_fk FOREIGN KEY (country_id) REFERENCES bacan.country (id),
    CONSTRAINT address_district_fk FOREIGN KEY (district_id) REFERENCES bacan.district (id),
    CONSTRAINT address_province_fk FOREIGN KEY (province_id) REFERENCES bacan.province (id),
    CONSTRAINT address_state_fk FOREIGN KEY (state_id) REFERENCES bacan.state (id),
    CONSTRAINT address_user_fk FOREIGN KEY (user_id) REFERENCES bacan."user" (document_id) ON DELETE CASCADE
);