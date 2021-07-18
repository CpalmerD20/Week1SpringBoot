package com.cpalmer.jeep.controller;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.doThrow;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.jdbc.JdbcTestUtils;
import com.cpalmer.jeep.Constants;
import com.cpalmer.jeep.entity.Jeep;
import com.cpalmer.jeep.entity.JeepModel;
import com.cpalmer.jeep.service.JeepSalesService;
import Jeep.Controller.Support.FetchJeepTestSupport;



class FetchJeepTest {
  
  @Nested
  @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
  @ActiveProfiles("test") //works with application-test.yaml
  @Sql(scripts = {
      "classpath:flyway/migrations/V1.0__Jeep_Schema.sql",
      "classpath:flyway/migrations/V1.1__Jeep_Data.sql"}, 
      config = @SqlConfig(encoding = "utf-8"))
  class TestsThatDoNotPolluteTheApplicationContext extends FetchJeepTestSupport {
    @Test
    void testThatJeepsAreReturnedWhenValidModelAndTrimAreSupplied() {
      //given a valid model, trim, and URI
      JeepModel model = JeepModel.WRANGLER;
      String trim = "Sport";
      String uri = String.format("%s?model=%s&trim=%s", getBaseUriForJeeps(), model, trim);
      
      //when a connection is made to the URI
      ResponseEntity<List<Jeep>> response = 
          getRestTemplate().exchange(uri, HttpMethod.GET, null, 
              new ParameterizedTypeReference<>() {});
          
      //then success (OK - 200) status code is returned
      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
      
      //And: the actual list returned will be the same as the expected list
      List<Jeep> actual = response.getBody();
      List<Jeep> expected = buildExpected();
      
      assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testThatAnErrorMessgeIsReturnedWhenUnknownTrimSupplied() {
      //given unknown entry
      JeepModel model = JeepModel.WRANGLER;
      String trim = "Unknown Value";
      String uri = String.format("%s?model=%s&trim=%s", getBaseUriForJeeps(), model, trim);
      
      //when
      ResponseEntity<Map<String, Object>> response = 
          getRestTemplate().exchange(uri, HttpMethod.GET, null, 
              new ParameterizedTypeReference<>() {});
          
      //then a not found (404) is returned
      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
      
      //And: error message is returned
      Map<String, Object> error = response.getBody();
      
      assertErrorMessageValid(error, HttpStatus.NOT_FOUND);
    }

    @ParameterizedTest
    @MethodSource("com.cpalmer.jeep.controller.FetchJeepTest#parametersForInvalidInput")
    void testThatAnErrorMessgeIsReturnedWhenInvalidTrimSupplied(
        String model, String trim, String reason) {
      //given invalid entry
      String uri = String.format("%s?model=%s&trim=%s", getBaseUriForJeeps(), model, trim);
      
      //when
      ResponseEntity<Map<String, Object>> response = 
          getRestTemplate().exchange(uri, HttpMethod.GET, null, 
              new ParameterizedTypeReference<>() {});
          
      //then a not found (404) is returned
      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
      
      //And: error message is returned
      Map<String, Object> error = response.getBody();
      
      assertErrorMessageValid(error, HttpStatus.BAD_REQUEST);
    }
  }
  
  static Stream<Arguments> parametersForInvalidInput() {
    return Stream.of(
        arguments("WRANGLER", "45F$#^F", "Trim contains non-alpha-numeric characters"),
        arguments("WRANGLER", "C".repeat(Constants.TRIM_MAX_LENGTH + 1), "Trim length too long"),
        arguments("INVALID", "Sport", "Model is not enum value")
        
        );
    }
 
  @Nested
  @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
  @ActiveProfiles("test") //works with application-test.yaml
  @Sql(scripts = {
      "classpath:flyway/migrations/V1.0__Jeep_Schema.sql",
      "classpath:flyway/migrations/V1.1__Jeep_Data.sql"}, 
      config = @SqlConfig(encoding = "utf-8"))
  class TestsThatPolluteTheApplicationContext extends FetchJeepTestSupport {
    
    @MockBean
    private JeepSalesService jeepSalesService;
    
    @Test
    void testThatAnUnplannedErrorResults500Status() {
      //given unplanned error
      JeepModel model = JeepModel.WRANGLER;
      String trim = "Invalid";
      String uri = String.format("%s?model=%s&trim=%s", getBaseUriForJeeps(), model, trim);
      
      doThrow(new RuntimeException("Ouch"))
      .when(jeepSalesService)
      .fetchJeeps(model, trim);
        
      
      //when connection made to the URI
      ResponseEntity<?> response = 
          getRestTemplate().exchange(uri, HttpMethod.GET, null, 
              new ParameterizedTypeReference<>() {});
          
      // then a 500 status is returned
      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
      
    }
  }  
}
