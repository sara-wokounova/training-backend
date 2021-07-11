package com.sda.java.training.backend.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.NoResultException;

import org.apache.logging.log4j.util.Strings;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import com.sda.java.training.backend.controller.BadRequestException;
import com.sda.java.training.backend.model.Produkt;

@Service
public class ProduktService implements IProduktService{


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
    }
  }

  @Override
  public List<Produkt> findAll() {
    return session.createQuery("select p from Produkt p WHERE p.stav = 'aktivni'", Produkt.class).getResultList();
  }

  @Override
  public Produkt getById(Long id) {
    try {
      Query<Produkt> query = session.createQuery("select p from Produkt p WHERE p.id = :id", Produkt.class);
      query.setParameter("id", id);
      return query.getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

  @Override
  public Produkt create(Produkt produkt) {
    Transaction transaction = getTransaction();
    if (Strings.isBlank(produkt.getNazev())) {
      throw new BadRequestException();
    }
    produkt.setStav(Produkt.AKTIVNI);
    session.persist(produkt);
    transaction.commit();
    return produkt;
  }

  @Override
  public Produkt update(Long id, Produkt produkt) {
    Transaction transaction = getTransaction();
    Produkt oldProdukt = getById(id);
    if (Strings.isNotBlank(produkt.getNazev())) {
      oldProdukt.setNazev(produkt.getNazev());
    }
    if (Strings.isNotBlank(produkt.getPopis())) {
      oldProdukt.setPopis(produkt.getPopis());
    }
    transaction.commit();
    return oldProdukt;
  }

  @Override
  public void deleteById(Long id) {
    Transaction transaction = getTransaction();
    session.delete(getById(id));
    transaction.commit();
  }

  private Transaction getTransaction() {
    try {
      return session.beginTransaction();
    } catch (Exception e) {
      System.out.println("Nepodarilo se vytvorit spojeni do databaze");
      throw new RuntimeException(e);
    }
  }


}
