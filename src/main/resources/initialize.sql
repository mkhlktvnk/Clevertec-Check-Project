create table _check (
    id               bigserial      not null,
    created_at       timestamp(6)   not null,
    total_price      numeric(38, 2) not null,
    discount_card_id bigint,
    primary key (id)
);

create table discount_card (
    id       bigserial not null,
    discount integer   not null,
    primary key (id)
);

create table position (
    id          bigserial      not null,
    quantity    integer        not null,
    total_price numeric(38, 2) not null,
    check_id    bigint,
    product_id  bigint         not null,
    primary key (id)
);

create table product (
    id          bigserial      not null,
    name        varchar(255)   not null,
    price       numeric(40, 2) not null,
    promotional boolean        not null,
    primary key (id)
);

alter table if exists _check
    add constraint fk_discount_card_id
        foreign key (discount_card_id)
            references discount_card;

alter table if exists position
    add constraint fk_check_id
        foreign key (check_id)
            references _check;

alter table if exists position
    add constraint fk_product_id
        foreign key (product_id)
            references product;