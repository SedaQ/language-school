package com.fi.ls.mapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import org.dozer.Mapper;

/**
 * @author Pavel Šeda (441048)
 *
 */
@Named
public class BeanMappingImpl implements BeanMapping {

    private Mapper dozer;
    
    @Inject
    public BeanMappingImpl(Mapper mapper) {
        this.dozer = mapper;
    }

    @Override
    public <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass) {
            List<T> mappedCollection = new ArrayList<>();
            for (Object object : objects) {
                    mappedCollection.add(dozer.map(object, mapToClass));
            }
            return mappedCollection;
    }

    @Override
    public <T> Optional<T> mapTo(Object u, Class<T> mapToClass) {
            return Optional.of(dozer.map(u, mapToClass));
    }

    @Override
    public Mapper getMapper() {
            return dozer;
    }

}
