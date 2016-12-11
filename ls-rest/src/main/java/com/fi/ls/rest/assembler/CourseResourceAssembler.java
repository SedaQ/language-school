package com.fi.ls.rest.assembler;

import com.fi.ls.dto.course.CourseDTO;
import com.fi.ls.rest.controller.CoursesController;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * @author Lukáš Daubner (410034)
 *
 */
@Component
public class CourseResourceAssembler implements ResourceAssembler<CourseDTO, Resource<CourseDTO>> {

    @Override
    public Resource<CourseDTO> toResource(CourseDTO courseDTO) {
        Resource<CourseDTO> courseResource = new Resource<>(courseDTO);

        try {
            courseResource.add(linkTo(CoursesController.class).slash(courseDTO.getId()).withSelfRel());
            courseResource.add(linkTo(CoursesController.class).slash(courseDTO.getId()).slash("lectures").withRel("lectures"));
            courseResource.add(linkTo(CoursesController.class).slash(courseDTO.getId()).withRel("DELETE"));

        } catch (Exception ex) {
            Logger.getLogger(CourseResourceAssembler.class.getName()).log(Level.SEVERE, "could not link resource from CoursesController", ex);
        }

        return courseResource;
    }
}
