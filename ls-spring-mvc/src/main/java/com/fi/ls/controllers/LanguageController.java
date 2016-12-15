package com.fi.ls.controllers;

import com.fi.ls.dto.language.LanguageDTO;
import com.fi.ls.dto.lecturer.LecturerCreateDTO;
import com.fi.ls.dto.lecturer.LecturerDTO;
import com.fi.ls.facade.LanguageFacade;
import com.fi.ls.facade.LecturerFacade;
import java.util.Optional;
import javax.inject.Inject;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Lukas Daubner (410034)
 */
@Controller
@RequestMapping("/language")
public class LanguageController {

	private final static Logger logger = LoggerFactory.getLogger(LanguageController.class);

	@Inject
	private LanguageFacade languageFacade;

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newLecturer(Model model) {
		logger.debug("new");
		model.addAttribute("language", new LanguageDTO());
		return "language/languageNew";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createLecturer(@Valid @ModelAttribute("language") LanguageDTO language,
			BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes,
			UriComponentsBuilder uriBuilder) {
		logger.debug("create");
		Optional<LanguageDTO> dto = languageFacade.createLanguage(language);
		return "redirect:" + uriBuilder.pathSegment("/lecturer/view/", dto.get().getLecturer().getId().toString()).buildAndExpand().encode().toUriString();
        }

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable long id, Model model) {
		model.addAttribute("language", languageFacade.getLanguageById(id));
		logger.debug("edit");
		return "language/languageEdit";
	}
        
        @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String updateStudent(@PathVariable Long id, @Valid @ModelAttribute("language") LanguageDTO language,
			BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes,
			UriComponentsBuilder uriBuilder) {
		logger.debug("update");
		Optional<LanguageDTO> dto = languageFacade.updateLanguage(language);
		return "redirect:" + uriBuilder.pathSegment("/lecturer/view/", dto.get().getLecturer().getId().toString()).buildAndExpand().encode().toUriString();
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder) {
		logger.debug("delete");
                languageFacade.deleteLanguage(languageFacade.getLanguageById(id).get());
		return "redirect:" + uriBuilder.pathSegment("/lecturer/view/", String.valueOf(id)).buildAndExpand().encode().toUriString();
	}
}
