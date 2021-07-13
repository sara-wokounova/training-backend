package com.sda.java.training.backend.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Objednavka {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name="id_zakaznik", nullable=false)
  private Zakaznik zakaznik;

  private LocalDate datum;

  @Column(name="celkova_cena")
  private BigDecimal celkovaCena;

  private String mena;

  @JsonIgnore
  @OneToMany
  @JoinColumn(name = "id_objednavka")
  private Set<PolozkaObjednavky> polozky;

}
