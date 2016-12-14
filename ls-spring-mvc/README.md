# Language School WEB

This is short documentation for running web project

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

After succesfully build, change directory to web module and run tomcat via:

```
> cd ls-spring-mvc
> ...\ls-spring-mvc\mvn tomcat7:run
or 
> ...\ls-spring-mvc\mvn
```

### Testing
For testing purposes there were created 3 different users in memory via ls-spring-data module. Each role has some differences in web view For example only admin could delete course, only student could enroll to course etc.

#### as admin:

```
username: test@email.cz
password: test1
```

#### as lecturer

```
username: testtest@email.cz
password: test2
```

#### as student

```
username: testtesttest@email.cz
password: test3
```


