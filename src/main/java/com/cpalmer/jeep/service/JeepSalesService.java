package com.cpalmer.jeep.service;

import java.util.List;
import com.cpalmer.jeep.entity.Jeep;
import com.cpalmer.jeep.entity.JeepModel;

public interface JeepSalesService {

  List<Jeep> fetchJeeps(JeepModel model, String trim);

}
