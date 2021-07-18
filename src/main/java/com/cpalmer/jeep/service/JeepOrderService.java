package com.cpalmer.jeep.service;

import com.cpalmer.jeep.entity.Order;
import com.cpalmer.jeep.entity.OrderRequest;

public interface JeepOrderService {

  Order createOrder(OrderRequest orderRequest);

}
