package com.example.Automobile.service;

import com.example.Automobile.DTO.CarDTO;
import com.example.Automobile.entity.Car;
import com.example.Automobile.exceptionHandler.CarInternalErrorException;
import com.example.Automobile.exceptionHandler.CarNotFoundException;
import com.example.Automobile.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import springfox.documentation.annotations.Cacheable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {
    @Autowired
    CarRepository carRepository;

    //indicates that result of method is cached,
    //value "cars" is name of the cache
    @Cacheable("cars")
    public List<CarDTO> getAllCars() {
        try {
            List<Car> cars = new ArrayList<>();
            carRepository.findAll().forEach(car -> cars.add(car));
            if (cars.isEmpty()){
                throw new CarNotFoundException("1001", "No cars found");
            }
            else {
                return cars.stream()
                        //applies specified function to each element in the stream
                        //produces new stream of transformed elements
                        .map(car -> mapCarEntityToDTO(car))
                        //new stream converted into list
                        .collect(Collectors.toList());

            }
        } catch (CarInternalErrorException e) {
            throw new CarInternalErrorException("1003", "Internal Server Error");
        }
    }
    //indicates that result of method is cached,
    //value "carById" is name of the cache
    @Cacheable("carById")
    public CarDTO getCarById(int id){
        try {
            Car car = carRepository.findById(id).orElseThrow(() ->
                    new CarNotFoundException("1002", "Car not found with id:" + id));
            return mapCarEntityToDTO(car);
        }
            catch(CarInternalErrorException e) {
                throw new CarInternalErrorException("1003", "Internal Server Error");
            }
    }

    //used to update the cache with new value whenever method is called
    @CachePut(value = "carById")
    public void createCar(CarDTO carDTO){
        try {
            Car car = mapDTOToCarEntity(carDTO);
            carRepository.save(car);
        } catch (CarInternalErrorException e){
            throw new CarInternalErrorException("1003", "Internal Server error");
        }
    }

    //used to remove entries from the specified caches
    //cacheNames attribute specifies the names of the catches to evict
    //allEntries = true means that all entries in specified cache will be removed
    @CacheEvict(cacheNames = {"cars" , "carById"})
    public void deleteById(int id){
        try {
            carRepository.findById(id).orElseThrow(() ->
                    new CarNotFoundException("1002", "Car not found with id:" + id));
            carRepository.deleteById(id);
        }catch (CarInternalErrorException e){
            throw new CarInternalErrorException("1003", "Internal Server Error");
        }
    }

    //used to update the cache with result of annotation
    @CachePut("carById")
    public CarDTO updateCar(int id, CarDTO updatedCarDTO)
    {
        try {
            Car existingCar=carRepository.findById(id).orElseThrow(()-> new CarNotFoundException("1002", "Car not found with id: "+id));
                existingCar.setBrandName(updatedCarDTO.getBrandName());
                existingCar.setModel(updatedCarDTO.getModel());
                existingCar.setCyear(updatedCarDTO.getCyear());
                carRepository.save(existingCar);
                return mapCarEntityToDTO(existingCar);
            }
        catch (CarInternalErrorException e){
            throw new CarInternalErrorException("1003", "Internal Server Error");
        }
        //If brand not found
    }

    private CarDTO mapCarEntityToDTO(Car carEntity)
    {
        CarDTO carDTO = new CarDTO();
        carDTO.setId(carEntity.getId());
        carDTO.setBrandName(carEntity.getBrandName());
        carDTO.setModel(carEntity.getModel());
        carDTO.setCyear(carEntity.getCyear());
        return carDTO;
    }

    private Car mapDTOToCarEntity(CarDTO carDTO)
    {
        Car carEntity = new Car();
        carEntity.setId(carDTO.getId());
        carEntity.setBrandName(carDTO.getBrandName());
        carEntity.setModel(carDTO.getModel());
        carEntity.setCyear(carDTO.getCyear());
        return carEntity;
    }

}

