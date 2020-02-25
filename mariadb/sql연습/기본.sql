-- 1 
 select concat(first_name,' ',last_name) as '이름'
  from employees
  where emp_no=10944;

-- 2    
select concat(first_name , ' ' , last_name) as '이름',
      gender as '성별', 
      hire_date as '입사일'
  from employees
   rder by hire_date;

-- 3    
select gender, count(*)
  from employees
  group by gender;

-- 4    
select count(*)
  from salaries
  where to_date > now();
  group by emp_no;

-- 5   
select count(*)
  from departments;

-- 6
select count(c.emp_no)
  from (
        select emp_no
          from dept_manager
          group by emp_no
            ) as c;

-- 7    
select dept_name 
  from departments
  order by length(dept_name) desc;

-- 8 --
select count(*)
  from salaries
  where to_date >= now()
      and salary >= 120000 ;

-- 9
select title
  from titles
  group by title
  order by length(title) desc;
 
-- 10   
select count(*)
  from titles
  where title like "%Engineer" 
    and to_date > now();
  
-- 11
select title, from_date
  from titles
  where emp_no = 13250
  order by from_date;