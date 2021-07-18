package com.cpalmer.jeep.entity;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
  private Long orderPK;
  private Customer customer;
  private Jeep model;
  private Color color;
  private Engine engine;
  private Tire tire;
  private BigDecimal price;
  private List<Option> options;
}
