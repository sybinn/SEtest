package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Restaurant;
import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
	@Query("SELECT DISTINCT r FROM Restaurant r LEFT JOIN FETCH r.menus WHERE r.rtag = :category")
    List<Restaurant> findByCategory(@Param("category") String category);
	
	@Query("SELECT DISTINCT r FROM Restaurant r LEFT JOIN FETCH r.menus")
    List<Restaurant> findAllWithMenus();
}