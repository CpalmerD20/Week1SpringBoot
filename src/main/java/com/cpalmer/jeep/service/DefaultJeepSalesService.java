package com.cpalmer.jeep.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import com.cpalmer.jeep.dao.JeepSalesDao;
import com.cpalmer.jeep.entity.Jeep;
import com.cpalmer.jeep.entity.JeepModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultJeepSalesService implements JeepSalesService {

  @Autowired
  private JeepSalesDao jeepSalesDao;
  
  @Override
  public List<Jeep> fetchJeeps(JeepModel model, String trim) {
    log.info(("The fetchJeeps method was called with model={} and trim={}"),
    model, trim);
    
    List<Jeep> jeeps = jeepSalesDao.fetchJeeps(model, trim);

    Collections.sort(jeeps);
    return jeeps;
  }

}
