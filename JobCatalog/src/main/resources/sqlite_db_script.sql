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

CREATE TABLE "POSITION" (
    "ID" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , 
    "NAME" TEXT NOT NULL , 
    "DESCRIPTION" TEXT
);

CREATE TABLE "SKILL" (
    "ID" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , 
    "NAME" TEXT NOT NULL  UNIQUE 
);

CREATE  TABLE "CPS_CATALOG" (
    "COMPANY_ID" INTEGER, 
    "POSITION_ID" INTEGER, 
    "SKILL_ID" INTEGER,
    FOREIGN KEY(COMPANY_ID) REFERENCES COMPANY(ID),
    FOREIGN KEY(POSITION_ID) REFERENCES POSITION(ID),
    FOREIGN KEY(SKILL_ID) REFERENCES SKILL(ID)
);


-------------------


UPDATE company SET 
name="Facebook", 
description="For-profit corporation and online social media and social networking service",
employee_count=15724
where id = 2;


select c.id, c.name, p.id, p.name, s.id, s.name
from 
company c,
position p,
skill s,
cps_catalog ctg
where 
ctg.company_id = c.id
and ctg.position_id = p.id
and ctg.skill_id = s.id
order by c.id, p.id
;




