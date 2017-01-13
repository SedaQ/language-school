package com.fi.ls.facade;

import com.fi.ls.dto.language.LanguageDTO;
import com.fi.ls.entity.Language;
import com.fi.ls.exceptions.ServiceLayerException;
import com.fi.ls.mapping.BeanMapping;
import com.fi.ls.service.LanguageService;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lukas Daubner (410034)
 */
@Service
@Transactional
public class LanguageFacadeImpl implements LanguageFacade {

	private final Logger logger = LoggerFactory.getLogger(LanguageFacadeImpl.class);

	private LanguageService languageService;

	private BeanMapping beanMapping;

	@Inject
	public LanguageFacadeImpl(LanguageService languageService, BeanMapping beanMapping) {
		this.languageService = languageService;
		this.beanMapping = beanMapping;
	}

	@Override
	public Optional<LanguageDTO> createLanguage(LanguageDTO lan) {
		if (lan == null)
			throw new IllegalArgumentException("LanguageDTO parameter is null");

		Optional<Language> entity = beanMapping.mapTo(lan, Language.class);
		try {
			Language created = languageService.create(entity.get());
			return beanMapping.mapTo(created, LanguageDTO.class);
		} catch (ServiceLayerException | NoSuchElementException ex) {
			logger.warn("createLanguage method invokes exception: " + ex);
			return Optional.empty();
		}
	}

	@Override
	public Optional<LanguageDTO> getLanguageById(Long id) {
		if (id == null)
			throw new IllegalArgumentException("Id parameter is null");

		try {
			Language entity = languageService.findById(id);
			return beanMapping.mapTo(entity, LanguageDTO.class);
		} catch (ServiceLayerException | NoSuchElementException ex) {
			logger.warn("getLanguageById method invokes exception: " + ex);
			return Optional.empty();
		}
	}

	@Override
	public Optional<LanguageDTO> updateLanguage(LanguageDTO lan) {
		if (lan == null)
			throw new IllegalArgumentException("LanguageDTO parameter is null");

		Optional<Language> entity = beanMapping.mapTo(lan, Language.class);
		try {
			Language updated = languageService.update(entity.get());
			return beanMapping.mapTo(updated, LanguageDTO.class);

		} catch (ServiceLayerException | NoSuchElementException ex) {
			logger.warn("updateLanguage method invokes exception: " + ex);
			return Optional.empty();
		}
	}

	@Override
	public Boolean deleteLanguage(LanguageDTO lan) {
		if (lan == null)
			throw new IllegalArgumentException("LanguageDTO parameter is null");

		Optional<Language> entity = beanMapping.mapTo(lan, Language.class);
		try {
			languageService.remove(entity.get());
			return true;
		} catch (ServiceLayerException | NoSuchElementException ex) {
			logger.warn("deleteLanguage method invokes exception: " + ex);
			return false;
		}
	}

	@Override
	public List<LanguageDTO> getAllLanguages() {
		try {
			Set<Language> entities = languageService.findAll();
			return beanMapping.mapTo(entities, LanguageDTO.class);
		} catch (ServiceLayerException | NoSuchElementException ex) {
			logger.warn("getAllLanguages method invokes exception: " + ex);
			return Collections.emptyList();
		}
	}
}
