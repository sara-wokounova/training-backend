package com.sda.java.training.backend.api;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ObjednavkaExtended extends ObjednavkaBase {
  Set<PolozkaObjednavkyBase> polozky;
}
