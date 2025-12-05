
// create a table name product
create table  product (
product_id INT PRIMARY KEY,
product_name VARCHAR(255) NOT NULL,
product_type VARCHAR(255) NOT NULL,
product_price DECIMAL(10,2) NOT NULL
);


//view the table data 
select * from product;


//insert the value in the product table
insert into product values (1,'apple','fruit','100');


//insert multiple value in the product table
insert into product values
(1,'apple','fruit','100'),
(2,'orange','fruit','200'),
(3,'pineapple','fruit','330'),
(5,'blueberry','fruit','400'),
(6,'banana','fruit','500'),
(7,'lemon','fruit','600');

//update the value from the table
update product set product_name='Coconut' where product-id=1;

//Delete the data from the table
delete from product where product_id=8;

//adding a new column in the data base
alter table product
add status varchar(20)  default 'Active' not null;

//delete a column from the table
alter table product drop status;

//COMMON DATA TYPE EXPLANATIONS

| Type              | Description        | Example           |
| ----------------- | ------------------ | ----------------- |
| **TINYINT**       | Very small integer | -128 to 127       |
| **SMALLINT**      | Small integer      | -32,768 to 32,767 |
| **INT / INTEGER** | Standard integer   | -2.1B to 2.1B     |
| **BIGINT**        | Very large integer | ±9 quintillion    |

| Type        | Example  | Notes                                          |
| ----------- | -------- | ---------------------------------------------- |
| **CHAR(n)** | CHAR(10) | Always stores fixed length; padded with spaces |

| Type           | Example      | Notes                                       |
| -------------- | ------------ | ------------------------------------------- |
| **VARCHAR(n)** | VARCHAR(255) | Stores variable-length strings; most common |

| Type      | Description                         |
| --------- | ----------------------------------- |
| **TEXT**  | Large text (MySQL)                  |
| **CLOB**  | Character Large Object (Oracle)     |
| **NTEXT** | Unicode large text (SQL Server old) |



| Type          | Description                    |
| ------------- | ------------------------------ |
| **DATE**      | Stores only date (YYYY-MM-DD)  |
| **TIME**      | Stores only time (HH:MM:SS)    |
| **DATETIME**  | Date + time                    |
| **TIMESTAMP** | Date + time + timezone support |
| **INTERVAL**  | Duration (PostgreSQL/Oracle)   |

| Type               | Values              |
| ------------------ | ------------------- |
| **BOOLEAN / BOOL** | TRUE / FALSE / NULL |

| Type      | Description                               |
| --------- | ----------------------------------------- |
| **JSON**  | Stores JSON documents (MySQL, PostgreSQL) |
| **JSONB** | Binary optimized JSON (PostgreSQL)        |
| **XML**   | Stores XML documents                      |

I. Special Data Types
1. UUID / GUID

Globally unique 128-bit identifiers.
Types: UUID, UNIQUEIDENTIFIER.

2. ENUM / SET (MySQL)
   Type	Description
   ENUM	One value from predefined list
   SET	Multiple values from predefined list

3. ARRAY (PostgreSQL)

Arrays of any type: INT[], TEXT[].

4. MONEY / CURRENCY

Stores currency values (PostgreSQL, SQL Server).


//some important keys use in the database

✅ 1. Primary Key
A Primary Key uniquely identifies each record in a table.
Features
1.Must be unique
2.Cannot be NULL
3.Only one primary key per table
4.Often automatically indexed

✅ 2. Candidate Key
A candidate key is any attribute (or set of attributes) that can uniquely identify a row.
One of them becomes the primary key; others become alternate keys.

✅ 4. Composite Key
A key that consists of two or more columns to uniquely identify a row.

✅ 5. Foreign Key
A foreign key links one table to another.
It references the primary key of another table.
Features
1.Enforces referential integrity
2.Values must match existing values in the parent table
3.Can be NULL unless specified otherwise

✅ 6. Super Key
A set of attributes that uniquely identify a row.
💡 All candidate keys are super keys
But not all super keys are candidate keys because super keys may contain unnecessary attributes.
Example:
candidate key = email
super key = (email, phone_number)

✅ 7. Unique Key
A unique constraint ensures that values in a column are unique, but unlike primary keys:
A table can have multiple unique keys
Can allow one NULL value (depends on DBMS)
Example
email VARCHAR(100) UNIQUE

✅ 8. Surrogate Key
An artificial or system-generated key, not derived from business data.
Example
Auto-increment ID
UUID
Useful when natural keys are long or change frequently.

✅ 9. Natural Key
A key derived from real-world data.
Example
Email
Phone number
National ID
Natural keys can change → often replaced by surrogate keys in modern design.

create table employees(
emp_id int primary key,
emp_name varchar(100) not null,
dept_id int,
foreign key (dept_id) references departments(dept_id)
)


// inner join the database 

CREATE TABLE department (
dept_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
dept_name VARCHAR(100) NOT NULL
);

CREATE TABLE employee (
emp_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
emp_name VARCHAR(100) NOT NULL,
dept_id INT,
FOREIGN KEY (dept_id) REFERENCES department(dept_id)
);
//insert the data your self any inner join two table 
SeLECT e.emp_name, d.dept_name
FROM employee e
INNER JOIN department d
ON e.dept_id = d.dept_id;

//find the all stuents where enrollment is done
select s.name,s.email,s.age,e.is_active
from student s
inner join enrollment e
on s.id=e.id
where e.is_active ='true';


//find the all stuents where enrollment is  not done
select s.name,s.email,s.age,e.is_active
from student s
inner join enrollment e
on s.id=e.id
where e.is_active ='false';

//find the student with  enrollment is enrollment (yes/no)
select s.name,s.email,s.age,
case
when count(e.student_id)>0 then 'yes'
else 'no'
end as enrollment_status
from student s
left join enrollment e
on s.id=e.student_id
group by s.name,s.email,s.age;

//find the student without enrollment is enrollment (yes/no)
SELECT
s.name,
s.email,
s.age,
'NO' AS enrollment_status
FROM student s
LEFT JOIN enrollment e
ON s.id = e.student_id
WHERE e.student_id IS NULL;

//find the all course where enrollment is done
SELECT c.* course,e.is_active as entrollment_is_active
FROM course c
left join enrollment e
on c.id=e.course_id
where e.is_active='true' order by id asc

//find the all course where enrollment is done
SELECT c.* course,e.is_active as entrollment_is_active
FROM course c
left join enrollment e
on c.id=e.course_id
where e.is_active='false' order by id asc


//find the all course where enrollment is enrollment (yes/no)
select c.*course,
case
when (e.course_id)>0 then 'yes'
else 'no'
end as enrollment_status
from course c
left join enrollment e
on c.id=e.course_id


//find the all course where enrollment is enrollment (yes/no) only who is active
select c.*course,
case
when (e.course_id)>0 then 'yes'
else 'no'
end as enrollment_status
from course c
left join enrollment e
on c.id=e.course_id
where e.is_active='true' order by id asc

//find the all course where enrollment is enrollment ( yes / no ) only who is unactive 
select c.* course,
case
when (e.course_id)>0 then 'yes'
else 'no'
end as enrollment_status
from course c
left join  enrolment e
on  c.id=e.course_id;

//find the all student which relation with enrollment and course
select s.*student,e.is_active,c.title as course_title,
case
when (e.student_id)>0 then 'yes'
else 'no'
end as enrollment_status
from student s
inner join enrollment e
on s.id=e.Student_id
inner join course c
on c.id=e.course_id
where e.is_active='true' order by id asc  ---->   1.is_active='true'--> relation with all
                                                  2.is_active='false'-->  no relation with all


//find the all course which relation with enrollment and student
select c.*course,e.is_active,s.id as Student_id,s.name as Student_name
from course c
inner join enrollment e
on c.id=e.course_id
inner join student s
on s.id= e.student_id order by id asc

//1. List all students with total number of enrollments
select s.*,count(e.id)as total_enrollments
from student s
left join enrollment e
on s.id=e.student_id
group by s.id order by total_enrollments desc

//2. Find students who are enrolled in more than one course
select s.*, count(e.id) as total_enrollments
from student s
left join enrollment e
on s.id=e.student_id
group by s.id
having count (e.id)>1