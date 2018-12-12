--- open database
.open 'c:\parkhomchuk\Repositories\VLPA_DEV\Java\ExpensesManager\res\expman.db'


select * from categories;

select * from expenses where category_id = 1;

select * from expense_pattern_types;

select * from expense_patterns;

select * from expenses 
where 
--category_id = 11 and
--merchant like 'THE CALGARY ZOO%' and
date between '2018-07-01' and '2018-07-31'
order by date
;