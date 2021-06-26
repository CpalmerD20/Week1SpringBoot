package com.cpalmer.controller;

import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import entity.Jeep;

@RestController
public class BasicJeepSalesController implements JeepSalesController{

  public List<Jeep> fetchJeeps(String model, String trim) {
    // TODO Auto-generated method stub
    return null;
  }

}
