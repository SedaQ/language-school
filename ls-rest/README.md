# Language School REST API

This is short documentation for testing and using REST API in Language School project.
Language School has HATEOAS API for easy navigation and usage.

### Prerequisites
The project was built and tested with these version of mvn and JDK, so if you have any unexpected troubles let us know.

```
Apache Maven 3.3.9
Java version: 1.8.0_101, vendor: Oracle Corporation
```

### Installing & running the project
Clone the master branch of this project to your directory via command

```
git clone https://github.com/SedaQ/language-school.git
```

Run command prompt in project root directory and build project via command:

```
mvn clean install
```

After succesfully build, change directory to rest module and run tomcat via:

```
> cd ls-rest
> ...\ls-rest\mvn tomcat7:run

```

### Testing
For easy testing purpose in GUI we reccomend you to use Advanced REST client for Chrome browser. https://advancedrestclient.com/
You can also use Curl or Postman.

For 3rd milestone purposes, only Course entity is implemented in full range (GET, DELETE, POST, PUT). For other entities, there is only GET available.

### Course entity commands
#### Find all courses
```
GET ~/courses
curl -i -X GET http://localhost:8080/pa165/rest/courses
```
#### Find course by id
```
GET ~/courses/{id}
curl -i -X GET http://localhost:8080/pa165/rest/courses/1
```
#### Find all lectures of course
```
GET ~/courses/{id}/lectures
curl -i -X GET http://localhost:8080/pa165/rest/courses/1/lectures
```
#### Delete specific course
```
DELETE ~/courses/{id}
curl -i -X DELETE http://localhost:8080/pa165/rest/courses/{id}
```
#### Create new course
```
POST ~/courses/create
curl -X POST -i -H "Content-Type: application/json" --data '{"name":"test","language":"test","proficiencyLevel":"A1"}' http://localhost:8080/pa165/rest/courses/create
```
#### Update existing course
```
PUT ~/courses/update
curl -X PUT -i -H "Content-Type: application/json" --data '{"id":2,"name":"Updated","language":"Updated","proficiencyLevel":"B1"}' http://localhost:8080/pa165/rest/courses/update
```
NOTE: Depending of your enviroment, you might need to escape " and ' characters.
