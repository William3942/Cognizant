### Table design and constraint choices:

The design approach focused on creating a clear relational structure with referential integrity and normalization. Three main tables were created: Employees, Departments, and Projects, each with a primary key (employeeId, departmentId, projectId). Foreign keys (departmentId in Employees and managerId in Projects) ensure valid associations between employees, departments, and project managers. Constraints such as UNIQUE on email prevent duplicate records. 

### Use of joins to retrieve data:

Joins were used to combine data across tables. For example, to display projects along with their managers names and department names, a JOIN was used between Projects, Employees, and Departments. This allowed us to efficiently retrieve cross-table data in a meaningful way.

### Use of views to simplify data access:

Views like ProjectDetailsView were created to combine key information from multiple tables into a single logical structure. Views made common queries much easier, reducing the complexity of SQL and avoiding repeated joins. This simplified reporting and increased team productivity.

### Challenges and resolutions:

One of the key challenges was managing foreign key constraints when deleting records. Another challenge was enforcing unique emails, solved with a UNIQUE constraint. Creating views also required careful naming and column consistency to avoid confusion in queries.