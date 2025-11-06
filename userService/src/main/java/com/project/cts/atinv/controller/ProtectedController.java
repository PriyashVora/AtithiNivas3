package com.project.cts.atinv.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hello")
public class ProtectedController {
    @GetMapping
    public ResponseEntity<String> getHelloMessage( @RequestHeader("X-Role") String role, @RequestHeader("X-User-Id") int id) {
        return new ResponseEntity<>("Hello "+role+" "+id, HttpStatus.OK);
    }
}

