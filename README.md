# PA165 project: Language School

language school is one of possible projects to develop and demonstrate knowledge of topics introduced in course: [PA165](https://is.muni.cz/predmet/fi/podzim2016/PA165)

## Project description:

Create a system for language school lectures management. Each lecture can take place in a given day and time, and is related to some course. The course is defined by its (unique) name, language and proficiency level. However, each lecture will be independent. That means that each lecture can have different topic, it can be given by different lecturer and arbitrary number of students can enroll in it. Each lecturer will have name, surname, and record of taught languages. In addition, the lecturer will have indication that he/she is a native speaker.

## Authors

UCO | Name 
------------ | -------------
441048 | Šeda Pavel
410034 | Daubner Lukáš
422787 | Guoth Matúš
357293 | Nedbal	Marek

## Running Project &  Tests

put following command to cmd in language-school directory of project or mvn clean install it via IDE of your choice

```
mvn clean install
```

## REST API documentation

REST API documentation is in [REST module](https://github.com/SedaQ/language-school/tree/master/ls-rest) submodule of this project.

## Use Case & Class diagram for entities

Use Case & Class diagram are in [Wiki](https://github.com/SedaQ/language-school/wiki) folder of this project.
