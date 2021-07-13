package com.sda.java.training.backend.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "polozka_objednavky")
public class PolozkaObjednavky {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private BigDecimal mnozstvi;

  @Column(name="jednotkova_cena")
  private BigDecimal jednotkovaCena;

  @ManyToOne
  @JoinColumn(name="id_polozka_ceniku", nullable = true)
  private PolozkaCeniku polozkaCeniku;

}
