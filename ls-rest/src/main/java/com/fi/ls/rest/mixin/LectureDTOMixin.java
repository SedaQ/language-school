package com.fi.ls.rest.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "listOfLecturers", "listOfStudents", "listOfCourses" })
public class LectureDTOMixin {
    
}

