package com.fi.ls.rest.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Lukáš Daubner (410034)
 *
 */
@JsonIgnoreProperties({ "passwordHash", "listOfLanguages", "listOfLectures", "userRole" })
public class LecturerDTOMixin {
    
}

