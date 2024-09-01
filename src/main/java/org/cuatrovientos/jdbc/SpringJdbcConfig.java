package org.cuatrovientos.jdbc;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan("org.cuatrovientos.jdbc")
public class SpringJdbcConfig {
	
		@Bean
		public DataSource mysqlDataSource() {
			
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
			dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
			dataSource.setUrl("jdbc:mysql://localhost:3306/product-manager");
			dataSource.setUsername("root");
			dataSource.setPassword("");
			
			return dataSource;		
			
		}
}