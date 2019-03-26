package com.simpleblog.config;

import org.springframework.beans.factory.annotation.Value;

public class ConfigPropertiesDatabase {
	@Value("${db.driver}")
	private String dbDriver;
	
	@Value("${db.url}")
	private String dbUrl;
	
	@Value("${db.username}")
	private String dbUsername;
	
	@Value("${db.password}")
	private String dbPassword;
	
	@Value("${hibernate.dialect}")
	private String dialect;
	
	@Value("${hibernate.show_sql}")
	private String showSql;
	
	@Value("${entitymanager.packages.to.scan}")
	private String packageToScan;

	public String getDbDriver() {
		return dbDriver;
	}

	public String getDbUrl() {
		return dbUrl;
	}

	public String getDbUsername() {
		return dbUsername;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public String getDialect() {
		return dialect;
	}

	public String getShowSql() {
		return showSql;
	}

	public String getPackageToScan() {
		return packageToScan;
	}

	
}
