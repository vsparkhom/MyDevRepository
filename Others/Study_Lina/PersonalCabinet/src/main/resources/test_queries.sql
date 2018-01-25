select * from accounts;
select * from plans;
select * from service_types;
select * from services;
select * from client_services;
select * from payments;
select * from hardware;



select a.id as account_id, a.login, s.id as service_id, s.name as service_name, cs.status  as service_status
from client_services cs, accounts a, services s
where 
cs.status = 'Active'
and cs.account_id = a.id
and cs.service_id = s.id
;


