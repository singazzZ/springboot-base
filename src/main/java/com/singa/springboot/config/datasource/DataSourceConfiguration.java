package com.singa.springboot.config.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author Singa
 * @version 1.0.0
 * @filename DataBaseConfiguration.java
 * @time 2018年12月26日 上午10:06:17
 * @copyright(C) 2018 深圳市北辰德科技股份有限公司
 */
@Configuration
@PropertySource("classpath:datasources.properties")
public class DataSourceConfiguration implements EnvironmentAware {

	private Environment environment;
	
	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}
	
	@Primary
    @Bean(name = "primaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DruidDataSource writeDataSource() {
    	DruidDataSource druid = new DruidDataSource();
		druid.setUrl(environment.getProperty("spring.datasource.primary.url"));
		druid.setUsername(environment.getProperty("spring.datasource.primary.username"));
		druid.setPassword(environment.getProperty("spring.datasource.primary.password"));
		druid.setDriverClassName(environment.getProperty("spring.datasource.primary.driver-class-name"));
		return druid;
    }
    

//	@Bean(name = "secondaryDataSource")
//	@ConfigurationProperties(prefix = "spring.datasource")
//	public DruidDataSource secondaryDataSource() {
//		DruidDataSource druid = new DruidDataSource();
//		druid.setUrl(environment.getProperty("spring.datasource.secondary.url"));
//		druid.setUsername(environment.getProperty("spring.datasource.secondary.username"));
//		druid.setPassword(environment.getProperty("spring.datasource.secondary.password"));
//		druid.setDriverClassName(environment.getProperty("spring.datasource.secondary.driver-class-name"));
//		return druid;
//	}



	
}
