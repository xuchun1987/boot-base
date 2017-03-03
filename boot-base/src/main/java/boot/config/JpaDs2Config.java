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
        entityManagerFactoryRef="entityManagerFactoryTwo",
        transactionManagerRef="transactionManagerTwo",
        basePackages= { "boot.dao.db2" })//设置Repository所在位置
public class JpaDs2Config {
	  @Autowired 
	  @Qualifier("twoDataSource")
	  private DataSource twoDataSource;

	    @Bean(name = "entityManagerTwo")
	    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
	        return entityManagerFactoryTwo(builder).getObject().createEntityManager();
	    }

	    @Bean(name = "entityManagerFactoryTwo")
	    public LocalContainerEntityManagerFactoryBean entityManagerFactoryTwo (EntityManagerFactoryBuilder builder) {
	        return builder
	                .dataSource(twoDataSource)
	                .properties(getVendorProperties(twoDataSource))
	                .packages("boot.dao.db2") //设置实体类所在位置
	                .persistenceUnit("secondPersistenceUnit")
	                .build();
	    }

	    @Autowired
	    private JpaProperties jpaProperties;

	    private Map<String, String> getVendorProperties(DataSource dataSource) {
	        return jpaProperties.getHibernateProperties(dataSource);
	    }

	    @Bean(name = "transactionManagerTwo")
	    public PlatformTransactionManager transactionManagerTwo(EntityManagerFactoryBuilder builder) {
	        return new JpaTransactionManager(entityManagerFactoryTwo(builder).getObject());
	    }
}
