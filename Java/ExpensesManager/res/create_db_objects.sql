
--- DROP SECTION ---

drop table expenses;
drop table expense_patterns;
drop table expense_pattern_types;
drop table categories;
drop table import_history;


--- CREATE SECTION ---

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
    bank text null,
    description text null,
    foreign key (category_id) references categories(id)
);

create table expense_pattern_types (
    id integer primary key not null,
    name text
);

insert into expense_pattern_types(name) values('REGULAR');
insert into expense_pattern_types(name) values('SKIP');
insert into expense_pattern_types(name) values('AMOUNT_BASED');

create table expense_patterns (
    id integer primary key not null,
    pattern text,
    category_id integer,
    type_id integer,
    priority integer default '10', -- MEDIUM
    amount float default '0',
    foreign key (category_id) references categories(id),
    foreign key (type_id) references expense_pattern_types(id)
);

create table import_history (
    date date null,
    message text null
);
