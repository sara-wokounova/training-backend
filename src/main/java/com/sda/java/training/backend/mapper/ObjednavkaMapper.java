package com.sda.java.training.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.sda.java.training.backend.api.ObjednavkaBase;
import com.sda.java.training.backend.api.ObjednavkaExtended;
import com.sda.java.training.backend.api.PolozkaObjednavkyBase;
import com.sda.java.training.backend.model.Objednavka;
import com.sda.java.training.backend.model.PolozkaObjednavky;

@Mapper
public interface ObjednavkaMapper {
  ObjednavkaMapper MAPPER = Mappers.getMapper( ObjednavkaMapper.class );

  @Mapping(target = "zakaznikName", source="zakaznik.name")
  ObjednavkaBase toBase(Objednavka objednavka);

  @Mapping(target = "zakaznikName", source="zakaznik.name")
  ObjednavkaExtended toExtended(Objednavka objednavka);

  @Mapping(target = "produktName", source="polozkaCeniku.produkt.nazev")
  PolozkaObjednavkyBase toPolozkaObjednavkyBase(PolozkaObjednavky polozkaObjednavky);
}
