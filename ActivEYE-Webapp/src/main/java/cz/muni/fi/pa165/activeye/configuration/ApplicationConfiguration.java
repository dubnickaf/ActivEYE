package cz.muni.fi.pa165.activeye.configuration;

import cz.muni.fi.pa165.activeye.config.ServiceConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by spriadka on 12/12/16.
 */
@Import(ServiceConfiguration.class)
@Configuration
public class ApplicationConfiguration  {
}
