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
public class Tire {
  private Long tirePK;
  private String tireId;
  private String tireSize;
  private String manufacturer;
  private BigDecimal price;
  private int warrantyMiles;
}
