package cz.muni.fi.pa165.activeye;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 * In memory Derby database bean configuration
 * @author dubnickaf@gmail.com [445647]
 */
@Configuration
public class InMemoryDatabaseSpring {

	@Bean
	public DataSource db(){
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.DERBY).build();
		return db;
	}
}
