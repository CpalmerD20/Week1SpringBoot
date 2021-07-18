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
public class Option {
  private Long optionPK;
  private String optionId;
  private OptionType category;
  private String manufacturer;
  private String name;
  private BigDecimal price;
  
}
