/**
 * 
 */
package com.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author virens
 *
 */
@Configuration
@EnableTransactionManagement
@ComponentScan("com.config")
@PropertySource("classpath:application.properties")
public class DBConfig {

	@Autowired
	private Environment environment;

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(getDataSource());
		sessionFactory.setPackagesToScan("com.model");
		sessionFactory.setHibernateProperties(getHibernateProperties());
		return sessionFactory;
	}

	@Bean
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("database.driverClassName"));
		dataSource.setUrl(environment.getRequiredProperty("database.url"));
		dataSource.setUsername(environment.getRequiredProperty("database.username"));
		dataSource.setPassword(environment.getRequiredProperty("database.password"));
		return dataSource;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory s) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(s);
		return txManager;
	}
	
	private Properties getHibernateProperties() {
		Properties pr = new Properties();
		pr.setProperty("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
		pr.setProperty("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
		pr.setProperty("hibernate.connection.release_mode",
				environment.getRequiredProperty("hibernate.connection.release_mode"));
		pr.setProperty("hibernate.transaction.auto_close_session",
				environment.getRequiredProperty("hibernate.transaction.auto_close_session"));
		pr.setProperty("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
		pr.setProperty("hibernate.default_batch_fetch_size",
				environment.getRequiredProperty("hibernate.default_batch_fetch_size"));
		return pr;
	}
}
