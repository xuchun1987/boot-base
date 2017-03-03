package boot.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourceConfig {

	
	 	@Bean(name = "oneDataSource")
	    @Qualifier("oneDataSource")
	    @ConfigurationProperties(prefix="spring.datasource.one")
	    public DataSource primaryDataSource() {
	        return DataSourceBuilder.create().build();
	    }

	    @Bean(name = "twoDataSource")
	    @Qualifier("twoDataSource")
	    @Primary
	    @ConfigurationProperties(prefix="spring.datasource.two")
	    public DataSource secondaryDataSource() {
	        return DataSourceBuilder.create().build();
	    }
}
