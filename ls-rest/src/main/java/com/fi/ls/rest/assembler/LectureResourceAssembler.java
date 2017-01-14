package com.fi.ls.rest.assembler;

import com.fi.ls.dto.lecture.LectureDTO;
import com.fi.ls.rest.controller.LecturesController;
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
public class LectureResourceAssembler implements ResourceAssembler<LectureDTO, Resource<LectureDTO>> {

    @Override
    public Resource<LectureDTO> toResource(LectureDTO lectureDTO) {
        Resource<LectureDTO> lectureResource = new Resource<>(lectureDTO);

        try {
            lectureResource.add(linkTo(LecturesController.class).slash(lectureDTO.getId()).withSelfRel());
            lectureResource.add(linkTo(LecturesController.class).slash(lectureDTO.getId()).slash("lecturers").withRel("lecturers"));
            lectureResource.add(linkTo(LecturesController.class).slash(lectureDTO.getId()).slash("courses").withRel("courses"));
            lectureResource.add(linkTo(LecturesController.class).slash(lectureDTO.getId()).slash("students").withRel("students"));
            lectureResource.add(linkTo(LecturesController.class).slash(lectureDTO.getId()).withRel("DELETE"));
            lectureResource.add(linkTo(LecturesController.class).slash("update").withRel("PUT"));

        } catch (Exception ex) {
            Logger.getLogger(LectureResourceAssembler.class.getName()).log(Level.SEVERE, "could not link resource from LecturesController", ex);
        }

        return lectureResource;
    }
}
