# EMS-v2

EMS-v2 is a comprehensive Employee Management System built with Spring Boot. It provides a backend API and a web-based dashboard to manage company personnel, including employees, applicants, payroll, departments, and positions.

## Features

*   **Employee Management**: Perform full CRUD (Create, Read, Update, Delete) operations on employee records.
*   **Applicant Tracking**: Manage job applicants from application to acceptance.
*   **Automated Onboarding**: Automatically convert "Accepted" applicants into new employee records and create their initial payroll entry.
*   **Payroll Management**: View and manage salary and payroll information for all employees.
*   **Department & Position Management**: Define and manage company departments and job positions.
*   **Secure API**: Endpoints are secured using JWT (JSON Web Tokens).
*   **Web Dashboard**: An integrated frontend built with Thymeleaf and JavaScript provides a user-friendly interface for:
    *   Viewing lists of employees, applicants, and payrolls with pagination.
    *   Viewing detailed information for individual employees.
    *   Navigating between different management modules.
*   **Authentication**: Features form-based login for the web dashboard and a token generation endpoint for API clients.

## Technologies Used

*   **Backend**: Java 25, Spring Boot 3.5.7
*   **Database**: Spring Data JPA, MS SQL Server
*   **Security**: Spring Security (Form-Based Login, OAuth2 Resource Server, JWT)
*   **Frontend**: Thymeleaf, HTML, CSS, JavaScript
*   **Build Tool**: Apache Maven
*   **Code Generation**: MapStruct for DTO-Entity mapping

## Getting Started

### Prerequisites

*   Java 25 JDK
*   Apache Maven
*   Microsoft SQL Server

### Database Setup

1.  Create a new database in your MS SQL Server instance. The application is configured to use the name `springbootdbems`.
2.  Update the database connection details in `src/main/resources/application.properties` with your SQL Server URL, username, and password.

    ```properties
    spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=springbootdbems;encrypt=false
    spring.datasource.username=your_sql_username
    spring.datasource.password=your_sql_password
    ```

3.  The application uses `spring.jpa.hibernate.ddl-auto=update`, which will automatically create and update the necessary tables when the application starts.

### JWT Configuration
The application requires RSA public and private keys for signing and verifying JWTs. Ensure you have `public.pem` and `private.pem` files in the `src/main/resources/certs/` directory.

### Running the Application

1.  Clone the repository:
    ```bash
    git clone https://github.com/minhtranc6/ems-v2.git
    cd ems-v2
    ```
2.  Run the application using the Maven wrapper:

    On macOS/Linux:
    ```bash
    ./mvnw spring-boot:run
    ```

    On Windows:
    ```bash
    mvnw.cmd spring-boot:run
    ```

3.  The application will start on `http://localhost:8080`.

### Accessing the Dashboard
Navigate to `http://localhost:8080` in your browser. You will be redirected to the login page. Use the following default credentials to log in:

*   **Username**: `ems@admin`
*   **Password**: `emsrebooted`

## API Endpoints

The application exposes the following RESTful API endpoints. They require a valid JWT Bearer token for access.

### Authentication
*   `POST /token`: Authenticate with username and password to generate a JWT.

### Employees
*   `GET /api/employees`: Get a paginated list of all employees.
*   `GET /api/employees/{id}`: Get a single employee by ID.
*   `PUT /api/employees/{id}`: Update an existing employee.
*   `DELETE /api/employees/{id}`: Delete an employee.

### Applicants
*   `GET /api/applicants`: Get a paginated list of all applicants.
*   `GET /api/applicants/{id}`: Get a single applicant by ID.
*   `POST /api/applicants`: Create a new applicant.
*   `PUT /api/applicants/{id}`: Update an existing applicant. If the status is changed to "Accepted", the applicant is automatically converted to an employee.
*   `DELETE /api/applicants/{id}`: Delete an applicant.

### Payrolls
*   `GET /api/payrolls`: Get a paginated list of all payroll records.
*   `GET /api/payrolls/{id}`: Get a single payroll record by ID.
*   `PUT /api/payrolls/{id}`: Update an existing payroll record.
*   `DELETE /api/payrolls/{id}`: Delete a payroll record.

### Departments
*   `GET /api/departments`: Get a list of all departments.
*   `GET /api/departments/{id}`: Get a single department by ID.
*   `POST /api/departments`: Create a new department.
*   `PUT /api/departments/{id}`: Update an existing department.
*   `DELETE /api/departments/{id}`: Delete a department.

### Positions
*   `GET /api/positions`: Get a list of all positions.
*   `GET /api/positions/{id}`: Get a single position by ID.
*   `POST /api/positions`: Create a new position.
*   `PUT /api/positions/{id}`: Update an existing position.
*   `DELETE /api/positions/{id}`: Delete a position.
