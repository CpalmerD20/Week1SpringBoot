package Jeep.Controller.Support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;


public class BaseTest {
  @LocalServerPort
  private int serverPort;
  
  @Autowired
  
  private TestRestTemplate restTemplate;
  
  protected String getBaseUri() {
    return String.format("http://localhost:%d/jeeps", serverPort);
  }

  public int getServerPort() {
    return serverPort;
  }

  public TestRestTemplate getRestTemplate() {
    return restTemplate;
  }
  
}
