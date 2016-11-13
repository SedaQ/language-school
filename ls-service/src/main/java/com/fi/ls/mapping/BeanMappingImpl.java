package com.fi.ls.mapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;

/**
 * @author Pavel Šeda (441048)
 *
 */
@Service
public class BeanMappingImpl implements BeanMapping {

	@Inject
	private Mapper dozer;
        
        public BeanMappingImpl() {
        }
        
        public BeanMappingImpl(Mapper mapper) {
            this.dozer = mapper;
        }

	public <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass) {
		List<T> mappedCollection = new ArrayList<>();
		for (Object object : objects) {
			mappedCollection.add(dozer.map(object, mapToClass));
		}
		return mappedCollection;
	}

	public <T> Optional<T> mapTo(Object u, Class<T> mapToClass) {
		return Optional.of(dozer.map(u, mapToClass));
	}

	public Mapper getMapper() {
		return dozer;
	}

}
