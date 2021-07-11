package com.sda.java.training.backend.service;

import java.util.List;

import com.sda.java.training.backend.model.Produkt;

public interface IProduktService {
  List<Produkt> findAll();
  Produkt getById(Long id);
  Produkt create(Produkt resource);
  Produkt update(Long id, Produkt resource);
  void deleteById(Long id);
}
