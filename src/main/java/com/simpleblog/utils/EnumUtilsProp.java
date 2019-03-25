package com.simpleblog.utils;

public enum EnumUtilsProp {
	DB_DRIVER,
	DB_URL,
	DB_USERNAME,
	DB_PASSWORD,
	HIBERNATE_DIALECT,
	HIBERNATE_SHOW_SQL,
	PACKAGES_TO_SCAN;
	
	public String getString() {
		switch(this) {
			case DB_DRIVER : return "db.driver";
			case DB_URL : return "db.url";
			case DB_USERNAME : return "db.username";
			case DB_PASSWORD : return "db.password";
			case HIBERNATE_DIALECT : return "hibernate.dialect";
			case HIBERNATE_SHOW_SQL : return "hibernate.show_sql";
			case PACKAGES_TO_SCAN : return "entitymanager.packages.to.scan";
			default : return "";
		}
	}
}
