package com.cpalmer.jeep.entity;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Engine {
  private Long enginePK;
  private String engineId;
  private Float sizeInLiters;
  private String name;
  private FuelType fuelType;
  private Float mpgCity;
  private Float mpgHighway;
  private boolean hasStartStop;
  private String description;
  private BigDecimal price;
}
