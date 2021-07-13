package com.sda.java.training.backend.service;

import java.util.List;

import javax.persistence.NoResultException;

import org.apache.logging.log4j.util.Strings;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sda.java.training.backend.controller.BadRequestException;
import com.sda.java.training.backend.model.Produkt;

@Service
public class ProduktService implements IProduktService{

  @Autowired
  IHibernateService hibernateService;

  @Override
  public List<Produkt> findAll() {
    return hibernateService.getSession().createQuery("select p from Produkt p WHERE p.stav = 'aktivni'", Produkt.class).getResultList();
  }

  @Override
  public Produkt getById(Long id) {
    try {
      Query<Produkt> query = hibernateService.getSession().createQuery("select p from Produkt p WHERE p.id = :id", Produkt.class);
      query.setParameter("id", id);
      return query.getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

  @Override
  public Produkt create(Produkt produkt) {
    Transaction transaction = hibernateService.getSession().beginTransaction();
    if (Strings.isBlank(produkt.getNazev())) {
      throw new BadRequestException();
    }
    produkt.setStav(Produkt.AKTIVNI);
    hibernateService.getSession().persist(produkt);
    transaction.commit();
    return produkt;
  }

  @Override
  public Produkt update(Long id, Produkt produkt) {
    Transaction transaction = hibernateService.getSession().beginTransaction();
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
    Transaction transaction = hibernateService.getSession().beginTransaction();
    hibernateService.getSession().delete(getById(id));
    transaction.commit();
  }




}
