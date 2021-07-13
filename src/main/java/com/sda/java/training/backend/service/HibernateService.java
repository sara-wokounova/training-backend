package com.sda.java.training.backend.service;

import javax.annotation.PostConstruct;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.stereotype.Service;

@Service
public class HibernateService implements IHibernateService {
  private SessionFactory sessionFactory;
  private Session session;

  @PostConstruct
  public void initSessionFactory() {
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
        .configure() // configures settings from hibernate.cfg.xml
        .build();
    try {
      sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
      session = sessionFactory.openSession();
    } catch (Exception ex) {
      StandardServiceRegistryBuilder.destroy(registry);
      throw ex;
    }
  }

  public Session getSession() {
    return session;
  }

}
