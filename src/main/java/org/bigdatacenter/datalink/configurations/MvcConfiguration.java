package org.bigdatacenter.datalink.configurations;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
@Configuration
public class MvcConfiguration extends WebMvcConfigurerAdapter {

	
	@Bean(name="ssisRequestDataInfoDataSource")
	@Primary
	public DataSource ssisRequestDataInfoDataSource(){
		return DataSourceBuilder
				.create()
				.driverClassName("com.mysql.jdbc.Driver")
				.url("jdbc:mysql://112.72.158.187:3306/SSIS_requestDataInfo")
				.username("hyunjoon")
				.password("hyunjoon")
				.build();
	}
	
	@Bean(name="jNHISDataSource")
	public DataSource jNHISDataSource(){
		return DataSourceBuilder
				.create()
				.driverClassName("com.mysql.jdbc.Driver")
				.url("jdbc:mysql://112.72.158.187:3306/J_nhis")
				.username("hyunjoon")
				.password("hyunjoon")
				.build();
	}
	
	@Bean(name="jNhisTakeKeyDataSource")
	public DataSource jNHISTakekeyDataSource(){
		return DataSourceBuilder
				.create()
				.driverClassName("com.mysql.jdbc.Driver")
				.url("jdbc:mysql://112.72.158.187:3306/J_nhisTakeKey")
				.username("hyunjoon")
				.password("hyunjoon")
				.build();
	}
	
	@Bean(name="jCdcTakeKeyDataSource")
	public DataSource jCdcTakeKeyDataSource(){
		return DataSourceBuilder
				.create()
				.driverClassName("com.mysql.jdbc.Driver")
				.url("jdbc:mysql://112.72.158.187:3306/J_cdcTakeKey")
				.username("hyunjoon")
				.password("hyunjoon")
				.build();
	}
	
	
	
	@Bean(name="jNecaDataSource")
	public DataSource jNecaDataSource(){
		return DataSourceBuilder
				.create()
				.driverClassName("com.mysql.jdbc.Driver")
				.url("jdbc:mysql://112.72.158.187:3306/J_neca")
				.username("hyunjoon")
				.password("hyunjoon")
				.build();
	}
	
	
	@Bean(name="statDataSource")
	public DataSource statDataSource(){
		return DataSourceBuilder
				.create()
				.driverClassName("com.mysql.jdbc.Driver")
				.url("jdbc:mysql://112.72.158.187:3306/stat")
				.username("hyunjoon")
				.password("hyunjoon")
				.build();
	}
	
	
	
	@Bean(name="jCdcSecretDataSource")
	public DataSource jCdcSecretDataSource(){
		return DataSourceBuilder
				.create()
				.driverClassName("com.mysql.jdbc.Driver")
				.url("jdbc:mysql://112.72.158.187:3306/J_cdcSecretData")
				.username("hyunjoon")
				.password("hyunjoon")
				.build();
	}
	
	
	@Bean(name="jCdcDataSource")
	public DataSource jCdcDataSource(){
		return DataSourceBuilder
				.create()
				.driverClassName("com.mysql.jdbc.Driver")
				.url("jdbc:mysql://112.72.158.187:3306/J_cdc")
				.username("hyunjoon")
				.password("hyunjoon")
				.build();
	}
	
	
	
	
	@Bean(name="jnecaTakeIDDataSource")
	public DataSource jnecaTakeIDDataSource(){
		return DataSourceBuilder
				.create()
				.driverClassName("com.mysql.jdbc.Driver")
				.url("jdbc:mysql://112.72.158.187:3306/J_necaTakeID")
				.username("hyunjoon")
				.password("hyunjoon")
				.build();
	}
	
	@Bean(name="jNhisTakeKeyDataSource")
	public DataSource jnecaTakeKeyDataSource(){
		return DataSourceBuilder
				.create()
				.driverClassName("com.mysql.jdbc.Driver")
				.url("jdbc:mysql://112.72.158.187:3306/J_nhisTakeKey")
				.username("hyunjoon")
				.password("hyunjoon")
				.build();
	}
	
	@Bean(name="jLinkerTakeCdcDataSource")
	public DataSource jLinkerTakeCdcDataSource(){
		return DataSourceBuilder
				.create()
				.driverClassName("com.mysql.jdbc.Driver")
				.url("jdbc:mysql://112.72.158.187:3306/J_linkerTakeCDC")
				.username("hyunjoon")
				.password("hyunjoon")
				.build();
	}
	
	@Bean(name="jLinkerTakeNhisDataSource")
	public DataSource jLinkerTakeNhisDataSource(){
		return DataSourceBuilder
				.create()
				.driverClassName("com.mysql.jdbc.Driver")
				.url("jdbc:mysql://112.72.158.187:3306/J_linkerTakeNHIS")
				.username("hyunjoon")
				.password("hyunjoon")
				.build();
	}
	
	
	@Bean(name="dataSource")
	public DataSource dataSource(){
		return DataSourceBuilder
				.create()
				.driverClassName("com.mysql.jdbc.Driver")
				.url("jdbc:mysql://112.72.158.187:3306")
				.username("hyunjoon")
				.password("hyunjoon")
				.build();
	}
	
	
	
	
	
	
	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}


}
