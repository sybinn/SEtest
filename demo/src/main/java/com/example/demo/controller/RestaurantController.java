package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.*;
import com.example.demo.entity.*;
import java.util.List;

@RestController
@RequestMapping("/api/yufood")
@CrossOrigin(origins = "http://localhost:3000")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        System.out.println("API 호출됨: getAllRestaurants");  // 추가
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        System.out.println("조회된 식당 수: " + restaurants.size());  // 추가
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable("id") int id) {
        System.out.println("상세 정보 API 호출됨: " + id);
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        if (restaurant != null) {
            return ResponseEntity.ok(restaurant);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
