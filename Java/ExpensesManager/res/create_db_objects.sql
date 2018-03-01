create table categories (
    id integer  not null primary key,
    name text  unique not null,
    'limit' float default '0' null
);

insert into categories(name, 'limit') values ('Unknown', 100);
insert into categories(name, 'limit') values ('Other', 100);
insert into categories(name, 'limit') values ('Car', 100);
insert into categories(name, 'limit') values ('Home', 100);
insert into categories(name, 'limit') values ('Food', 100);
insert into categories(name, 'limit') values ('Vova', 100);
insert into categories(name, 'limit') values ('Dog', 100);

create table expenses (
    id integer  primary key not null,
    date date  null,
    merchant text  null,
    amount float default '0' null,
    category_id integer,
    foreign key (category_id) references categories(id)
);

insert into expenses(date, merchant, amount, category_id)
values (date('now'), 'car service', 20, 3);

insert into expenses(date, merchant, amount, category_id)
values (date('now'), 'gas', 20, 3);

insert into expenses(date, merchant, amount, category_id)
values (date('now'), 'shampoo', 20, 4);

insert into expenses(date, merchant, amount, category_id)
values (date('now'), 'bread', 20, 5);

insert into expenses(date, merchant, amount, category_id)
values (date('now'), 'beer', 20, 5);


create table expenses_mapping (
    pattern text  not null primary key,
    category_id integer,
    foreign key (category_id) references categories(id)
);

insert into expenses_mapping values ('WWW.ALIEXPRESS.COM', 2);
insert into expenses_mapping values ('HUMBLEBUNDLE.COM', 2);
insert into expenses_mapping values ('1786 GB CALGARY SETON', 3);
insert into expenses_mapping values ('IKEA CALGARY', 4);
insert into expenses_mapping values ('REAL CANADIAN SUPERSTO', 5);
insert into expenses_mapping values ('REAL CANADIAN LIQUOR S', 6);
insert into expenses_mapping values ('PET PLANET', 7);
