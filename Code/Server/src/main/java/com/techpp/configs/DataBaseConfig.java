package com.techpp.configs;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

//@Configuration
public class DataBaseConfig {

	public DataBaseConfig() {
		// TODO Auto-generated constructor stub
	}
	
//	@Bean
    public DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://http://192.168.10.14:3306/mydb");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
 
        return dataSource;
    }
	
}
