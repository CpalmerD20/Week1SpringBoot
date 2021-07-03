package com.cpalmer.jeep.controller;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.jdbc.JdbcTestUtils;
import com.cpalmer.jeep.entity.Jeep;
import com.cpalmer.jeep.entity.JeepModel;
import Jeep.Controller.Support.FetchJeepTestSupport;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test") //works with application-test.yaml
@Sql(scripts = {
    "classpath:flyway/migrations/V1.0__Jeep_Schema.sql",
    "classpath:flyway/migrations/V1.1__Jeep_Data.sql"}, 
    config = @SqlConfig(encoding = "utf-8"))

class FetchJeepTest extends FetchJeepTestSupport {
  
//  @Autowired
//  private JdbcTemplate jdbcTemplate;
//  
//  @Test
//  void testDb() {
//    int numrows = JdbcTestUtils.countRowsInTable(jdbcTemplate, "customers");
//    System.out.println("num = " + numrows);
//  }
//
//  @Disabled
  @Test
  void testThatJeepsAreReturnedWhenValidModelAndTrimAreSupplied() {
    //given a valid model, trim, and URI
    JeepModel model = JeepModel.WRANGLER;
    String trim = "Sport";
    String uri = String.format("%s?model=%s&trim=%s", getBaseUri(), model, trim);
//    String uri = String.format("http://localhost/%d/jeeps?model=%s&trim=%s", serverPort, model, trim);
    
    System.out.println(uri);
    //when a connection is made to the URI
    ResponseEntity<List<Jeep>> response = 
        getRestTemplate().exchange(uri, HttpMethod.GET, null, 
            new ParameterizedTypeReference<>() {});
        
    //then success (OK - 200) status code is returned
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    
    //And: the actual list returned will be the same as the expected list
    List<Jeep> expected = buildExpected();
    assertThat(response.getBody()).isEqualTo(expected);
  }


}
