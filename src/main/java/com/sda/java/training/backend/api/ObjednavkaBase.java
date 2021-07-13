package com.sda.java.training.backend.api;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObjednavkaBase {
  private Long id;
  private String zakaznikName;
  private String datum;
  private BigDecimal celkovaCena;
  private String mena;
}
