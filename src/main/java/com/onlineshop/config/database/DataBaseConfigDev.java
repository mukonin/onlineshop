package com.onlineshop.config.database;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.annotation.Resource;
import javax.sql.DataSource;


@Configuration
@PropertySource(value = {"classpath:application-dev.properties"})
@Profile("dev")
public class DataBaseConfigDev extends DBConfig {

	@Resource
	private Environment environment;

	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty(PROP_DB_DRIVER));
		dataSource.setUrl(environment.getRequiredProperty(PROP_DB_URL));
		dataSource.setUsername(environment.getRequiredProperty(PROP_DB_USER));
		dataSource.setPassword(environment.getRequiredProperty(PROP_DB_PASSWORD));
		return dataSource;
	}
}
