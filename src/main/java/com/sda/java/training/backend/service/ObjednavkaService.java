package com.sda.java.training.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sda.java.training.backend.model.Objednavka;

@Service
public class ObjednavkaService implements IObjednavkaService {

  @Autowired
  IHibernateService hibernateService;

  @Override
  public List<Objednavka> findAll() {
    return hibernateService.getSession()
        .createQuery("SELECT o from Objednavka o", Objednavka.class)
        .getResultList();
  }

  @Override
  public Objednavka getById(Long id) {
    return hibernateService.getSession()
        .createQuery("SELECT o FROM Objednavka o JOIN FETCH o.polozky po WHERE o.id = :id", Objednavka.class)
        .setParameter("id", id)
        .getSingleResult();
  }

}
