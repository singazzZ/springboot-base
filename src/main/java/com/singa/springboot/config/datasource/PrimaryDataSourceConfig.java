package com.singa.springboot.config.datasource;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
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

import com.singa.springboot.repository.extend.BaseRepositoryFactoryBean;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef="primaryEntityManagerFactory",
        transactionManagerRef="primaryTransactionManager",
		repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class,
        basePackages= { "com.singa.springboot.repository" })/**repository所在包名,多个用逗号分隔*/
public class PrimaryDataSourceConfig {
	
	// 实体类所在包名
	private final String[] entityPackages = new String[]{"com.singa.springboot.domain"};

	@Autowired 
	@Qualifier("primaryDataSource")
    private DataSource primaryDataSource;
	
	@Primary
	@Bean(name = "primaryEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory (EntityManagerFactoryBuilder builder) {
		return builder
				.dataSource(primaryDataSource)
				.properties(getVendorProperties(primaryDataSource))
				.packages(entityPackages)
				.persistenceUnit("primaryPersistenceUnit")
				.build();
	}
	
	@Primary
    @Bean(name = "primaryEntityManager")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return primaryEntityManagerFactory(builder).getObject().createEntityManager();
    }
    
    @Autowired
    private JpaProperties jpaProperties;

    private Map<String, Object> getVendorProperties(DataSource dataSource) {
    	return jpaProperties.getHibernateProperties(new HibernateSettings());
    }

    @Primary
    @Bean(name = "primaryTransactionManager")
    public PlatformTransactionManager primaryTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(primaryEntityManagerFactory(builder).getObject());
    }

	
}
