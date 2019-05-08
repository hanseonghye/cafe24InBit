-- 1
select count(*)
	from salaries
    where to_date = '9999-01-01'
		and salary > (	select avg(salary)
							from salaries
							where to_date='9999-01-01' );
                        
                        
-- 2
select e.emp_no, CONCAT(e.first_name, ' ', e.last_name) as '이름', d.dept_name
	from employees e, salaries s, dept_emp demp, departments d,
	 	( select demp.dept_no, max(s.salary) as max_salary 
			from employees e, salaries s, dept_emp demp
	    	where e.emp_no = s.emp_no
				and e.emp_no = demp.emp_no
		        and s.to_date = '9999-01-01'
		        and demp.to_date = '9999-01-01'
			group by demp.dept_no
			) dept_e
	where  e.emp_no = s.emp_no
		and s.to_date = '9999-01-01'
        and e.emp_no = demp.emp_no
        and demp.to_date = '9999-01-01'
        and s.salary = dept_e.max_salary
        and dept_e.dept_no = demp.dept_no
        and demp.dept_no = d.dept_no
	order by dept_e.max_salary;


-- 3
select e.emp_no, CONCAT(e.first_name, ' ', e.last_name) as '이름', s.salary
	from employees e, salaries s,
		(	select demp.dept_no, avg(s.salary) as avg_salary 
				from employees e, salaries s, dept_emp demp
				where e.emp_no = s.emp_no
					and e.emp_no = demp.emp_no
					and s.to_date = '9999-01-01'
					and demp.to_date = '9999-01-01'
				group by demp.dept_no
    		) avg_e
    where e.emp_no = s.emp_no
		and s.to_date = '9999-01-01'
        and s.salary > avg_e.avg_salary;

-- 4
select e.emp_no, CONCAT(e.first_name, ' ', e.last_name) as "이름", now_manager.manager_name, d.dept_name
	from employees e, departments d, dept_emp demp, 
		(	select CONCAT(e.first_name, ' ', e.last_name) as manager_name , m.dept_no 
				from dept_manager m, employees e 
				where m.to_date='9999-01-01' 
					and m.emp_no = e.emp_no
			) as now_manager
	where e.emp_no=demp.emp_no
		and demp.to_date = '9999-01-01'
	    and demp.dept_no = now_manager.dept_no
	    and demp.dept_no = d.dept_no;

-- 5
select e.emp_no , CONCAT(e.first_name, ' ', e.last_name) as "이름", t.title, s.salary
	from employees e, salaries s, titles t, departments d,
		(
			select d.dept_no
				from employees e, salaries s, dept_emp demp, departments d
			    where e.emp_no = demp.emp_no
					and demp.to_date = '9999-01-01'
			        and e.emp_no = s.emp_no
			        and s.to_date = '9999-01-01'
			        and demp.dept_no = d.dept_no
			    group by (demp.dept_no)
			    order by avg(s.salary)  desc limit 1
			) top_dept
	where e.dept_no = top_dept.dept_no
		and e.emp_no = s.emp_no
	    and e.emp_no = t.emp_no
	order by s.salary;


-- 6
select d.dept_name, avg(s.salary) as avg_salary
	from employees e, salaries s, dept_emp demp, departments d
    where e.emp_no = demp.emp_no
		and demp.to_date = '9999-01-01'
        and e.emp_no = s.emp_no
        and s.to_date = '9999-01-01'
        and demp.dept_no = d.dept_no
    group by (demp.dept_no)
    order by avg(s.salary)  desc limit 1;

-- 부서 이름만 출력하고 싶으면
select d.dept_name
	from employees e, salaries s, dept_emp demp, departments d
    where e.emp_no = demp.emp_no
		and demp.to_date = '9999-01-01'
        and e.emp_no = s.emp_no
        and s.to_date = '9999-01-01'
        and demp.dept_no = d.dept_no
    group by (demp.dept_no)
    order by avg(s.salary)  desc limit 1;


-- 7
select t.title, avg(s.salary) as avg_salary
	from employees e, titles t, salaries s
    where e.emp_no = s.emp_no
		and s.to_date = '9999-01-01'
        and e.emp_no = t.emp_no
	group by (t.title)
    order by avg(s.salary) desc limit 1;


-- 8
select d.dept_name, CONCAT(e.first_name, ' ', e.last_name) as "이름",s.salary ,now_manager.manager_name, now_manager.salary
	from employees e, departments d, dept_emp demp, 
	(	select CONCAT(e.first_name, ' ', e.last_name) as manager_name,s.salary , m.dept_no 
			from dept_manager m, employees e, salaries s 
			where m.to_date='9999-01-01' 
				and m.emp_no = e.emp_no 
				and m.emp_no=s.emp_no 
				and s.to_date='9999-01-01'
		) as now_manager
	where e.emp_no=demp.emp_no
		and demp.to_date = '9999-01-01'
	    and demp.dept_no = now_manager.dept_no
	    and demp.dept_no = d.dept_no
	    and s.salary > now_manager.salary;