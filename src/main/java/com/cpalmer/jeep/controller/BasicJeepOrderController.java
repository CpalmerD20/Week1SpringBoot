package com.cpalmer.jeep.controller;

import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.RestController;
import com.cpalmer.jeep.entity.Order;
import com.cpalmer.jeep.entity.OrderRequest;
import com.cpalmer.jeep.service.JeepOrderService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BasicJeepOrderController implements JeepOrderController {

  @AutoConfigureOrder
  private JeepOrderService jeepOrderService;
  
  @Override
  public Order createOrder(OrderRequest orderRequest) {
    log.debug("Order={}", orderRequest);
    return jeepOrderService.createOrder(orderRequest);
  }

}
