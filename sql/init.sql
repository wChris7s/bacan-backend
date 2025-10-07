-- bacan.category definition

-- Drop table

-- DROP TABLE bacan.category;

CREATE SCHEMA IF NOT EXISTS bacan;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE bacan.category
(
    id         bigserial    NOT NULL,
    "name"     varchar(100) NOT NULL,
    created_at timestamp    NOT NULL,
    updated_at timestamp    NOT NULL,
    CONSTRAINT category_pk PRIMARY KEY (id)
);


-- bacan.country definition

-- Drop table

-- DROP TABLE bacan.country;

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


-- bacan.frontend_module definition

-- Drop table

-- DROP TABLE bacan.frontend_module;

CREATE TABLE bacan.frontend_module
(
    id          bigserial    NOT NULL,
    "name"      varchar(150) NOT NULL,
    icon        varchar(20) NULL,
    "path"      varchar(200) NOT NULL,
    "position"  int4         NOT NULL,
    displayable bool         NOT NULL,
    CONSTRAINT module_pk PRIMARY KEY (id)
);


-- bacan."role" definition

-- Drop table

-- DROP TABLE bacan."role";

CREATE TABLE bacan."role"
(
    id         bigserial          NOT NULL,
    "name"     varchar(20)        NOT NULL,
    created_at timestamp          NOT NULL,
    updated_at timestamp          NOT NULL,
    enabled    bool               NOT NULL,
    is_public  bool DEFAULT false NOT NULL, -- Roles que puedes ser consultados sin estar autenticado
    CONSTRAINT role_pk PRIMARY KEY (id),
    CONSTRAINT role_unique UNIQUE (name)
);

-- Column comments

COMMENT
ON COLUMN bacan."role".is_public IS 'Roles que puedes ser consultados sin estar autenticado';


-- bacan.frontend_access definition

-- Drop table

-- DROP TABLE bacan.frontend_access;

CREATE TABLE bacan.frontend_access
(
    module_id int8 NOT NULL,
    role_id   int8 NOT NULL,
    CONSTRAINT frontend_access_pk PRIMARY KEY (module_id, role_id),
    CONSTRAINT frontend_access_module_fk FOREIGN KEY (module_id) REFERENCES bacan.frontend_module (id),
    CONSTRAINT frontend_access_role_fk FOREIGN KEY (role_id) REFERENCES bacan."role" (id)
);


-- bacan.state definition

-- Drop table

-- DROP TABLE bacan.state;

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


-- bacan."user" definition

-- Drop table

-- DROP TABLE bacan."user";

CREATE TABLE bacan."user"
(
    document_id            varchar(20)  NOT NULL,
    "name"                 varchar(40)  NOT NULL,
    lastname               varchar(40)  NOT NULL,
    birthdate              date         NOT NULL,
    phone                  varchar(20)  NOT NULL,
    email                  varchar(254) NOT NULL,
    "password"             varchar(255) NOT NULL,
    photo                  varchar(255) NOT NULL,
    created_at             timestamp    NOT NULL,
    updated_at             timestamp    NOT NULL,
    enabled                bool         NOT NULL,
    password_modified_date timestamp    NOT NULL,
    phone_country_id       int8         NOT NULL,
    CONSTRAINT user_pk PRIMARY KEY (document_id),
    CONSTRAINT user_country_fk FOREIGN KEY (phone_country_id) REFERENCES bacan.country (id)
);


-- bacan.user_role definition

-- Drop table

-- DROP TABLE bacan.user_role;

CREATE TABLE bacan.user_role
(
    user_id varchar(20) NOT NULL,
    role_id int8        NOT NULL,
    CONSTRAINT permissions_pk PRIMARY KEY (user_id, role_id),
    CONSTRAINT permissions_role_fk FOREIGN KEY (role_id) REFERENCES bacan."role" (id),
    CONSTRAINT permissions_user_fk FOREIGN KEY (user_id) REFERENCES bacan."user" (document_id)
);


-- bacan.wallet definition

-- Drop table

-- DROP TABLE bacan.wallet;

CREATE TABLE bacan.wallet
(
    document_id varchar(20)        NOT NULL,
    balance     float8 DEFAULT 0.0 NOT NULL,
    created_at  timestamp          NOT NULL,
    updated_at  timestamp          NOT NULL,
    CONSTRAINT wallet_pk PRIMARY KEY (document_id),
    CONSTRAINT wallet_user_fk FOREIGN KEY (document_id) REFERENCES bacan."user" (document_id) ON DELETE CASCADE
);


-- bacan.province definition

-- Drop table

-- DROP TABLE bacan.province;

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


-- bacan.store definition

-- Drop table

-- DROP TABLE bacan.store;

CREATE TABLE bacan.store
(
    id         uuid DEFAULT uuid_generate_v4() NOT NULL,
    "name"     varchar(200)                    NOT NULL,
    "open"     time                            NOT NULL,
    "close"    time                            NOT NULL,
    story      text                            NOT NULL,
    logo       varchar(255)                    NOT NULL,
    background varchar(255)                    NOT NULL,
    user_id    varchar(20)                     NOT NULL,
    created_at timestamp                       NOT NULL,
    updated_at timestamp                       NOT NULL,
    enabled    bool                            NOT NULL,
    CONSTRAINT store_pk PRIMARY KEY (id),
    CONSTRAINT store_user_fk FOREIGN KEY (user_id) REFERENCES bacan."user" (document_id)
);


-- bacan.district definition

-- Drop table

-- DROP TABLE bacan.district;

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


-- bacan.product definition

-- Drop table

-- DROP TABLE bacan.product;

CREATE TABLE bacan.product
(
    id          uuid    DEFAULT uuid_generate_v4() NOT NULL,
    store_id    uuid                               NOT NULL,
    "name"      varchar(200)                       NOT NULL,
    price       numeric DEFAULT 0.0                NOT NULL,
    stock       int4    DEFAULT 0                  NOT NULL,
    description text                               NOT NULL,
    photo       bytea                              NOT NULL,
    created_at  timestamp                          NOT NULL,
    updated_at  timestamp                          NOT NULL,
    CONSTRAINT product_pk PRIMARY KEY (id),
    CONSTRAINT product_store_fk FOREIGN KEY (store_id) REFERENCES bacan.store (id) ON DELETE CASCADE
);


-- bacan.product_category definition

-- Drop table

-- DROP TABLE bacan.product_category;

CREATE TABLE bacan.product_category
(
    product_id  uuid NOT NULL,
    category_id int8 NOT NULL,
    CONSTRAINT product_category_pk PRIMARY KEY (product_id, category_id),
    CONSTRAINT product_category_category_fk FOREIGN KEY (category_id) REFERENCES bacan.category (id),
    CONSTRAINT product_category_product_fk FOREIGN KEY (product_id) REFERENCES bacan.product (id)
);


-- bacan.sale definition

-- Drop table

-- DROP TABLE bacan.sale;

CREATE TABLE bacan.sale
(
    id       uuid        NOT NULL,
    store_id uuid        NOT NULL,
    user_id  varchar(20) NOT NULL,
    total    numeric     NOT NULL, -- Precio total de la venta
    quantity int4        NOT NULL, -- Total de productos comprados
    CONSTRAINT sale_pk PRIMARY KEY (id),
    CONSTRAINT sale_store_fk FOREIGN KEY (store_id) REFERENCES bacan.store (id),
    CONSTRAINT sale_user_fk FOREIGN KEY (user_id) REFERENCES bacan."user" (document_id)
);

-- Column comments

COMMENT
ON COLUMN bacan.sale.total IS 'Precio total de la venta';
COMMENT
ON COLUMN bacan.sale.quantity IS 'Total de productos comprados';


-- bacan.sale_detail definition

-- Drop table

-- DROP TABLE bacan.sale_detail;

CREATE TABLE bacan.sale_detail
(
    id         uuid    NOT NULL,
    sale_id    uuid    NOT NULL,
    product_id uuid    NOT NULL,
    quantity   int4    NOT NULL,
    sub_total  numeric NOT NULL,
    CONSTRAINT sale_detail_pk PRIMARY KEY (id),
    CONSTRAINT sale_detail_product_fk FOREIGN KEY (product_id) REFERENCES bacan.product (id),
    CONSTRAINT sale_detail_sale_fk FOREIGN KEY (sale_id) REFERENCES bacan.sale (id)
);


-- bacan.address definition

-- Drop table

-- DROP TABLE bacan.address;

CREATE TABLE bacan.address
(
    id          uuid DEFAULT uuid_generate_v4() NOT NULL,
    user_id     varchar(20)                     NOT NULL,
    country_id  int8                            NOT NULL,
    state_id    varchar(2)                      NOT NULL,
    province_id varchar(4)                      NOT NULL,
    district_id varchar(6)                      NOT NULL,
    street      varchar(255) NULL,
    postal_code varchar(20) NULL,
    "number"    varchar(20) NULL,
    reference   varchar(255) NULL,
    created_at  timestamp                       NOT NULL,
    updated_at  timestamp                       NOT NULL,
    CONSTRAINT address_pk PRIMARY KEY (id),
    CONSTRAINT address_country_fk FOREIGN KEY (country_id) REFERENCES bacan.country (id),
    CONSTRAINT address_district_fk FOREIGN KEY (district_id) REFERENCES bacan.district (id),
    CONSTRAINT address_province_fk FOREIGN KEY (province_id) REFERENCES bacan.province (id),
    CONSTRAINT address_state_fk FOREIGN KEY (state_id) REFERENCES bacan.state (id),
    CONSTRAINT address_user_fk FOREIGN KEY (user_id) REFERENCES bacan."user" (document_id) ON DELETE CASCADE
);


-- bacan.payment definition

-- Drop table

-- DROP TABLE bacan.payment;

CREATE TABLE bacan.payment
(
    id           uuid      NOT NULL,
    sale_id      uuid      NOT NULL,
    payment_date timestamp NOT NULL,
    CONSTRAINT payment_sale_fk FOREIGN KEY (sale_id) REFERENCES bacan.sale (id)
);