package com.TheAllen.Service.Discovery.endpoints;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin
public class HomeController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<String> homeMessage() {
        return new ResponseEntity<>("Service Discovery", HttpStatus.OK);
    }
}
