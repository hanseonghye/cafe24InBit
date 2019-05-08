-- 1
select e.emp_no, concat(e.first_name, '', e.last_name) as '이름', s.salary
	from employees e, salaries s
    where e.emp_no = s.emp_no
		and s.to_date='9999-01-01'
    order by s.salary desc;
    
-- 2
select e.emp_no, concat(e.first_name, '', e.last_name) as emp_name, t.title
	from employees e, titles t
    where e.emp_no=t.emp_no
		and t.to_date='9999-01-01'
	order by emp_name;

-- 3
select e.emp_no, concat(e.first_name, '', e.last_name) as emp_name, d.dept_name
	from employees e, dept_emp demp, departments d
    where e.emp_no=demp.emp_no
		and demp.to_date='9999-01-01'
        and demp.dept_no=d.dept_no
	order by emp_name;
    
-- 4
select e.emp_no, concat(e.first_name, '', e.last_name) as emp_name, s.salary, t.title, d.dept_name
	from employees e, titles t, dept_emp demp, departments d, salaries s
    where e.emp_no=t.emp_no
		and t.to_date='9999-01-01'
        and s.to_date='9999-01-01'
        and demp.to_date='9999-01-01'
        and e.emp_no=s.emp_no
        and e.emp_no=demp.emp_no
		and demp.dept_no=d.dept_no
	order by emp_name;
    
-- 5
select e.emp_no, concat(e.first_name, '', e.last_name) as emp_name
		from employees e, titles t
        where e.emp_no=t.emp_no
            and t.title='Technique Leader'
            and not t.to_date='9999-01-01';

-- 6
 select e.last_name, d.dept_name, t.title
	from employees e, dept_emp demp, departments d, titles t
    where	e.last_name like "S%"
		and e.emp_no=demp.emp_no
		and demp.dept_no=d.dept_no
        and e.emp_no=t.emp_no;
        
-- 7
select e.emp_no, concat(e.first_name, '', e.last_name) as emp_name, s.salary
	from employees e, titles t, salaries s
    where t.to_date = '9999-01-01'
		and s.to_date = '9999-01-01'
		and t.title = 'Engineer'
        and t.emp_no = s.emp_no
		and s.salary >= 4000
        and t.emp_no = e.emp_no
	order by s.salary desc;
    
-- 8
select s.emp_no, s.salary, t.title
	from titles t, salaries s
    where s.to_date = '9999-01-01'
		and t.to_date = '9999-01-01'
		and s.salary > 5000
        and s.emp_no = t.emp_no
	order by s.salary desc;
    
-- 9
select d.dept_name, avg(s.salary)
	from dept_emp demp, departments d , salaries s
    where	demp.emp_no = s.emp_no
		and demp.dept_no=d.dept_no
		and	d.to_date = '9999-01-01'
		and s.to_date = '9999-01-01'
	group by d.dept_no
    order by avg(s.salary) desc;
    
-- 10
select t.title, avg(s.salary)
	from titles t, salaries s
    where t.emp_no = s.emp_no
		and t.to_date = '9999-01-01'
		and s.to_date = '9999-01-01'
	group by t.title
    order by avg(s.salary) desc;
    
