package com.onlineshop.config.database;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * Created by Adam on 08.07.2017.
 */
@Configuration
@PropertySource(value = "classpath:DataBaseProperties/application-dev.properties")
@Profile("dev")
public class DataBaseConfigDev {

	@Resource
	private Environment environment;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getProperty("db.driver"));
		dataSource.setUrl(environment.getProperty("db.url"));
		dataSource.setPassword(environment.getProperty("db.password"));
		dataSource.setUsername(environment.getProperty("db.user"));
		return dataSource;
	}
}
