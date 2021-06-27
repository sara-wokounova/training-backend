package com.sda.java.training.backend.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Produkt {

  private Integer id;
  private String nazev;
  private String popis;
}
