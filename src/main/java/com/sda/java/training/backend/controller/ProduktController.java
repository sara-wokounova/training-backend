package com.sda.java.training.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sda.java.training.backend.service.IProduktService;
import com.sda.java.training.backend.service.Produkt;

@RestController
@RequestMapping("/produkt")
public class ProduktController {

    @Autowired
    private IProduktService service;

    @GetMapping
    public List<Produkt> findAll() {
      return service.findAll();
    }

    @GetMapping(value = "/{id}")
    public Produkt findById(@PathVariable("id") Long id) {
      return RestPreconditions.checkFound(service.getById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produkt create(@RequestBody Produkt resource) {
      RestPreconditions.checkNotNull(resource);
      return service.create(resource);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable( "id" ) Long id, @RequestBody Produkt resource) {
      RestPreconditions.checkNotNull(resource);
      RestPreconditions.checkNotNull(service.getById(new Long(resource.getId())));
      service.update(resource);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
      service.deleteById(id);
    }

  }
