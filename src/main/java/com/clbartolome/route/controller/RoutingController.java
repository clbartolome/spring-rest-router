package com.clbartolome.route.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/route")
public class RoutingController {

  @Value("${app.routing.name}")
  private String name;

  @Value("${app.routing.version}")
  private String version;

  @Value("${app.routing.destinations:}")
  private String[] stringArrayWithDefaults;

  public RoutingController() {
  }

  @GetMapping
  public ResponseEntity<String> route() {
    RestTemplate restTemplate = new RestTemplate();
    String responses = "";
    for (String route : stringArrayWithDefaults) {
      ResponseEntity<String> response = restTemplate.getForEntity(route, String.class);
      responses = responses + " ::: " + response.getBody();
    }

    return ResponseEntity.status(HttpStatus.OK).body(name + "(" + version + ")" + responses);
  }

}
