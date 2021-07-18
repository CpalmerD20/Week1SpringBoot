package com.cpalmer.jeep.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import com.cpalmer.jeep.entity.Color;
import com.cpalmer.jeep.entity.Customer;
import com.cpalmer.jeep.entity.Engine;
import com.cpalmer.jeep.entity.Jeep;
import com.cpalmer.jeep.entity.JeepModel;
import com.cpalmer.jeep.entity.Option;
import com.cpalmer.jeep.entity.Order;
import com.cpalmer.jeep.entity.OrderRequest;
import com.cpalmer.jeep.entity.Tire;

public interface JeepOrderDao {
  List<Option> fetchOptions(List<String> optionIds);
  Optional<Customer> fetchCustomer(String customerId);
  Optional<Jeep> fetchModel(JeepModel model, String trim, int doors);
  Optional<Color> fetchColor(String colorId);
  Optional<Engine> fetchEngine(String engineId);
  Optional<Tire> fetchTire(String tireId);
  
  Order saveOrder(Customer customer, Jeep jeep, Color color, Engine engine, 
      Tire tire, BigDecimal price, List<Option> options);
  
  Order createOrder(OrderRequest orderRequest);
}