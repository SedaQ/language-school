package com.fi.ls.rest.assembler;

import com.fi.ls.dto.student.StudentDTO;
import com.fi.ls.rest.controller.StudentsController;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.stereotype.Component;

/**
 * @author Lukáš Daubner (410034)
 *
 */
@Component
public class StudentResourceAssembler implements ResourceAssembler<StudentDTO, Resource<StudentDTO>> {

    @Override
    public Resource<StudentDTO> toResource(StudentDTO studentDTO) {
        Resource<StudentDTO> studentResource = new Resource<>(studentDTO);

        try {
            studentResource.add(linkTo(StudentsController.class).slash(studentDTO.getId()).withSelfRel());
            studentResource.add(linkTo(StudentsController.class).slash(studentDTO.getId()).slash("lectures").withRel("lectures"));
            studentResource.add(linkTo(StudentsController.class).slash(studentDTO.getId()).withRel("DELETE"));
            studentResource.add(linkTo(StudentsController.class).slash("create").withRel("POST"));
            studentResource.add(linkTo(StudentsController.class).slash("update").withRel("PUT"));

        } catch (Exception ex) {
            Logger.getLogger(StudentResourceAssembler.class.getName()).log(Level.SEVERE, "could not link resource from StudentsController", ex);
        }

        return studentResource;
    }
}
