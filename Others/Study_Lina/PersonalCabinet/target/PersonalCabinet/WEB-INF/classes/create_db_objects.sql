
drop table accounts;

CREATE TABLE accounts (
  id NUMBER(6) NOT NULL,
  login VARCHAR(50) NOT NULL,
  password VARCHAR(50),
  first_name VARCHAR(50),
  last_name VARCHAR(50),
  status VARCHAR(20) NOT NULL,
  email VARCHAR(1000),
  address  VARCHAR(200)
  , CONSTRAINT accounts_pk PRIMARY KEY (id)
  , CONSTRAINT accounts_login_uk UNIQUE (login)
  , CONSTRAINT accounts_status_fixed_values check (status in ('Active', 'Inactive'))
); 

DROP SEQUENCE accounts_seq;

CREATE SEQUENCE accounts_seq
  START WITH 1
  INCREMENT BY 1
  CACHE 100;
  
CREATE OR REPLACE TRIGGER accounts_insert_trigger
  BEFORE INSERT ON accounts
  FOR EACH ROW
BEGIN
  SELECT accounts_seq.nextval
    INTO :new.id
    FROM dual;
END;

COMMIT;

INSERT INTO accounts(login, password, first_name, last_name, status, email, address) VALUES('test', 'test', 'Vasia', 'Pupkin', 'Active', 'test@gmail.com', 'New York');
INSERT INTO accounts(login, password, first_name, last_name, status, email, address) VALUES('test2', 'test2', 'John', 'Smith', 'Active', 'test2@gmail.com', 'Chicago');
INSERT INTO accounts(login, password, first_name, last_name, status, email, address) VALUES('guest', 'guest', 'Bruce', 'Lee', 'Inactive', 'brlee@gmail.com', 'Japan');