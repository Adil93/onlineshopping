package com.ecommerce.shoppingbackend.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = { "com.ecommerce.shoppingbackend.dto" })
@EnableTransactionManagement
public class HibernateConfig {

	// Change the below based on the DBMS we connect
	private static final String DATABASE_URL = "jdbc:h2:tcp://localhost/~/onlineshopping";
	private static final String DATABASE_DRIVER = "org.h2.Driver";
	private static final String DATABASE_DIALECT = "org.hibernate.dialect.H2Dialect";
	private static final String DATABASE_USERNAME = "sa";
	private static final String DATABASE_PASSWORD = "";
	
	// Datasource bean will be available
	@Bean
	public DataSource getDatasource() {
		BasicDataSource dataSource = new BasicDataSource();

		// providing database connection info
		dataSource.setDriverClassName(DATABASE_DRIVER);
		dataSource.setUrl(DATABASE_URL);
		dataSource.setUsername(DATABASE_USERNAME);
		dataSource.setPassword("");

		return dataSource;
	}

	// SessionFactory bean will be available
	@Bean
	public SessionFactory getSessionFactory(DataSource datasource) {
		LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(datasource);
		builder.addProperties(getHibernateProperties());
		builder.scanPackages("com.ecommerce.shoppingbackend.dto");
		return builder.buildSessionFactory();

	}

	// All the hibernate properties will be written in this method
	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", DATABASE_DIALECT);
		properties.put("hibernate.show_sql", "true");
		properties.put("format_sql", "true");
		return properties;
	}
	
	// Transaction Manager bean will be available 	 
	@Bean
	public HibernateTransactionManager getTransactionManager(SessionFactory sesseionFactory) 
	{
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sesseionFactory);
		return transactionManager;
	}

}
