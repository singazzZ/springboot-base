package com.singa.springboot.config.datasource;
 
//import java.util.Map;
//
//import javax.persistence.EntityManager;
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
//import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import com.singa.springboot.repository.extend.BaseRepositoryFactoryBean;
// 
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        entityManagerFactoryRef = "secondaryEntityManagerFactory",
//        transactionManagerRef = "secondaryTransactionManager",
//		repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class,
//        basePackages = {"com.singa.springboot.repository2"})/**repository所在包名,多个用逗号分隔*/
public class SecondaryDataSourceConfig {
 
//	// 实体类所在包名
//	private final String[] entityPackages = new String[]{"com.singa.springboot.domain2"};
//	
//	@Autowired 
//    @Qualifier("secondaryDataSource")
//    private DataSource secondaryDataSource;
//    
//    @Bean(name = "secondaryEntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean secondaryEntityManagerFactory(EntityManagerFactoryBuilder builder) {
//    	return builder
//    			.dataSource(secondaryDataSource)
//    			.packages(entityPackages)
//    			.persistenceUnit("secondaryPersistenceUnit")
//    			.properties(getVendorProperties())
//    			.build();
//    }
// 
//    @Bean(name = "secondaryEntityManager")
//    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
//        return secondaryEntityManagerFactory(builder).getObject().createEntityManager();
//    }
// 
//    @Autowired
//    private JpaProperties jpaProperties;
// 
//    private Map<String, Object> getVendorProperties() {
//        return jpaProperties.getHibernateProperties(new HibernateSettings());
//    }
// 
//    @Bean(name = "secondaryTransactionManager")
//    PlatformTransactionManager secondaryTransactionManager(EntityManagerFactoryBuilder builder) {
//        return new JpaTransactionManager(secondaryEntityManagerFactory(builder).getObject());
//    }
 
}
