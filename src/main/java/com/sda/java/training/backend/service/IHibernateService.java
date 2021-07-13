package com.sda.java.training.backend.service;

import org.hibernate.Session;

public interface IHibernateService {

  Session getSession();
}
