-- 1
select max(salary) as "최고임금", min(salary) as "최저임금", max(salary)-min(salary) as "최고임금-최저임금"
	from salaries;
    

-- 2    
select date_format( max(hire_date), '%Y년 %m월 %d일')
	from employees;


-- 3
select date_format(min(from_date),'%Y년 %m월 %d일'), datediff( if(max(to_date)='9999-01-01',CURRENT_DATE,max(to_date)), min(from_date)) as work_date
from salaries
group by emp_no
order by work_date desc limit 1 ;

-- select emp_no, min(from_date),  max( if(to_date='9999-01-01',CURRENT_DATE,to_date) ), max(datediff( if(to_date='9999-01-01',CURRENT_DATE,to_date), from_date)) as work_date
-- from salaries
-- group by emp_no
-- order by work_date desc ;

-- select emp_no,min(from_date), datediff( if(max(to_date)='9999-01-01',CURRENT_DATE,max(to_date)), min(from_date)) as work_date
-- from salaries
-- group by emp_no
-- order by work_date desc limit 1 ;


-- 4
select avg(salary)
	from salaries
    where to_date='9999-01-01';
    

-- 5
select min(salary), max(salary)
	from salaries
    where to_date='9999-01-01';


-- 6
SELECT max(DATEDIFF('2019-05-07', STR_TO_DATE(e.birth_date, '%Y-%m-%d'))/365) AS "최고령", min(DATEDIFF('2019-05-07', STR_TO_DATE(e.birth_date, '%Y-%m-%d'))/365) AS "최연소"
  FROM employees e;

-- select min(birth_date), max(birth_date)
-- 	from employees;