package com.simpleblog.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
@PropertySource("classpath:token.properties")
@EnableJpaRepositories("com.simpleblog.repository")
public class DataConfig {
	
	@Bean
	public ConfigPropDatabase confiPropgDb() {
		return new ConfigPropDatabase();
	}
	
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(confiPropgDb().getDbDriver());
        dataSource.setUrl(confiPropgDb().getDbUrl());
        dataSource.setUsername(confiPropgDb().getDbUsername());
        dataSource.setPassword(confiPropgDb().getDbPassword());

        return dataSource;
    }
    
    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put(confiPropgDb().getDialect(), confiPropgDb().getDialect());
        properties.put(confiPropgDb().getShowSql(), confiPropgDb().getShowSql());

        return properties;
    }
    
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = 
        		new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan(confiPropgDb().getPackageToScan());
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        entityManagerFactoryBean.setJpaProperties(getHibernateProperties());

        return entityManagerFactoryBean;
    }
    
    @Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		return new HibernateJpaVendorAdapter();
	}

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory().getObject());
    }

    
}
