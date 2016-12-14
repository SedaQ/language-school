# Language School REST API

This is short documentation for testing and using REST API in Language School project

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
or 
> ...\ls-rest\mvn
```

### Testing
For easy testing purpose in GUI we reccomend you to use Advanced REST client for Chrome browser. https://advancedrestclient.com/

### Course entity commands
#### find all courses

```
curl -i -X GET http://localhost:8080/pa165/rest/courses
```
#### or in more general
```
GET ~/courses
```
#### Find course by id
```
GET ~/courses/{id}
```
#### Delete specific course
```
DELETE ~/courses/{id}
```
#### Create new course
```
POST ~/courses/{id}
```
#### Update existing specific course
```
PUT ~/courses/{id}
```

