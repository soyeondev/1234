package com.example.demo.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Database 설정 
 * @author jay
 */

@Configuration
public class DatabaseConfiguration {

	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseConfiguration.class);
	
	static {
		LOGGER.info("Configure Database[Mysql]");
	}

	public static final String className = "com.mysql.jdbc.Driver";
	
	/**
	 * TODO properties 또는 yml 적용  
	 */
	@Primary
	@Bean(name="dataSource")
   	public DataSource getDataSource() {
   		DriverManagerDataSource dataSource = new DriverManagerDataSource();
   		dataSource.setDriverClassName(className);
 		dataSource.setUrl("jdbc:mysql://localhost:3306/testdb?serverTimezone=UTC");
   		dataSource.setUsername("tiger");
   		dataSource.setPassword("1234");
   		return dataSource;
   	}
	
    @Bean(name="sqlSessionFactory")
   	public SqlSessionFactory sqlSessionFactory() throws Exception {
   		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
   		sessionFactoryBean.setDataSource(getDataSource());
   		sessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
   		return sessionFactoryBean.getObject();
   	}
    
}
