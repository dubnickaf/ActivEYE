package cz.muni.fi.pa165.config;


import cz.muni.fi.pa165.facades.ActivityFacadeImpl;
import cz.muni.fi.pa165.facades.GroupFacadeImpl;
import cz.muni.fi.pa165.facades.RecordFacadeImpl;
import cz.muni.fi.pa165.facades.UserFacadeImpl;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import cz.muni.fi.pa165.activeye.PersistenceContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Configuration for service mapper (thru dozer)
 * @author dubnickaf@gmail.com [445647]
 */
@Configuration
@Import(PersistenceContext.class)
@ComponentScan(basePackageClasses={UserFacadeImpl.class, GroupFacadeImpl.class, ActivityFacadeImpl.class ,RecordFacadeImpl.class})
public class ServiceConfiguration {


    @Bean
    public Mapper dozer(){
        // should be needed to support Java 8 time api with Dozer
        List<String> mappingFiles = new ArrayList();
        mappingFiles.add("dozerJdk8Converters.xml");

        DozerBeanMapper dozer = new DozerBeanMapper();


        return dozer;
    }


}