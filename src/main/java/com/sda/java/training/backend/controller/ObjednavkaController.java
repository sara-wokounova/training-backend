package com.sda.java.training.backend.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sda.java.training.backend.api.ObjednavkaBase;
import com.sda.java.training.backend.api.ObjednavkaExtended;
import com.sda.java.training.backend.mapper.ObjednavkaMapper;
import com.sda.java.training.backend.service.IObjednavkaService;

@RestController
@RequestMapping("/objednavka")
public class ObjednavkaController {

  @Autowired
  private IObjednavkaService service;

  @GetMapping
  public List<ObjednavkaBase> findAll() {
    return service.findAll().stream()
        .map(it -> ObjednavkaMapper.MAPPER.toBase(it))
        .collect(Collectors.toList());
  }

  @GetMapping(value = "/{id}")
  public ObjednavkaExtended findById(@PathVariable("id") Long id) {
    return ObjednavkaMapper.MAPPER.toExtended(RestPreconditions.checkFound(service.getById(id)));
  }

}
