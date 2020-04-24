create table users(
    id serial,
    name varchar(50),
    email varchar(100),
    password varchar,
    primary key (id)
);

create table wallets(
    id serial,
    name varchar(60),
    value numeric(10,6),
    primary key (id)
);

create table users_wallets(
    id serial,
    wallets integer,
    users integer,
    primary key (id),
    foreign key (users) references users(id),
    foreign key (wallets) references wallets(id)
);

create table wallets_items(
    id serial,
    wallet integer,
    date date,
    type varchar(2),
    description varchar(500),
    value numeric(10,6),
    primary key (id),
    foreign key (wallet) references wallets(id)
);