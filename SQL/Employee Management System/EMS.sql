create database EmployeeManagement;
create table Employees (
	employeeId int primary key auto_increment,
    firstName varchar (50),
    lastName varchar(50),
    age int,
    email  varchar (100) unique,
    departmentId int,
    FOREIGN KEY (departmentId) REFERENCES Departments(departmentId)
    );
    
create table Departments (
	departmentId int primary key auto_increment,
    departmentName varchar(100)
    );
    
create table Projects(
	projectId int primary key auto_increment,
    projectName varchar(100),
    projectBudget  decimal,
    managerId int,
    foreign key (managerId) references Employees(employeeId)
);

INSERT INTO Departments (departmentName) values
('Human Resources'),
('Engineering'),
('Marketing');

select * from departments;

INSERT INTO Employees (firstName, lastName, age, email, departmentId) values
('Joaquin', 'Perez', 39, 'jperez@example.com', 1),
('Andrea', 'Ortiz', 30, 'aortiz@example.com', 2),
('Guillermo', 'Ortiz', 40, 'gortiz@example.com', 3),
('Jose', 'Robles', 50, 'jrobles@example.com', 2),
('Ana', 'Gonzalez', 45, 'agonzalez@example.com', 3);

select * from employees;

INSERT INTO Projects (projectName, projectBudget, managerId) values
('Web app marketing', 40000.00, 2),
('Web marketing', 7000.00, 3),
('Socio economical studies', 3000.00, 1);

Select e.firstName, e.lastName, d.departmentName From Employees e
Inner join departments d on e.departmentId = d.departmentId;

CREATE VIEW EmployeeDetails AS 
Select e.employeeId, e.firstName, e.lastName, d.departmentName From Employees e
Inner join departments d on e.departmentId = d.departmentId;

select projectName, projectBudget, managerId from Projects
where projectBudget > 1000;

Create view ActiveProjects as
select projectName, projectBudget, managerId from Projects
where projectBudget > 1000;