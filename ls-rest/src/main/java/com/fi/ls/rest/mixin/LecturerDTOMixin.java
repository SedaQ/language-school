package com.fi.ls.rest.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "passwordHash", "listOfLanguages", "listOfLectures" })
public class LecturerDTOMixin {
    
}

