DROP TABLE COMPANY;

CREATE TABLE COMPANY 
(
ID INTEGER PRIMARY KEY AUTOINCREMENT, 
NAME TEXT NOT NULL, 
DESCRIPTION TEXT, 
EMPLOYEE_COUNT NUMBER, 
ADDRESS TEXT
);

insert into company(name,description,employee_count,address) values('TestCompanyName1','TestDescr1',100,'California');
insert into company(name,description,employee_count,address) values('TestCompanyName2','TestDescr2',200,'California');
insert into company(name,description,employee_count,address) values('TestCompanyName3','TestDescr3',300,'California');

-- .open c:/parkhomchuk/Repositories/VLPA/MyDevRepository/JobCatalog/src/main/resources/JobCatalog.db