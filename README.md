# ToDoList API V1
## I. Overview
- Source code todo list
- Use Spring framework, access database use spring data jpa, desgin pattern spring MVC.
- Architecture 3 layer. Including controller ( get the request from client), service ( handle logic code ), repository ( access to database ).
- Use unit test for the 3 layer ( controller, service and repository ).
## II. Config source code
- Clone source code from repository on github
- Update file application.properties including username and password for the same your account my sql in the local computer.
## III. Building source
- Run file deployed.sh to building jar file and create images service from docker-compose.yml
- If do not success. Excute for step by step below there.
    + Step 1: From source run command: mvn clean package -DskipTests
    + Step 2: From source run command docker-compose -f docker-compose.yml up
    + Unless 1: From the terminal run command docker run mysql:8 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=todolist
    + Unless 2: From source run command docker-compose -f docker-compose.yml up and separated two line when we building.
### IV. Swagger API
- Swagger UI: http://localhost:8080/todolist/swagger-ui.html#
- Swagger Doc: http://localhost:8080/todolist/v2/api-docs
#### V. API for the project
1. API for the get list works
- Method: [GET]
- Request URL: http://localhost:8080/todolist/api/v1/work?pageNumber=1&sortBy=workName&sortDir=asc
2. API for the create work
- Method: [POST]
- Request URL: http://localhost:8080/todolist/api/v1/work
- Payload:
{
    "workName": "Reading a book",
    "startingDate": "2022-01-14T03:48:25",
    "endingDate":"2022-01-20T03:48:25",
    "status": "PLANNING"
}
3. API for the update work
- Method: [POST]
- Request URL: http://localhost:8080/todolist/api/v1/work
- Payload:
{
    "id": 2,
    "workName": "Playing football",
    "startingDate": "2022-02-01T03:48:25",
    "endingDate":"2022-02-10T03:48:25",
    "status": "COMPLETE"
}
4. API for the delete work
- Method: [DELETE]
- Request URL: http://localhost:8080/todolist/api/v1/work/5
