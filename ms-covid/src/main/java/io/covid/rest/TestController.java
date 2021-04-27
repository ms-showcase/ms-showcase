package io.covid.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class TestController {

    @Value("${message}")
    private String message;

    @GetMapping(path = "/hello")
    public ResponseEntity<String> hello(){
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
