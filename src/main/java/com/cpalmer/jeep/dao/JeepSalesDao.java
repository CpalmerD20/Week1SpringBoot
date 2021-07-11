package com.cpalmer.jeep.dao;

import java.util.List;
import com.cpalmer.jeep.entity.Jeep;
import com.cpalmer.jeep.entity.JeepModel;

public interface JeepSalesDao {
/**
 * 
 * @param model
 * @param trim
 * @return
 */
  
  List<Jeep> fetchJeeps(JeepModel model, String trim);

}
