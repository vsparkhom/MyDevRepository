
DROP TABLE accounts;

CREATE TABLE accounts (
  id NUMBER(10) NOT NULL,
  login VARCHAR(50) NOT NULL,
  password VARCHAR(50),
  first_name VARCHAR(50),
  last_name VARCHAR(50),
  status VARCHAR(20) NOT NULL,
  email VARCHAR(1000),
  address  VARCHAR(200)
  , CONSTRAINT accounts_pk PRIMARY KEY (id)
  , CONSTRAINT accounts_login_uk UNIQUE (login)
  , CONSTRAINT accounts_status_fixed_values CHECK (status IN ('Active', 'Inactive'))
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


DROP TABLE plans;

CREATE TABLE plans (
  id NUMBER(10) NOT NULL,
  name VARCHAR(200) NOT NULL,
  price NUMBER DEFAULT 0
  , CONSTRAINT plans_pk PRIMARY KEY (id)
  , CONSTRAINT plans_name_uk UNIQUE (name)
  , CONSTRAINT plans_price_not_negative CHECK (price >= 0)
); 


INSERT INTO plans VALUES(1, 'Internet Plan 1', 50);
INSERT INTO plans VALUES(2, 'Internet Plan 2', 100);
INSERT INTO plans VALUES(3, 'TV Plan 1', 45);
INSERT INTO plans VALUES(4, 'TV Plan 2', 90);
INSERT INTO plans VALUES(5, 'Phone Plan 1', 35);
INSERT INTO plans VALUES(6, 'Phone Plan 2', 70);



DROP TABLE service_types;

CREATE TABLE service_types (
  id NUMBER(10) NOT NULL,
  name VARCHAR(200) NOT NULL
  , CONSTRAINT service_types_pk PRIMARY KEY (id)
  , CONSTRAINT service_types_name_uk UNIQUE (name)
); 

INSERT INTO service_types VALUES(100, 'Internet');
INSERT INTO service_types VALUES(101, 'TV');
INSERT INTO service_types VALUES(102, 'Phone');


DROP TABLE services;

CREATE TABLE services (
  id NUMBER(10) NOT NULL,
  name VARCHAR(200) NOT NULL,
  type_id NUMBER(10),
  plan_id NUMBER(10)
  , CONSTRAINT services_pk PRIMARY KEY (id)
  , CONSTRAINT services_name_uk UNIQUE (name)
  , CONSTRAINT services_type_id_fk
      FOREIGN KEY (type_id)
      REFERENCES service_types (id)
  , CONSTRAINT services_plan_id_fk
      FOREIGN KEY (plan_id)
      REFERENCES plans (id)
); 

INSERT INTO services VALUES(10, 'Internet Service 1', 100, 1);
INSERT INTO services VALUES(20, 'Internet Service 2', 100, 2);
INSERT INTO services VALUES(30, 'TV Service 1', 101, 3);
INSERT INTO services VALUES(40, 'TV Service 2', 101, 4);
INSERT INTO services VALUES(50, 'Phone Service 1', 102, 5);
INSERT INTO services VALUES(60, 'Phone Service 2', 102, 6);


DROP TABLE client_services;

CREATE TABLE client_services (
  account_id NUMBER(10),
  service_id NUMBER(10)
  , CONSTRAINT client_services_uk UNIQUE (account_id, service_id)
  , CONSTRAINT client_services_acc_id_fk
      FOREIGN KEY (account_id)
      REFERENCES accounts (id)
      ON DELETE CASCADE
  , CONSTRAINT client_services_serv_id_fk
      FOREIGN KEY (service_id)
      REFERENCES services (id)
      ON DELETE CASCADE
); 


INSERT INTO client_services VALUES(1, 10); -- Internet Service 1
--INSERT INTO client_services VALUES(1, 20); -- Internet Service 2
--INSERT INTO client_services VALUES(1, 30); -- TV Service 1
--INSERT INTO client_services VALUES(1, 40); -- TV Service 2
--INSERT INTO client_services VALUES(1, 50); -- Phone Service 1
--INSERT INTO client_services VALUES(1, 60); -- Phone Service 2

--INSERT INTO client_services VALUES(2, 10); -- Internet Service 1
INSERT INTO client_services VALUES(2, 20); -- Internet Service 2
INSERT INTO client_services VALUES(2, 30); -- TV Service 1
--INSERT INTO client_services VALUES(2, 40); -- TV Service 2
--INSERT INTO client_services VALUES(2, 50); -- Phone Service 1
--INSERT INTO client_services VALUES(2, 60, 'Active'); -- Phone Service 2

--INSERT INTO client_services VALUES(3, 10); -- Internet Service 1
--INSERT INTO client_services VALUES(3, 20); -- Internet Service 2
--INSERT INTO client_services VALUES(3, 30); -- TV Service 1
--INSERT INTO client_services VALUES(3, 40); -- TV Service 2
--INSERT INTO client_services VALUES(3, 50); -- Phone Service 1
--INSERT INTO client_services VALUES(3, 60); -- Phone Service 2


DROP TABLE payments;

CREATE TABLE payments (
  id NUMBER(10),
  account_id NUMBER(10),
  period_start DATE,
  period_end DATE,
  pay_before DATE,
  summa NUMBER(10) DEFAULT 0,
  status VARCHAR2(50) DEFAULT 'Not Paid'
  , CONSTRAINT payments_pk PRIMARY KEY (id)
  , CONSTRAINT payments_account_id_fk
      FOREIGN KEY (account_id)
      REFERENCES accounts (id)
  , CONSTRAINT payments_status_values_chk CHECK (status IN ('Paid', 'Not Paid'))
  , CONSTRAINT payments_period_chk CHECK (period_start <= period_end)
); 

INSERT INTO payments VALUES(1001, 1, TO_DATE('2017/07/01', 'yyyy/mm/dd'), TO_DATE('2017/07/30', 'yyyy/mm/dd'), 
    TO_DATE('2017/07/30', 'yyyy/mm/dd'), 100, 'Paid');
INSERT INTO payments VALUES(1002, 1, TO_DATE('2017/08/01', 'yyyy/mm/dd'), TO_DATE('2017/08/30', 'yyyy/mm/dd'), 
    TO_DATE('2017/08/30', 'yyyy/mm/dd'), 100, 'Paid');
INSERT INTO payments VALUES(1003, 1, TO_DATE('2017/09/01', 'yyyy/mm/dd'), TO_DATE('2017/09/30', 'yyyy/mm/dd'), 
    TO_DATE('2017/09/30', 'yyyy/mm/dd'), 100, 'Paid');
INSERT INTO payments VALUES(1004, 1, TO_DATE('2017/10/01', 'yyyy/mm/dd'), TO_DATE('2017/10/30', 'yyyy/mm/dd'), 
    TO_DATE('2017/10/30', 'yyyy/mm/dd'), 100, 'Paid');
INSERT INTO payments VALUES(1005, 1, TO_DATE('2017/11/01', 'yyyy/mm/dd'), TO_DATE('2017/11/30', 'yyyy/mm/dd'), 
    TO_DATE('2017/11/30', 'yyyy/mm/dd'), 100, 'Paid');
INSERT INTO payments VALUES(1006, 1, TO_DATE('2017/12/01', 'yyyy/mm/dd'), TO_DATE('2017/12/30', 'yyyy/mm/dd'), 
    TO_DATE('2017/12/30', 'yyyy/mm/dd'), 100, 'Paid');
INSERT INTO payments VALUES(1007, 1, TO_DATE('2018/01/01', 'yyyy/mm/dd'), TO_DATE('2018/01/30', 'yyyy/mm/dd'), 
    TO_DATE('2018/01/30', 'yyyy/mm/dd'), 100, 'Not Paid');
INSERT INTO payments VALUES(1008, 2, TO_DATE('2018/01/01', 'yyyy/mm/dd'), TO_DATE('2018/01/30', 'yyyy/mm/dd'), 
    TO_DATE('2018/01/30', 'yyyy/mm/dd'), 100, 'Not Paid');
    


DROP TABLE hardware;

CREATE TABLE hardware (
  id NUMBER(10),
  account_id NUMBER(10),
  name VARCHAR2(200) NOT NULL,
  serial_number VARCHAR2(200) NOT NULL,
  status VARCHAR2(50) DEFAULT 'Inactive'
  , CONSTRAINT hw_pk PRIMARY KEY (id)
  , CONSTRAINT hw_account_id_fk
      FOREIGN KEY (account_id)
      REFERENCES accounts (id)
  , CONSTRAINT hw_status_values_chk check (status in ('Active'))
); 


INSERT INTO hardware VALUES(5001, 1, 'Internet Modem 1', 'SN123', 'Active');
INSERT INTO hardware VALUES(5002, null, 'Internet Modem 2', 'SN124');