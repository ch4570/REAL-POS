
create table if not exists JWT_TOKEN(
    TOKEN_ID bigint auto_increment primary key,
    MEMBER_ID bigint not null,
    ACCESS_TOKEN TEXT not null,
    REG_DATE timestamp not null default current_timestamp,
    MOD_DATE TIMESTAMP not null default current_timestamp
);

create table if not exists MEMBER(
    MEMBER_ID bigint auto_increment primary key,
    PHONE_NUMBER varchar(20) not null,
    MEMBER_PASSWORD varchar(100) not null,
    REG_DATE timestamp not null default current_timestamp,
    MOD_DATE TIMESTAMP not null default current_timestamp
);

create table if not exists CATEGORY(
    CATEGORY_ID bigint auto_increment primary key,
    CATEGORY_NAME varchar(30) not null,
    REG_DATE timestamp not null default current_timestamp,
    MOD_DATE TIMESTAMP not null default current_timestamp
);

create table if not exists PRODUCT(
    PRODUCT_ID bigint auto_increment primary key,
    CATEGORY_ID bigint not null,
    PRODUCT_COST integer not null,
    PRODUCT_PRICE integer not null,
    PRODUCT_NAME varchar(100) not null,
    PRODUCT_BARCODE varchar(100) not null,
    PRODUCT_DESCRIPTION text not null,
    PRODUCT_EXPIRATION_DATE timestamp not null,
    PRODUCT_SIZE varchar(20) not null,
    REG_DATE timestamp not null default current_timestamp,
    MOD_DATE TIMESTAMP not null default current_timestamp
);

create table if not exists PRODUCT_SEARCH(
    PRODUCT_SEARCH_ID bigint auto_increment primary key,
    PRODUCT_ID bigint not null,
    PRODUCT_SEARCH_KEYWORD varchar(100) not null,
    REG_DATE timestamp not null default current_timestamp,
    MOD_DATE TIMESTAMP not null default current_timestamp
);