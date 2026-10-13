package com.example.studentapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> getHomeInfo() {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", "UP");
        response.put("application", "Spring Boot Student Management Service");
        response.put("timestamp", LocalDateTime.now());
        response.put("deployment", "Automated deployment via GitHub Actions SSH Action on Remote VPS");
        response.put("actuator_health", "/actuator/health");
        response.put("api_endpoint", "/api/students");
        return ResponseEntity.ok(response);
    }
}
