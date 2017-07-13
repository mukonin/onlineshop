package com.onlineshop.config;

import org.hibernate.SessionFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by sanya on 17.06.2017.
 */

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@PropertySource(value = {"classpath:application.properties"})
@ComponentScan(basePackages = {"com.onlineshop"})
public class WebConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {

	private static final String PREFIX = "/WEB-INF/templates/";
	private static final String SUFIX = ".html";
	private static final String RESOURCE_PATH_PATERN = "/resources/**";
	private static final String RESOURCE_LOCATION = "/resources/";

	private static final String PROP_HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
	private static final String PROP_HIBERNATE_DIALECT = "hibernate.dialect";
	private static final String PROP_HIBERNATE_SHOW_SQL = "hibernate.show_sql";

	private static final String PROP_MAIL_HOST = "mail.host";
	private static final String PROP_MAIL_PORT = "mail.port";
	private static final String PROP_MAIL_USERNAME = "mail.username";
	private static final String PROP_MAIL_PASSWORD = "mail.password";

	private static final String PROP_MAIL_SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";
	private static final String PROP_MAIL_SMTP_AUTH = "mail.smtp.auth";
	private static final String PROP_MAIL_TRANSPORT_PROTOCOL = "mail.transport.protocol";
	private static final String PROP_MAIL_DEDUG = "mail.debug";

	@Resource
	private Environment environment;

	@Autowired
	private DataSource dataSource;

	private ApplicationContext applicationContext;

	public WebConfig() {
		super();
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}


	@Bean
	public JavaMailSender getMailSender(){
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(environment.getRequiredProperty(PROP_MAIL_HOST));
		mailSender.setPort(Integer.parseInt(environment.getRequiredProperty(PROP_MAIL_PORT)));
		mailSender.setUsername(environment.getRequiredProperty(PROP_MAIL_USERNAME));
		mailSender.setPassword(environment.getRequiredProperty(PROP_MAIL_PASSWORD));
		mailSender.setJavaMailProperties(getMailProperties());
		return mailSender;
	}

	public Properties getMailProperties(){
		Properties properties = new Properties();
		properties.put(PROP_MAIL_SMTP_STARTTLS_ENABLE, environment.getRequiredProperty(PROP_MAIL_SMTP_STARTTLS_ENABLE));
		properties.put(PROP_MAIL_SMTP_AUTH, environment.getRequiredProperty(PROP_MAIL_SMTP_AUTH));
		properties.put(PROP_MAIL_TRANSPORT_PROTOCOL, environment.getRequiredProperty(PROP_MAIL_TRANSPORT_PROTOCOL));
		properties.put(PROP_MAIL_DEDUG,  environment.getRequiredProperty(PROP_MAIL_DEDUG));
		return properties;
	}


	public Properties getHibernateProperties() {
		Properties properties = new Properties();
		//properties.put(PROP_HIBERNATE_HBM2DDL_AUTO,  environment.getRequiredProperty(PROP_HIBERNATE_HBM2DDL_AUTO));
		properties.put(PROP_HIBERNATE_DIALECT, environment.getRequiredProperty(PROP_HIBERNATE_DIALECT));
		properties.put(PROP_HIBERNATE_SHOW_SQL, environment.getRequiredProperty(PROP_HIBERNATE_SHOW_SQL));
		return properties;
	}

	@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setPackagesToScan(new String[]{"com.onlineshop.model"});
		sessionFactory.setHibernateProperties(getHibernateProperties());
		return sessionFactory;
	}

	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager;
	}

	@Bean
	public MessageSource messageSource() {
		return new ReloadableResourceBundleMessageSource() {{
			setBasename("classpath:messages");
		}};
	}

	@Bean
	public SpringResourceTemplateResolver templateResolver() {
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setApplicationContext(this.applicationContext);
		templateResolver.setPrefix(PREFIX);
		templateResolver.setSuffix(SUFIX);
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setCacheable(true);
		return templateResolver;
	}

	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		templateEngine.setEnableSpringELCompiler(true);
		return templateEngine;
	}

	@Bean
	public ThymeleafViewResolver viewResolver() {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine());
		return viewResolver;
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(RESOURCE_PATH_PATERN).addResourceLocations(RESOURCE_LOCATION);
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}


}
