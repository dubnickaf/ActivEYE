package cz.muni.fi.pa165.activeye.config;


import cz.muni.fi.pa165.activeye.PersistenceContext;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Configuration for service provider (thru dozer)
 * @author dubnickaf@gmail.com [445647]
 */
@Configuration
@EnableAspectJAutoProxy
@Import(PersistenceContext.class)
@ComponentScan(basePackages="cz.muni.fi.pa165.activeye")
public class ServiceConfiguration {


    @Bean
    public Mapper dozer(){
        List<String> mappingFiles = new ArrayList<String>();
        mappingFiles.add("dozerJdk8Converters.xml");
        DozerBeanMapper dozer = new DozerBeanMapper();
        dozer.setMappingFiles(mappingFiles);
        return dozer;
    }


}