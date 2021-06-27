package com.sda.java.training.backend.controller;

public class RestPreconditions {
  public static <T> T checkFound(T resource) {
    if (resource == null) {
      throw new NotFoundException();
    }
    return resource;
  }

  public static <T> T checkNotNull(T resource) {
    if (resource == null) {
      throw new BadRequestException();
    }
    return resource;
  }
}