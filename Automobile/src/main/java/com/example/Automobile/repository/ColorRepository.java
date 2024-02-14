package com.example.Automobile.repository;

import com.example.Automobile.entity.Car;
import com.example.Automobile.entity.Colors;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ColorRepository extends JpaRepository<Colors, Integer> {
    List<Colors> findByCar(Car car);
    //method returns an Optional<Colors> based on provided colorId and Car
    //If no matching found returns an empty Optional
    Optional<Colors> findByIdAndCar(int color_id, Car car);
}
