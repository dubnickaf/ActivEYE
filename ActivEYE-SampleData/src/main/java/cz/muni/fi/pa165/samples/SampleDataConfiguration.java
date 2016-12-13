package cz.muni.fi.pa165.samples;

import cz.muni.fi.pa165.activeye.config.ServiceConfiguration;
import cz.muni.fi.pa165.samples.facade.SampleDataLoadFacade;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * Created by spriadka on 12/13/16.
 */
@Configuration
@Import(ServiceConfiguration.class)
@ComponentScan(basePackages = "cz.muni.fi.pa165.samples")
public class SampleDataConfiguration {
    @Inject
    private SampleDataLoadFacade loadFacade;

    @PostConstruct
    public void load(){
        loadFacade.loadData();
    }

}
