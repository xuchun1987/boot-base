package boot.config;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef="entityManagerFactoryOne",
        transactionManagerRef="transactionManagerOne",
        basePackages= { "boot.dao.db1" })//设置Repository所在位置
public class JpaDs1Config {
	  @Autowired 
	  @Qualifier("oneDataSource")
	  private DataSource oneDataSource;

	    @Primary
	    @Bean(name = "entityManagerOne")
	    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
	        return entityManagerFactoryOne(builder).getObject().createEntityManager();
	    }
	    
	    @Primary
	    @Bean(name = "entityManagerFactoryOne")
	    public LocalContainerEntityManagerFactoryBean entityManagerFactoryOne (EntityManagerFactoryBuilder builder) {
	        return builder
	                .dataSource(oneDataSource)
	                .properties(getVendorProperties(oneDataSource))
	                .packages("boot.dao.db1") //设置实体类所在位置
	                .persistenceUnit("firstPersistenceUnit")
	                .build();
	    }

	    @Autowired
	    private JpaProperties jpaProperties;

	    private Map<String, String> getVendorProperties(DataSource dataSource) {
	        return jpaProperties.getHibernateProperties(dataSource);
	    }

	    @Primary
	    @Bean(name = "transactionManagerOne")
	    public PlatformTransactionManager transactionManagerOne(EntityManagerFactoryBuilder builder) {
	        return new JpaTransactionManager(entityManagerFactoryOne(builder).getObject());
	    }
}
