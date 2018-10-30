
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
DROP TABLE information;
DROP TABLE info_attributes;
DROP TABLE info_categories;

DROP SEQUENCE accounts_seq;
DROP SEQUENCE service_types_seq;
DROP SEQUENCE services_seq;
DROP SEQUENCE payments_seq;
DROP SEQUENCE hardware_seq;
DROP SEQUENCE info_cat_seq;
DROP SEQUENCE info_attr_seq;
DROP SEQUENCE plans_seq;

-- DROP OBJECTS [END] --

-- CREATE TABLES [START] --

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


CREATE TABLE plans (
  id NUMBER(10) NOT NULL,
  name VARCHAR(200) NOT NULL,
  price FLOAT DEFAULT 0
  , CONSTRAINT plans_pk PRIMARY KEY (id)
  , CONSTRAINT plans_name_uk UNIQUE (name)
  , CONSTRAINT plans_price_not_negative CHECK (price >= 0)
); 


CREATE TABLE service_types (
  id NUMBER(10) NOT NULL,
  name VARCHAR(200) NOT NULL
  , CONSTRAINT service_types_pk PRIMARY KEY (id)
  , CONSTRAINT service_types_name_uk UNIQUE (name)
); 


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


CREATE TABLE payments (
  id NUMBER(10) NOT NULL,
  account_id NUMBER(10) NOT NULL,
  period_start DATE NOT NULL,
  period_end DATE NOT NULL,
  due_date DATE NOT NULL,
  summa FLOAT DEFAULT 0,
  status VARCHAR2(50) DEFAULT 'Not Paid',
  "COMMENT" VARCHAR2(200)
  , CONSTRAINT payments_pk PRIMARY KEY (id)
  , CONSTRAINT payments_account_id_fk
      FOREIGN KEY (account_id)
      REFERENCES accounts (id)
  , CONSTRAINT payments_status_values_chk CHECK (status IN ('Paid', 'Not Paid'))
  , CONSTRAINT payments_period_chk CHECK (period_start <= period_end)
); 
    

CREATE TABLE hardware (
  id NUMBER(10) PRIMARY KEY NOT NULL,
  account_id NUMBER(10) REFERENCES accounts (id),
  service_type_id NUMBER(10) REFERENCES service_types (id),
  name VARCHAR2(200) NOT NULL,
  serial_number VARCHAR2(200) NOT NULL,
  status VARCHAR2(50) DEFAULT 'Inactive'
  , CONSTRAINT hw_status_values_chk check (status in ('Active', 'Inactive'))
);


CREATE TABLE info_categories (
  id NUMBER(10) NOT NULL,
  name VARCHAR(200) NOT NULL
  , CONSTRAINT info_categories_pk PRIMARY KEY (id)
  , CONSTRAINT info_categories_name_uk UNIQUE (name)
); 


CREATE TABLE info_attributes (
  id NUMBER(10) NOT NULL,
  name VARCHAR(200) NOT NULL
  , CONSTRAINT info_attributes_pk PRIMARY KEY (id)
  , CONSTRAINT info_attributes_uk UNIQUE (name)
); 


CREATE TABLE information (
  attr_id NUMBER(10) NOT NULL,
  category_id NUMBER(10) NOT NULL,
  value VARCHAR2(2000)
  , CONSTRAINT information_attr_id_fk
      FOREIGN KEY (attr_id)
      REFERENCES info_attributes (id)
  , CONSTRAINT information_cat_id_fk
      FOREIGN KEY (category_id)
      REFERENCES info_categories (id)
);


CREATE TABLE internet_service_options (
  service_id NUMBER(10) NOT NULL,
  download_speed VARCHAR(20) NOT NULL,
  upload_speed VARCHAR(20) NOT NULL,
  data_limit VARCHAR(20) NOT NULL
  , CONSTRAINT int_serv_opt_fk
      FOREIGN KEY (service_id)
      REFERENCES services (id)
); 


CREATE TABLE tv_service_options (
  service_id NUMBER(10) NOT NULL,
  channels_count NUMBER(5) NOT NULL,
  uhd_support VARCHAR(10) NOT NULL
  , CONSTRAINT tv_serv_opt_fk
      FOREIGN KEY (service_id)
      REFERENCES services (id)
  , CONSTRAINT tv_serv_opt_uhd_values CHECK (uhd_support IN ('On', 'Off'))
);


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

-- CREATE TABLES [END] --


--- CREATE TRIGGERS AND SEQUENCES [START] --

--DROP SEQUENCE accounts_seq;

CREATE SEQUENCE accounts_seq
  START WITH 1
  INCREMENT BY 1
  CACHE 100;
/

CREATE OR REPLACE TRIGGER accounts_insert_trigger
  BEFORE INSERT ON accounts
  FOR EACH ROW
BEGIN
  SELECT accounts_seq.nextval
    INTO :new.id
    FROM dual;
END;
/

CREATE SEQUENCE service_types_seq
  START WITH 10
  INCREMENT BY 1
  CACHE 100;
/

CREATE OR REPLACE TRIGGER service_types_insert_trigger
  BEFORE INSERT ON service_types
  FOR EACH ROW
BEGIN
  SELECT service_types_seq.nextval
    INTO :new.id
    FROM dual;
END;
/

CREATE SEQUENCE services_seq
  START WITH 100
  INCREMENT BY 1
  CACHE 100;
/

CREATE OR REPLACE TRIGGER services_insert_trigger
  BEFORE INSERT ON services
  FOR EACH ROW
BEGIN
  SELECT services_seq.nextval
    INTO :new.id
    FROM dual;
END;
/

CREATE SEQUENCE payments_seq
  START WITH 1000
  INCREMENT BY 1
  CACHE 100;
/

CREATE OR REPLACE TRIGGER payments_insert_trigger
  BEFORE INSERT ON payments
  FOR EACH ROW
BEGIN
  SELECT payments_seq.nextval
    INTO :new.id
    FROM dual;
END;
/

CREATE SEQUENCE hardware_seq
  START WITH 10000
  INCREMENT BY 1
  CACHE 100;
/

CREATE OR REPLACE TRIGGER hardware_insert_trigger
  BEFORE INSERT ON hardware
  FOR EACH ROW
BEGIN
  SELECT hardware_seq.nextval
    INTO :new.id
    FROM dual;
END;
/

CREATE SEQUENCE plans_seq
  START WITH 20000
  INCREMENT BY 1
  CACHE 100;
/

CREATE OR REPLACE TRIGGER plans_insert_trigger
  BEFORE INSERT ON plans
  FOR EACH ROW
BEGIN
  SELECT plans_seq.nextval
    INTO :new.id
    FROM dual;
END;
/

CREATE SEQUENCE info_cat_seq
  START WITH 1
  INCREMENT BY 1
  CACHE 100;
/

CREATE OR REPLACE TRIGGER info_cat_insert_trigger
  BEFORE INSERT ON info_categories
  FOR EACH ROW
BEGIN
  SELECT info_cat_seq.nextval
    INTO :new.id
    FROM dual;
END;
/

CREATE SEQUENCE info_attr_seq
  START WITH 10
  INCREMENT BY 1
  CACHE 100;
/

CREATE OR REPLACE TRIGGER info_attr_insert_trigger
  BEFORE INSERT ON info_attributes
  FOR EACH ROW
BEGIN
  SELECT info_attr_seq.nextval
    INTO :new.id
    FROM dual;
END;
/



--- CREATE TRIGGERS AND SEQUENCES [END] --


-- INSERT DATA [START] --

INSERT INTO accounts(login, password, first_name, last_name, status, email, address) VALUES('test', 'test', 'Vasia', 'Pupkin', 'Active', 'test@gmail.com', 'New York');
INSERT INTO accounts(login, password, first_name, last_name, status, email, address) VALUES('test2', 'test2', 'John', 'Smith', 'Active', 'test2@gmail.com', 'Chicago');
INSERT INTO accounts(login, password, first_name, last_name, status, email, address) VALUES('guest', 'guest', 'Bruce', 'Lee', 'Inactive', 'brlee@gmail.com', 'Japan');
INSERT INTO accounts(login, password, first_name, last_name, status, email, address) VALUES('phoneuser', '123', 'Kate', 'Radistka', 'Active', 'phuser@gmail.com', 'Canada');

INSERT INTO plans(name, price) VALUES( 'Internet Plan 1', 50.0);
INSERT INTO plans(name, price) VALUES('Internet Plan 2', 100.0);
INSERT INTO plans(name, price) VALUES('TV Plan 1', 45.0);
INSERT INTO plans(name, price) VALUES('TV Plan 2', 90.0);
INSERT INTO plans(name, price) VALUES('Phone Plan 1', 35.0);
INSERT INTO plans(name, price) VALUES('Phone Plan 2', 70.0);
INSERT INTO plans(name, price) VALUES('Phone Plan 3', 80.0);


INSERT INTO service_types(name) VALUES('Internet');
INSERT INTO service_types(name) VALUES('TV');
INSERT INTO service_types(name) VALUES('Phone');


INSERT INTO services(name, type_id, plan_id) VALUES('Internet Service 1', 10, 20000);
INSERT INTO services(name, type_id, plan_id) VALUES('Internet Service 2', 10, 20001);
INSERT INTO services(name, type_id, plan_id) VALUES('TV Service 1', 11, 20002);
INSERT INTO services(name, type_id, plan_id) VALUES('TV Service 2', 11, 20003);
INSERT INTO services(name, type_id, plan_id) VALUES('Phone Service 1', 12, 20004);
INSERT INTO services(name, type_id, plan_id) VALUES('Phone Service 2', 12, 20005);
INSERT INTO services(name, type_id, plan_id) VALUES('Phone Service 3', 12, 20006);


INSERT INTO client_services VALUES(1, 100); -- Internet Service 1

INSERT INTO client_services VALUES(2, 101); -- Internet Service 2
INSERT INTO client_services VALUES(2, 102); -- TV Service 1

INSERT INTO client_services VALUES(3, 100); -- Internet Service 1
INSERT INTO client_services VALUES(3, 102); -- TV Service 1
INSERT INTO client_services VALUES(3, 104); -- Phone Service 1

INSERT INTO client_services VALUES(4, 105); -- Phone Service 2


INSERT INTO payments(account_id, period_start, period_end, due_date, summa, status, "COMMENT")
    VALUES(1, ADD_MONTHS(sysdate, -5), ADD_MONTHS(sysdate, -4), ADD_MONTHS(sysdate, -4), 100.0, 'Paid', 'Montly fee');
INSERT INTO payments(account_id, period_start, period_end, due_date, summa, status, "COMMENT")
    VALUES(1, ADD_MONTHS(sysdate, -4), ADD_MONTHS(sysdate, -3), ADD_MONTHS(sysdate, -3), 100.0, 'Paid', 'Montly fee');
INSERT INTO payments(account_id, period_start, period_end, due_date, summa, status, "COMMENT")
    VALUES(1, ADD_MONTHS(sysdate, -3), ADD_MONTHS(sysdate, -2), ADD_MONTHS(sysdate, -2), 100.0, 'Paid', 'Montly fee');
INSERT INTO payments(account_id, period_start, period_end, due_date, summa, status, "COMMENT")
    VALUES(1, ADD_MONTHS(sysdate, -2), ADD_MONTHS(sysdate, -1), ADD_MONTHS(sysdate, -1), 100.0, 'Paid', 'Montly fee');
INSERT INTO payments(account_id, period_start, period_end, due_date, summa, status, "COMMENT")
    VALUES(1, ADD_MONTHS(sysdate, -1), sysdate, sysdate, 100.0, 'Not Paid', 'Montly fee');

-- Internet hardware
INSERT INTO hardware(account_id, service_type_id, name, serial_number, status)
    VALUES(1, 10, 'Internet Modem 1', 'SN123', 'Active');
INSERT INTO hardware(service_type_id, name, serial_number, status)
    VALUES(10, 'Internet Modem 2', 'SN124', 'Inactive');
INSERT INTO hardware(account_id, service_type_id, name, serial_number, status)
    VALUES(2, 10, 'Internet Modem 3', 'SN125', 'Active');
INSERT INTO hardware(account_id, service_type_id, name, serial_number, status)
    VALUES(3, 10, 'Internet Modem 4', 'SN126', 'Active');
    
-- TV hardware
INSERT INTO hardware(service_type_id, name, serial_number, status)
    VALUES(11, 'TV Modem 1', 'SN00178', 'Inactive');
INSERT INTO hardware(account_id, service_type_id, name, serial_number, status)
    VALUES(2, 11, 'TV Modem 2', 'SN00179', 'Active');
INSERT INTO hardware(account_id, service_type_id, name, serial_number, status)
    VALUES(3, 11, 'TV Modem 3', 'SN00180', 'Active');

-- Phone hardware
INSERT INTO hardware(account_id, service_type_id, name, serial_number, status)
    VALUES(3, 12, 'Phone Device 1', 'PH0001', 'Active');
INSERT INTO hardware(account_id, service_type_id, name, serial_number, status)
    VALUES(4, 12, 'Phone Device 2', 'PH0002', 'Active');
INSERT INTO hardware(service_type_id, name, serial_number, status)
    VALUES(12, 'Phone Device 3', 'PH003', 'Inactive');
    
    
INSERT INTO info_categories(name) VALUES('Internet');
INSERT INTO info_categories(name) VALUES('TV');
INSERT INTO info_categories(name) VALUES('Phone');
INSERT INTO info_categories(name) VALUES('Billing');
INSERT INTO info_categories(name) VALUES('General');  


INSERT INTO info_attributes(name) VALUES('Phone Number');
INSERT INTO info_attributes(name) VALUES('Email');
INSERT INTO info_attributes(name) VALUES('Facebook');
INSERT INTO info_attributes(name) VALUES('VK');


INSERT INTO information VALUES(10, 1, '(403) 123-0001');
INSERT INTO information VALUES(11, 1, 'internet_support@gmail.com');

INSERT INTO information VALUES(10, 2, '(403) 123-0002');
INSERT INTO information VALUES(11, 2, 'tv_support@gmail.com');

INSERT INTO information VALUES(10, 3, '(403) 123-0003');
INSERT INTO information VALUES(11, 3, 'phone_support@gmail.com');

INSERT INTO information VALUES(10, 4, '(403) 123-0004');
INSERT INTO information VALUES(11, 4, 'billing_support@gmail.com');

INSERT INTO information VALUES(12, 5, 'tks-facebook');
INSERT INTO information VALUES(13, 5, 'vk-facebook');


INSERT INTO internet_service_options VALUES(100, '15 Mbps', '5 Mbps', '100 GB');
INSERT INTO internet_service_options VALUES(101, '75 Mbps', '15 Mbps', '500 GB');


INSERT INTO tv_service_options VALUES(102, 25, 'Off');
INSERT INTO tv_service_options VALUES(103, 50, 'On');


INSERT INTO phone_service_options VALUES(104, 100, 1, 'Off');
INSERT INTO phone_service_options VALUES(105, 500, 5, 'On');
INSERT INTO phone_service_options VALUES(106, 1000, 10, 'On');





-- INSERT DATA [END] --