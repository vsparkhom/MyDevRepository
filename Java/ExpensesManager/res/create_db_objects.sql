create table categories (
    id integer not null primary key,
    name text  unique not null,
    'limit' float default '0' null
);

insert into categories(name, 'limit') values ('Unknown', 1000);

create table expenses (
    id integer primary key not null,
    date date null,
    merchant text null,
    amount float default '0' null,
    category_id integer,
    foreign key (category_id) references categories(id)
);

create table expense_pattern_types (
    id integer primary key not null,
    name text
);

insert into expense_pattern_types(name) values('REGULAR');
insert into expense_pattern_types(name) values('SKIP');

create table expense_patterns (
    id integer  primary key not null,
    pattern text,
    category_id integer,
    type_id integer,
    foreign key (category_id) references categories(id),
    foreign key (type_id) references expense_pattern_types(id)
);
