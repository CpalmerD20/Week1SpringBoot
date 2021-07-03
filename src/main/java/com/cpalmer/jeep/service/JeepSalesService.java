package com.cpalmer.jeep.service;

import java.util.List;
import com.cpalmer.jeep.entity.Jeep;

public interface JeepSalesService {

  List<Jeep> fetchJeeps(String model, String trim);

}
