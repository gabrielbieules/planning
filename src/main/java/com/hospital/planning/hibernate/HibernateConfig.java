package com.hospital.planning.hibernate;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactoryBean;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.hospital.planning.primitives.Appointment;
import com.hospital.planning.primitives.Doctor;
import com.hospital.planning.primitives.Patient;
import com.hospital.planning.primitives.Person;

/**
 * Hibernate config
 * 
 * @author gabriel.bieules
 *
 */
@Configuration
@EnableTransactionManagement
public class HibernateConfig {

  /**
   * Register all DB entities
   * 
   * @return session factory
   */
  @Bean
  public SessionFactory sessionFactory() {
    return new LocalSessionFactoryBuilder(datasource()).addAnnotatedClasses(Person.class)
        .addAnnotatedClasses(Doctor.class).addAnnotatedClasses(Patient.class).addAnnotatedClasses(Appointment.class)
        .buildSessionFactory();
  }

  /**
   * Register Hibernate Transaction Manager
   * 
   * @return manager
   */
  @Bean
  public PlatformTransactionManager transactionManager() {
    return new HibernateTransactionManager(sessionFactory());
  }

  /**
   * Configure (and populate) DB
   * 
   * @author gabriel.bieules
   *
   */
  @Bean
  public DataSource datasource() {
    EmbeddedDatabaseFactoryBean bean = new EmbeddedDatabaseFactoryBean();
    ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
    ClassPathResource classPathResource = new ClassPathResource("schema.sql");
    databasePopulator.addScript(classPathResource);
    bean.setDatabasePopulator(databasePopulator);
    bean.afterPropertiesSet();
    return bean.getObject();
  }

}
