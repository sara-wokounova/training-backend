package com.sda.java.training.backend.service;

import java.util.List;

import com.sda.java.training.backend.model.Objednavka;

public interface IObjednavkaService {
  List<Objednavka> findAll();
  Objednavka getById(Long id);
}
