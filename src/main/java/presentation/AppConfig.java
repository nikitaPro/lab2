package presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

import application.*;
import domain.BookRepository;
import domain.BookRepositoryImpl;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@ComponentScan
@PropertySource("persistence-entity.properties")
@EnableTransactionManagement
public class AppConfig {

	@Autowired
	private Environment env;

	@Bean
	public ApplicationService applicationService() {
		return new ApplicationServiceImpl();
	}
	
	@Bean
	public BookRepository bookRepository() {
		return new BookRepositoryImpl();
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(JpaVendorAdapter jpaVendorAdapter, DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource);
		em.setPackagesToScan(new String[] { "domain" });
		em.setJpaVendorAdapter(jpaVendorAdapter);
		em.getJpaPropertyMap().put("eclipselink.weaving", "false");

		return em;
	}

	@Bean
	public DataSource dataSource() {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.user"));
		dataSource.setPassword(env.getProperty("jdbc.pass"));
		return dataSource;
	}

	@Bean
	protected AbstractJpaVendorAdapter jpaVendorAdapter() {
		return new EclipseLinkJpaVendorAdapter();
	}

}
