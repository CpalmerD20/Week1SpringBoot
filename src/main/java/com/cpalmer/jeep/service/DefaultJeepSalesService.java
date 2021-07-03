package com.cpalmer.jeep.service;

import java.util.List;
import com.cpalmer.jeep.entity.Jeep;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultJeepSalesService implements JeepSalesService {

  @Override
  public List<Jeep> fetchJeeps(String model, String trim) {
    log.info(("The fetchJeeps method was called with model={} and trim={}"),
    model, trim);
    return null;
  }

}
