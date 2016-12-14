# Language School WEB

This is short documentation for running web project

### Prerequisites
The project was built and tested with these version of mvn and JDK, so if you have any unexpected troubles let us know.

```
Apache Maven 3.3.9
Java version: 1.8.0_101, vendor: Oracle Corporation
```

### Installing project
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



