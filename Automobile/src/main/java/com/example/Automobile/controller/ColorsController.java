package com.example.Automobile.controller;

import com.example.Automobile.DTO.ColorsDTO;
import com.example.Automobile.exceptionHandler.CarBadRequestException;
import com.example.Automobile.exceptionHandler.CarInternalErrorException;
import com.example.Automobile.service.ColorsService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ColorsController {
    @Autowired
    private ColorsService colorsService;

    @GetMapping("/cars/{carId}/colors")
    public List<ColorsDTO> getColorsByCarId(@PathVariable int carId){
        return colorsService.getColorsByCarId(carId);
    }

    @GetMapping("/cars/{carId}/colors/{colorId}")
    public ColorsDTO getColorsById(@PathVariable int carId, @PathVariable int colorId){
        return colorsService.getColorsById(carId, colorId);
    }

    @PostMapping("/cars/{carId}/colors")
    public ColorsDTO createColors(@PathVariable int carId, @RequestBody ColorsDTO colorsDTO){
        try {
            return colorsService.createColors(carId, colorsDTO);
        }
        catch (ConstraintViolationException ex){
            throw new CarBadRequestException("1004", "Exceeded maximum allowed colors for car");
        }
    }

    @DeleteMapping("cars/{carId}/colors/{colorId}")
    public void deleteColors(@PathVariable int carId, @PathVariable int colorId){
        colorsService.deleteColors(carId, colorId);
    }

    @PutMapping("/cars/{carId}/colors/{colorId}")
    public ColorsDTO updateColors(@PathVariable int carId, @PathVariable int colorId, @RequestBody ColorsDTO colorsDTO){
        return colorsService.updateColors(carId, colorId, colorsDTO);
    }

}
