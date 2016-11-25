package cz.muni.fi.pa165.mapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.SchedulingException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Implementation of BeanMappingService
 * @author dubnickaf@gmail.com [445647]
 */
@Service
public class BeanMappingServiceImpl implements BeanMappingService{

    @Inject
    private Mapper dozer;

    @Override
    public  <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass) {
        if (objects == null) throw new IllegalArgumentException("null object inserted into mapTo");
        if (dozer == null)throw new NullPointerException("injected dozer is null");

        List<T> mappedCollection = new ArrayList<T>();
        for (Object object : objects) {
            mappedCollection.add(dozer.map(object, mapToClass));
        }
        return mappedCollection;
    }

    @Override
    public  <T> T mapTo(Object u, Class<T> mapToClass)
    {
        if (u == null) throw new IllegalArgumentException("null object inserted into mapTo");
        if (dozer == null)throw new NullPointerException("injected dozer is null");
        return dozer.map(u, mapToClass);
    }

    @Override
    public Mapper getMapper(){
        return dozer;
    }
}
