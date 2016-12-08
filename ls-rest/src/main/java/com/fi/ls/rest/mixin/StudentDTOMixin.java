package com.fi.ls.rest.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "passwordHash", "listOfLectures" })
public class StudentDTOMixin {
    
}

