CREATE database StudentRecords;
CREATE TABLE Students(
	studentID INT primary KEY auto_increment,
    firstName VARCHAR(50),
    lastName Varchar(50),
    age int,
    email varchar(100)
    );
    
INSERT INTO Students (firstName, lastName, age, email) VALUES 
('Ana', 'Martínez', 20, 'ana.martinez@example.com'),
('Luis', 'Gómez', 22, 'luis.gomez@example.com'),
('Carla', 'Fernández', 19, 'carla.fernandez@example.com'),
('Jorge', 'Ramírez', 21, 'jorge.ramirez@example.com'),
('Lucía', 'Pérez', 23, 'lucia.perez@example.com');

select * from students;

CREATE TABLE Courses (
    courseId INT PRIMARY KEY AUTO_INCREMENT,
    courseName VARCHAR(100),
    courseDescription TEXT,
    studentId INT,
    FOREIGN KEY (studentId) REFERENCES Students(studentID)
);

INSERT INTO Courses (courseName, courseDescription, studentId)
VALUES ('Matemáticas', 'Curso básico de álgebra y aritmética', 1),
		('Historia', 'Historia mundial desde la antigüedad hasta la era moderna', 2),
        ('Programación', 'Introducción a la programación con Python', 3),
        ('Biología', 'Estudio de los seres vivos y su entorno', 4),
        ('Física', 'Fundamentos de la física clásica', 5);

select * from Courses;

INSERT INTO Students (firstName, lastName, age, email) VALUES 
('Roberto', 'Mejia', 20, 'roberto.mejia@example.com'),
('Pedro', 'Pascal', 22, 'pedro.pascal@example.com');

select * from students;

update students SET age = 21 where studentID = 1;

select * from students;