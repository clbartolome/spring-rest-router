package com.clbartolome.route.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/route")
public class RoutingController {

  @Value("${app.routing.name}") 
  private String name;

  @Value("${app.routing.destinations:}")
  private String[] stringArrayWithDefaults;

  public RoutingController() {
  }

  @GetMapping
  public ResponseEntity<String> route() {    

    for (String route : stringArrayWithDefaults) {
      System.out.println(">>>" + route);
    }

    return ResponseEntity.status(HttpStatus.OK).body(name);
  }

}
