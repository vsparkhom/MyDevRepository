
-- DROP OBJECTS [START] --

DROP TABLE client_services;
DROP TABLE hardware;
DROP TABLE internet_service_options;
DROP TABLE tv_service_options;
DROP TABLE phone_service_options;
DROP TABLE services;
DROP TABLE service_types;
DROP TABLE payments;
DROP TABLE plans;
DROP TABLE accounts;
DROP SEQUENCE accounts_seq;
DROP TABLE information;
DROP TABLE info_attributes;
DROP TABLE info_categories;

-- DROP OBJECTS [END] --

-- CREATE OBJECTS [START] --


CREATE TABLE accounts (
  id NUMBER(10) NOT NULL,
  login VARCHAR(50) NOT NULL,
  password VARCHAR(50),
  first_name VARCHAR(100),
  last_name VARCHAR(100),
  status VARCHAR(20) NOT NULL,
  email VARCHAR(100),
  address  VARCHAR(1000)
  , CONSTRAINT accounts_pk PRIMARY KEY (id)
  , CONSTRAINT accounts_login_uk UNIQUE (login)
  , CONSTRAINT accounts_status_fixed_values CHECK (status IN ('Active', 'Inactive'))
); 


--DROP SEQUENCE accounts_seq;

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
/

INSERT INTO accounts(login, password, first_name, last_name, status, email, address) VALUES('test', 'test', 'Vasia', 'Pupkin', 'Active', 'test@gmail.com', 'New York');
INSERT INTO accounts(login, password, first_name, last_name, status, email, address) VALUES('test2', 'test2', 'John', 'Smith', 'Active', 'test2@gmail.com', 'Chicago');
INSERT INTO accounts(login, password, first_name, last_name, status, email, address) VALUES('guest', 'guest', 'Bruce', 'Lee', 'Inactive', 'brlee@gmail.com', 'Japan');
INSERT INTO accounts(login, password, first_name, last_name, status, email, address) VALUES('phoneuser', '123', 'Kate', 'Radistka', 'Active', 'phuser@gmail.com', 'Canada');


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
INSERT INTO plans VALUES(7, 'Phone Plan 3', 80);


CREATE TABLE service_types (
  id NUMBER(10) NOT NULL,
  name VARCHAR(200) NOT NULL
  , CONSTRAINT service_types_pk PRIMARY KEY (id)
  , CONSTRAINT service_types_name_uk UNIQUE (name)
); 

INSERT INTO service_types VALUES(100, 'Internet');
INSERT INTO service_types VALUES(101, 'TV');
INSERT INTO service_types VALUES(102, 'Phone');


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
INSERT INTO services VALUES(70, 'Phone Service 3', 102, 7);


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


CREATE TABLE payments (
  id NUMBER(10),
  account_id NUMBER(10),
  period_start DATE,
  period_end DATE,
  due_date DATE,
  summa NUMBER(10) DEFAULT 0,
  status VARCHAR2(50) DEFAULT 'Not Paid',
  "COMMENT" VARCHAR2(200)
  , CONSTRAINT payments_pk PRIMARY KEY (id)
  , CONSTRAINT payments_account_id_fk
      FOREIGN KEY (account_id)
      REFERENCES accounts (id)
  , CONSTRAINT payments_status_values_chk CHECK (status IN ('Paid', 'Not Paid'))
  , CONSTRAINT payments_period_chk CHECK (period_start <= period_end)
); 

INSERT INTO payments VALUES(1001, 1, TO_DATE('2017/07/01', 'yyyy/mm/dd'), TO_DATE('2017/07/30', 'yyyy/mm/dd'), 
    TO_DATE('2017/07/30', 'yyyy/mm/dd'), 100, 'Paid', 'Montly fee');
INSERT INTO payments VALUES(1002, 1, TO_DATE('2017/08/01', 'yyyy/mm/dd'), TO_DATE('2017/08/30', 'yyyy/mm/dd'), 
    TO_DATE('2017/08/30', 'yyyy/mm/dd'), 100, 'Paid', 'Montly fee');
INSERT INTO payments VALUES(1003, 1, TO_DATE('2017/09/01', 'yyyy/mm/dd'), TO_DATE('2017/09/30', 'yyyy/mm/dd'), 
    TO_DATE('2017/09/30', 'yyyy/mm/dd'), 100, 'Paid', 'Montly fee');
INSERT INTO payments VALUES(1004, 1, TO_DATE('2017/10/01', 'yyyy/mm/dd'), TO_DATE('2017/10/30', 'yyyy/mm/dd'), 
    TO_DATE('2017/10/30', 'yyyy/mm/dd'), 100, 'Paid', 'Montly fee');
INSERT INTO payments VALUES(1005, 1, TO_DATE('2017/11/01', 'yyyy/mm/dd'), TO_DATE('2017/11/30', 'yyyy/mm/dd'), 
    TO_DATE('2017/11/30', 'yyyy/mm/dd'), 100, 'Paid', 'Montly fee');
INSERT INTO payments VALUES(1006, 1, TO_DATE('2017/12/01', 'yyyy/mm/dd'), TO_DATE('2017/12/30', 'yyyy/mm/dd'), 
    TO_DATE('2017/12/30', 'yyyy/mm/dd'), 100, 'Paid', 'Montly fee');
INSERT INTO payments VALUES(1007, 1, TO_DATE('2018/01/01', 'yyyy/mm/dd'), TO_DATE('2018/01/30', 'yyyy/mm/dd'), 
    TO_DATE('2018/01/30', 'yyyy/mm/dd'), 100, 'Not Paid', 'Montly fee');
INSERT INTO payments VALUES(1008, 2, TO_DATE('2018/01/01', 'yyyy/mm/dd'), TO_DATE('2018/01/30', 'yyyy/mm/dd'), 
    TO_DATE('2018/01/30', 'yyyy/mm/dd'), 100, 'Not Paid', 'Montly fee');
    

CREATE TABLE hardware (
  id NUMBER(10),
  account_id NUMBER(10),
  service_type_id NUMBER(10) NOT NULL,
  name VARCHAR2(200) NOT NULL,
  serial_number VARCHAR2(200) NOT NULL,
  status VARCHAR2(50) DEFAULT 'Inactive'
  , CONSTRAINT hw_pk PRIMARY KEY (id)
  , CONSTRAINT hw_account_id_fk
      FOREIGN KEY (account_id)
      REFERENCES accounts (id)
  , CONSTRAINT hw_service_type_id_fk
      FOREIGN KEY (service_type_id)
      REFERENCES service_types (id)
  , CONSTRAINT hw_status_values_chk check (status in ('Active', 'Inactive'))
); 

-- Internet hardware
INSERT INTO hardware VALUES(5001, 1, 100, 'Internet Modem 1', 'SN123', 'Active');
INSERT INTO hardware VALUES(5002, null, 100, 'Internet Modem 2', 'SN124', 'Inactive');
INSERT INTO hardware VALUES(5003, 2, 100, 'Internet Modem 3', 'SN125', 'Active');

-- TV hardware
INSERT INTO hardware VALUES(5004, null, 101, 'TV Modem 1', 'SN00178', 'Inactive');
INSERT INTO hardware VALUES(5005, 2, 101, 'TV Modem 2', 'SN00179', 'Active');

-- Phone hardware
INSERT INTO hardware VALUES(5006, 1, 102, 'Phone Device 1', 'PH0001', 'Inactive');
INSERT INTO hardware VALUES(5007, null, 102, 'Phone Device 2', 'PH002', 'Inactive');
INSERT INTO hardware VALUES(5008, null, 102, 'Phone Device 3', 'PH003', 'Active');


CREATE TABLE info_categories (
  id NUMBER(10) NOT NULL,
  name VARCHAR(200) NOT NULL
  , CONSTRAINT info_categories_pk PRIMARY KEY (id)
  , CONSTRAINT info_categories_name_uk UNIQUE (name)
); 

INSERT INTO info_categories VALUES(1, 'Internet');
INSERT INTO info_categories VALUES(2, 'TV');
INSERT INTO info_categories VALUES(3, 'Phone');
INSERT INTO info_categories VALUES(4, 'Billing');
INSERT INTO info_categories VALUES(5, 'General');


CREATE TABLE info_attributes (
  id NUMBER(10) NOT NULL,
  name VARCHAR(200) NOT NULL
  , CONSTRAINT info_attributes_pk PRIMARY KEY (id)
  , CONSTRAINT info_attributes_uk UNIQUE (name)
); 

INSERT INTO info_attributes VALUES(100, 'Phone Number');
INSERT INTO info_attributes VALUES(101, 'Email');
INSERT INTO info_attributes VALUES(102, 'Facebook');
INSERT INTO info_attributes VALUES(103, 'VK');


CREATE TABLE information (
  attr_id NUMBER(10) NOT NULL,
  category_id NUMBER(10) NOT NULL,
  value VARCHAR2(500)
  , CONSTRAINT information_attr_id_fk
      FOREIGN KEY (attr_id)
      REFERENCES info_attributes (id)
  , CONSTRAINT information_cat_id_fk
      FOREIGN KEY (category_id)
      REFERENCES info_categories (id)
);

INSERT INTO information VALUES(100, 1, '(403) 123-0001');
INSERT INTO information VALUES(101, 1, 'internet_support@gmail.com');

INSERT INTO information VALUES(100, 2, '(403) 123-0002');
INSERT INTO information VALUES(101, 2, 'tv_support@gmail.com');

INSERT INTO information VALUES(100, 3, '(403) 123-0003');
INSERT INTO information VALUES(101, 3, 'phone_support@gmail.com');

INSERT INTO information VALUES(100, 4, '(403) 123-0004');
INSERT INTO information VALUES(101, 4, 'billing_support@gmail.com');

INSERT INTO information VALUES(102, 5, 'tks-facebook');
INSERT INTO information VALUES(103, 5, 'vk-facebook');


CREATE TABLE internet_service_options (
  service_id NUMBER(10) NOT NULL,
  download_speed VARCHAR(20) NOT NULL,
  upload_speed VARCHAR(20) NOT NULL,
  data_limit VARCHAR(20) NOT NULL
  , CONSTRAINT int_serv_opt_fk
      FOREIGN KEY (service_id)
      REFERENCES services (id)
); 

INSERT INTO internet_service_options VALUES(10, '15 Mbps', '5 Mbps', '100 GB');
INSERT INTO internet_service_options VALUES(20, '75 Mbps', '15 Mbps', '500 GB');


CREATE TABLE tv_service_options (
  service_id NUMBER(10) NOT NULL,
  channels_count NUMBER(5) NOT NULL,
  uhd_support VARCHAR(10) NOT NULL
  , CONSTRAINT tv_serv_opt_fk
      FOREIGN KEY (service_id)
      REFERENCES services (id)
  , CONSTRAINT tv_serv_opt_uhd_values CHECK (uhd_support IN ('On', 'Off'))
);

INSERT INTO tv_service_options VALUES(30, 25, 'Off');
INSERT INTO tv_service_options VALUES(40, 50, 'On');


CREATE TABLE phone_service_options (
  service_id NUMBER(10) NOT NULL,
  talk_limit NUMBER(10) NOT NULL,
  data_limit NUMBER(10) NOT NULL,
  voice_mail VARCHAR2(5) DEFAULT 'Off'
  , CONSTRAINT ph_serv_opt_fk
      FOREIGN KEY (service_id)
      REFERENCES services (id)
  , CONSTRAINT ph_serv_opt_vm_values CHECK (voice_mail IN ('On', 'Off'))
);

INSERT INTO phone_service_options VALUES(50, 100, 1, 'Off');
INSERT INTO phone_service_options VALUES(60, 500, 5, 'On');
INSERT INTO phone_service_options VALUES(70, 1000, 10, 'On');

-- CREATE OBJECTS [END] --