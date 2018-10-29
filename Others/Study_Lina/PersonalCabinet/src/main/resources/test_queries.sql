select * from accounts;
select * from services;
select * from service_types;
select * from client_services;
select * from payments;
select * from hardware;
select * from plans;
select * from internet_service_options;
select * from tv_service_options;
select * from phone_service_options;
select * from information;
select * from info_attributes;
select * from info_categories;

select a.id as account_id, a.login, s.id as service_id, s.name as service_name, cs.status  as service_status
from client_services cs, accounts a, services s
where 
cs.status = 'Active'
and cs.account_id = a.id
and cs.service_id = s.id
;


