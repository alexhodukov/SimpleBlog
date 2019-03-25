package com.simpleblog.config;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.simpleblog.security.UserDetailsServiceImpl;
import com.simpleblog.service.ArticleService;
import com.simpleblog.service.UserService;
import com.simpleblog.utils.EnumUtilsProp;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
@EnableJpaRepositories("com.simpleblog.repository")
@ComponentScan("com.simpleblog")
public class DataConfig {
    
    @Bean
	public ArticleService articleService() {
		return new ArticleService();
	}
    
    @Bean UserService userService() {
    	return new UserService();
    }
    
    @Bean
    public UserDetailsService getUserDetailsService(){
        return new UserDetailsServiceImpl();
    }
    
    @Resource
    private Environment env;
    
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(env.getRequiredProperty(EnumUtilsProp.DB_DRIVER.getString()));
        dataSource.setUrl(env.getRequiredProperty(EnumUtilsProp.DB_URL.getString()));
        dataSource.setUsername(env.getRequiredProperty(EnumUtilsProp.DB_USERNAME.getString()));
        dataSource.setPassword(env.getRequiredProperty(EnumUtilsProp.DB_PASSWORD.getString()));

        return dataSource;
    }
    
    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put(EnumUtilsProp.HIBERNATE_DIALECT.getString(), 
        		env.getRequiredProperty(EnumUtilsProp.HIBERNATE_DIALECT.getString()));
        properties.put(EnumUtilsProp.HIBERNATE_SHOW_SQL.getString(), 
        		env.getRequiredProperty(EnumUtilsProp.HIBERNATE_SHOW_SQL.getString()));

        return properties;
    }
    
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = 
        		new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan(env.getRequiredProperty(EnumUtilsProp.PACKAGES_TO_SCAN.getString()));
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
