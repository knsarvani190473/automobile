package com.example.Automobile.service;

import com.example.Automobile.DTO.ColorsDTO;
import com.example.Automobile.entity.Car;
import com.example.Automobile.entity.Colors;
import com.example.Automobile.exceptionHandler.CarNotFoundException;
import com.example.Automobile.repository.CarRepository;
import com.example.Automobile.repository.ColorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ColorsService {

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private CarRepository carRepository;

    public List<ColorsDTO> getColorsByCarId(int carId){
        Car car = carRepository.findById(carId).orElseThrow(()->new CarNotFoundException("1002", "Car not found with id: "+carId));
        return colorRepository.findByCar(car)
                .stream()
                .map(colors ->mapColorsEntityToDTO(colors))
                .collect(Collectors.toList());
    }

    public ColorsDTO getColorsById(int carId, int colorId){
        Car car = carRepository.findById(carId).orElseThrow(()->new CarNotFoundException("1002", "Car not found with id: "+carId));
        Colors colors = colorRepository.findByIdAndCar(colorId, car).orElseThrow(()->new CarNotFoundException("1004", "Colors not found with id: "+colorId));
        return mapColorsEntityToDTO(colors);
    }

    public ColorsDTO createColors(int carId, ColorsDTO colorsDTO){
        Car car = carRepository.findById(carId).orElseThrow(()->new CarNotFoundException("1002", "Car not found with id: "+carId));
        Colors colors = mapDTOToColorsEntity(colorsDTO);
        colors.setCar(car);
        Colors savedColors = colorRepository.save(colors);
        return mapColorsEntityToDTO(savedColors);
    }

    public void deleteColors(int carId, int colorId){
        Car car = carRepository.findById(carId).orElseThrow(()->new CarNotFoundException("1002", "Car not found with id: "+carId));
        Colors colors = colorRepository.findByIdAndCar(colorId, car).orElseThrow(()->new CarNotFoundException("1004", "Colors not found with id: "+colorId));
        colorRepository.delete(colors);
    }

    public ColorsDTO updateColors(int carId, int colorId, ColorsDTO colorsDTO){
        Car car = carRepository.findById(carId).orElseThrow(()->new CarNotFoundException("1002", "Car not found with id: "+carId));
        Colors colors = colorRepository.findByIdAndCar(carId, car).orElseThrow(()->new CarNotFoundException("1004", "Colors not found with id: "+colorId));
        colors.setName(colorsDTO.getName());
        Colors updatedColors = colorRepository.save(colors);
        return mapColorsEntityToDTO(updatedColors);
    }

    private Colors mapDTOToColorsEntity(@Valid ColorsDTO colorsDTO){
        Colors colors = new Colors();
        colors.setId(colorsDTO.getId());
        colors.setName(colorsDTO.getName());
        return colors;
    }
    private ColorsDTO mapColorsEntityToDTO(Colors colors){
        ColorsDTO colorsDTO = new ColorsDTO();
        colorsDTO.setId(colors.getId());
        colorsDTO.setName(colors.getName());
        return colorsDTO;
    }

}
