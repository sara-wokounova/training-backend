package com.sda.java.training.backend.api;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PolozkaObjednavkyBase {
  private BigDecimal mnozstvi;
  private BigDecimal jednotkovaCena;
  private String produktName;
}
