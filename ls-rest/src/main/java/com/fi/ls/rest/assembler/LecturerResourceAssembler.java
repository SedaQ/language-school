package com.fi.ls.rest.assembler;

import com.fi.ls.dto.language.LanguageDTO;
import com.fi.ls.dto.lecture.LectureDTO;
import com.fi.ls.dto.lecturer.LecturerDTO;
import com.fi.ls.rest.controller.LanguagesController;
import com.fi.ls.rest.controller.LecturersController;
import com.fi.ls.rest.controller.LecturesController;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
public class LecturerResourceAssembler implements ResourceAssembler<LecturerDTO, Resource<LecturerDTO>> {

    @Override
    public Resource<LecturerDTO> toResource(LecturerDTO lecturerDTO) {
        long id = lecturerDTO.getId();
        Resource<LecturerDTO> lecturerResource = new Resource<>(lecturerDTO);

        try {
            LinkedList<Link> listOfLanguages = new LinkedList<>();
            for(LanguageDTO languageDTO : lecturerDTO.getListOfLanguages()) {
                listOfLanguages.add(linkTo(LanguagesController.class).slash(languageDTO.getId()).withRel("language"));
            }
            
            lecturerResource.add(listOfLanguages);
            LinkedList<Link> listOfLectures = new LinkedList<>();
            for(LectureDTO lectureDTO : lecturerDTO.getListOfLectures()) {
                listOfLectures.add(linkTo(LecturesController.class).slash(lectureDTO.getId()).withRel("lecture"));
            }
            
            lecturerResource.add(listOfLectures);
            lecturerResource.add(linkTo(LecturersController.class).slash(lecturerDTO.getId()).withSelfRel());
            lecturerResource.add(linkTo(LecturersController.class).slash(lecturerDTO.getId()).withRel("DELETE"));

        } catch (Exception ex) {
            Logger.getLogger(LecturerResourceAssembler.class.getName()).log(Level.SEVERE, "could not link resource from LecturerController", ex);
        }

        return lecturerResource;
    }
}
