package com.clbartolome.route.controller;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class RoutingControllerTests {

  @LocalServerPort
  private int port;

  private String baseUrl = "http://localhost";

  private static RestTemplate restTemplate = null;

  @BeforeAll
  public static void init() {
    restTemplate = new RestTemplate();
  }

  @BeforeEach
  public void setUp() {
    baseUrl = baseUrl.concat(":").concat(port + "");
  }

  @Test
  public void getWatchesTest() {

    ResponseEntity<String> response = restTemplate.getForEntity(baseUrl + "/route", String.class);
    assertAll(
        () -> assertNotNull(response.getBody()),
        () -> assertEquals("test(v1)", response.getBody()),
        () -> assertEquals(HttpStatus.OK, response.getStatusCode()));
  }

}