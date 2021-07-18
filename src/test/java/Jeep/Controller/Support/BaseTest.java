package Jeep.Controller.Support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;


public class BaseTest {
  @LocalServerPort
  int serverPort;
  
  @Autowired
  TestRestTemplate restTemplate;
  
  protected String getBaseUriForJeeps() {
    return String.format("http://localhost:%d/jeeps", serverPort);
  }

  protected String getBaseUriForOrders() {
    return String.format("http://localhost:%d/orders", serverPort);
  }

  /**
   * 
   * @return
   */
  public int getServerPort() {
    return serverPort;
  }

  /**
   * 
   * @return
   */
  public TestRestTemplate getRestTemplate() {
    return restTemplate;
  }
  
}
