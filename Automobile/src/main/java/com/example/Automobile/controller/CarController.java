package com.example.Automobile.controller;

import com.example.Automobile.DTO.CarDTO;
import com.example.Automobile.service.CarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {
    @Autowired
    private CarService carService;

    //Read operation
    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    public List<CarDTO> getCars(){

        return carService.getAllCars();
    }

    @GetMapping("/cars/{id}")
    public CarDTO getCarById(@PathVariable int id){

        return carService.getCarById(id);
    }

    //Delete data
    //PathVariable is used to capture template variables
    @DeleteMapping("/delete/{id}" )
    public void delete(@PathVariable int id){
        carService.deleteById(id);
    }

    //Create data
    @PostMapping("/create")
    public int create(@RequestBody CarDTO carDTO){
        carService.createCar(carDTO);
        return carDTO.getId();
    }
    @PutMapping("/update/{id}")
    public CarDTO updatecar(@PathVariable int id, @RequestBody CarDTO updatedCarDTO){
        CarDTO updated = carService.updateCar(id, updatedCarDTO);
        return updated;
        //error code : 1001
        //error desc : invalid car number
        //1002...
    }
}
