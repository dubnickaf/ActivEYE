package cz.muni.fi.pa165.mapping;

import org.dozer.Mapper;

import java.util.Collection;
import java.util.List;

/**
 * Interface of BeanMappingService
 * @author dubnickaf@gmail.com [445647]
 */
public interface BeanMappingService {

    public  <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);
    public  <T> T mapTo(Object u, Class<T> mapToClass);
    public Mapper getMapper();
}
