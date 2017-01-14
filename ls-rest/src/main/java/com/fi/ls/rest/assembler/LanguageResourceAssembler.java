package com.fi.ls.rest.assembler;

import com.fi.ls.dto.language.LanguageDTO;
import com.fi.ls.rest.controller.LanguagesController;
import com.fi.ls.rest.controller.LecturersController;
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
public class LanguageResourceAssembler implements ResourceAssembler<LanguageDTO, Resource<LanguageDTO>> {

    @Override
    public Resource<LanguageDTO> toResource(LanguageDTO languageDTO) {
        Resource<LanguageDTO> languageResource = new Resource<>(languageDTO);

        try {
            languageResource.add(linkTo(LanguagesController.class).slash(languageDTO.getId()).withSelfRel());
            languageResource.add(linkTo(LecturersController.class).slash(languageDTO.getLecturer().getId()).withRel("lecturer"));
            languageResource.add(linkTo(LanguagesController.class).slash(languageDTO.getId()).withRel("DELETE"));
            languageResource.add(linkTo(LanguagesController.class).slash("update").withRel("PUT"));

        } catch (Exception ex) {
            Logger.getLogger(LanguageResourceAssembler.class.getName()).log(Level.SEVERE, "could not link resource from LanguagesController", ex);
        }

        return languageResource;
    }
}
