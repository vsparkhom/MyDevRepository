select e.*, c.name as category_name, c.type as category_type, p_category.id as p_category_id, p_category.name as p_category_name
from expenses e
left join categories c
  on c.id = e.category_id
left join categories p_category
  on p_category.id = c.parent_id
where 
  e.purchase_date >= date('2024-12-01') 
  and e.purchase_date <  date('2025-01-01')
;


select 
  e.*, c.*
from expenses e
join cards c
  on c.id = e.card_id
where
  e.purchase_date >= date('2024-12-01') 
  and e.purchase_date <  date('2025-01-01')
  and category_id = 0
;