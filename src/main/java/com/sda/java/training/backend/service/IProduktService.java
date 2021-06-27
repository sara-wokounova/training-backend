package com.sda.java.training.backend.service;

import java.util.List;

public interface IProduktService {
  List<Produkt> findAll();
  Produkt getById(Long id);
  Produkt create(Produkt resource);
  void update(Produkt resource);
  void deleteById(Long id);
}
