package com.data.consumer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@SpringBootApplication(exclude = JpaRepositoriesAutoConfiguration.class)
@EnableTransactionManagement

public class DataConsumerApplication {
	@Autowired
	private DataSource dataSource;

	public static void main(String[] args) {
		SpringApplication.run(DataConsumerApplication.class, args);
	}

	@Bean
	public LocalSessionFactoryBean createSessionFactory()
	{
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setPackagesToScan("com.data.consumer.entity");
		factoryBean.setDataSource(dataSource);
		return factoryBean;
	}
	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(createSessionFactory().getObject());
		return transactionManager;
	}
}
