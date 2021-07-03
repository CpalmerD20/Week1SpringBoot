package com.cpalmer.jeep.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.cpalmer.jeep.entity.Jeep;
import com.cpalmer.jeep.service.JeepSalesService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j

public class BasicJeepSalesController implements JeepSalesController{

  @Autowired //I want an injected here...
  private JeepSalesService jeepSalesService;
  
  public List<Jeep> fetchJeeps(String model, String trim) {
    log.info("model= {}, trim = {}", model, trim);
    //need the curly brackets
    
    return jeepSalesService.fetchJeeps(model,trim);
  }

}
