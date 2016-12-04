package com.fi.ls.rest.assembler;

import com.fi.ls.dto.language.LanguageDTO;
import com.fi.ls.rest.controller.LanguagesController;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
public class LanguageResourceAssembler implements ResourceAssembler<LanguageDTO, Resource<LanguageDTO>> {

    @Override
    public Resource<LanguageDTO> toResource(LanguageDTO languageDTO) {
        long id = languageDTO.getId();
        Resource<LanguageDTO> productResource = new Resource<>(languageDTO);

        try {
            productResource.add(linkTo(LanguagesController.class).slash(languageDTO.getId()).withSelfRel());
            productResource.add(linkTo(LanguagesController.class).slash(languageDTO.getId()).withRel("DELETE"));

        } catch (Exception ex) {
            Logger.getLogger(LanguageResourceAssembler.class.getName()).log(Level.SEVERE, "could not link resource from LanguagesController", ex);
        }

        return productResource;
    }
}
