package cz.muni.fi.pa165.activeye.mapping;

import org.dozer.Mapper;

import java.util.Collection;
import java.util.List;

/**
 * Interface of BeanMappingService
 * @author dubnickaf@gmail.com [445647]
 */
public interface BeanMappingService {

    <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);
    <T> T mapTo(Object u, Class<T> mapToClass);
    Mapper getMapper();
}
