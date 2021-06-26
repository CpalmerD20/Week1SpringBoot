package com.cpalmer.jeep.controller;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import Jeep.Controller.Support.FetchJeepTestSupport;
import entity.Jeep;
import entity.JeepModel;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

class FetchJeepTest extends FetchJeepTestSupport {

  @Test
  void testThatJeepsAreReturnedWhenValidModelAndTrimAreSupplied() {
    //given a valid model, trim, and URI
    JeepModel model = JeepModel.WRANGLER;
    String trim = "Sport";
    String uri = String.format("%s?model=%s&trim=%s", getBaseUri(), model, trim);
    
    System.out.println(uri);
    //when a connection is made to the URI
    ResponseEntity<Jeep> response = 
        getRestTemplate().getForEntity(uri, Jeep.class);
    
    //then success (OK - 200) status code is returned
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

}
