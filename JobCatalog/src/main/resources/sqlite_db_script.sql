-- .open c:/parkhomchuk/Repositories/VLPA/MyDevRepository/JobCatalog/src/main/resources/JobCatalog.db

DROP TABLE COMPANY;

CREATE TABLE COMPANY 
(
    ID INTEGER PRIMARY KEY AUTOINCREMENT, 
    NAME TEXT NOT NULL, 
    DESCRIPTION TEXT, 
    EMPLOYEE_COUNT NUMBER, 
    ADDRESS TEXT
);

INSERT INTO COMPANY(NAME, DESCRIPTION, EMPLOYEE_COUNT, ADDRESS) 
VALUES ("Google","Multinational technology company specializing in Internet-related services and products that include online advertising technologies, search, cloud computing, software, and hardware.",57100,"California");

INSERT INTO COMPANY(NAME, DESCRIPTION, EMPLOYEE_COUNT, ADDRESS)  
VALUES ("Facebook","For-profit corporation and online social media and social networking service",15724,"California");



DROP TABLE POSITION;

CREATE TABLE POSITION (
    ID INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , 
    NAME TEXT NOT NULL , 
    DESCRIPTION TEXT, 
    COMPANY_ID INTEGER,
    FOREIGN KEY(COMPANY_ID) REFERENCES COMPANY(ID)
);

INSERT INTO POSITION(NAME,DESCRIPTION,COMPANY_ID) VALUES ("Java Developer"     ,null,1);
INSERT INTO POSITION(NAME,DESCRIPTION,COMPANY_ID) VALUES ("PHP Developer"      ,null,2);
INSERT INTO POSITION(NAME,DESCRIPTION,COMPANY_ID) VALUES ("QA Engineer"        ,null,1);
INSERT INTO POSITION(NAME,DESCRIPTION,COMPANY_ID) VALUES ("HD Support Engineer",null,2);


DROP TABLE SKILL;

CREATE TABLE SKILL (
    ID INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , 
    NAME TEXT NOT NULL  UNIQUE 
);

INSERT INTO SKILL(NAME) VALUES ("Java");
INSERT INTO SKILL(NAME) VALUES ("PHP");
INSERT INTO SKILL(NAME) VALUES ("Git");
INSERT INTO SKILL(NAME) VALUES ("SVN");
INSERT INTO SKILL(NAME) VALUES ("Maven");
INSERT INTO SKILL(NAME) VALUES ("AGILE");
INSERT INTO SKILL(NAME) VALUES ("JUnit");
INSERT INTO SKILL(NAME) VALUES ("English");


DROP TABLE PS_CATALOG;

CREATE  TABLE PS_CATALOG (
    POSITION_ID INTEGER, 
    SKILL_ID INTEGER,
    FOREIGN KEY(POSITION_ID) REFERENCES POSITION(ID),
    FOREIGN KEY(SKILL_ID) REFERENCES SKILL(ID)
);

INSERT INTO PS_CATALOG(POSITION_ID,SKILL_ID) VALUES ("1","1");
INSERT INTO PS_CATALOG(POSITION_ID,SKILL_ID) VALUES ("1","3");
INSERT INTO PS_CATALOG(POSITION_ID,SKILL_ID) VALUES ("1","5");
INSERT INTO PS_CATALOG(POSITION_ID,SKILL_ID) VALUES ("2","2");
INSERT INTO PS_CATALOG(POSITION_ID,SKILL_ID) VALUES ("2","4");
INSERT INTO PS_CATALOG(POSITION_ID,SKILL_ID) VALUES ("3","6");
INSERT INTO PS_CATALOG(POSITION_ID,SKILL_ID) VALUES ("3","7");
INSERT INTO PS_CATALOG(POSITION_ID,SKILL_ID) VALUES ("4","8");


------------------------ QUERIES ----------------------------


UPDATE company SET 
name="Facebook", 
description="For-profit corporation and online social media and social networking service",
employee_count=15724
where id = 2;

--- select all positions with all skills for all companies

select c.id, c.name, p.id, p.name, s.id, s.name
from 
ps_catalog ctg,
company c,
position p,
skill s
where 
ctg.position_id = p.id
and p.company_id = c.id
and ctg.skill_id = s.id
order by c.id, p.id
;





