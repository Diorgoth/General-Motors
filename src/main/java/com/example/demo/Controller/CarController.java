package com.example.demo.Controller;

import com.example.demo.Entity.Autoshop;
import com.example.demo.Entity.Car;
import com.example.demo.Payload.Autoshopdto;
import com.example.demo.Payload.Cardto;
import com.example.demo.Payload.Gmdto;
import com.example.demo.Repository.AutoshopRepository;
import com.example.demo.Repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CarController {

    @Autowired
    CarRepository carRepository;
    @Autowired
    AutoshopRepository autoshopRepository;

    @RequestMapping(value = "/car", method = RequestMethod.GET)
    public List<Car> getCar() {

        List<Car> carList = carRepository.findAll();

        return carList;

    }

    @RequestMapping(value = "/car/{id}",method = RequestMethod.GET)
    public Car getCar(@PathVariable Integer id) {

        Optional<Car> optionalCar = carRepository.findById(id);

        if (optionalCar.isPresent())
        {
       Car car = optionalCar.get();
            return  car;
        }else {
            return new Car();
        }

    }


    @RequestMapping(value = "/cars/{autoshop_id}",method = RequestMethod.GET)
    public List<Car> getCars(@PathVariable Integer autoshop_id) {

        List<Car> carList = carRepository.carsByAutoshop_id(autoshop_id);

        return carList;

    }




    @RequestMapping(value = "/car", method = RequestMethod.POST)
    public String addCar(@RequestBody Cardto cardto) {

        Car car = new Car();

        car.setModel(cardto.getModel());

        car.setPrice(cardto.getPrice());

        car.setYear(cardto.getYear());

        Optional<Autoshop> optionalAutoshop = autoshopRepository.findById(cardto.getAutoshop_id());

        if (optionalAutoshop.isPresent()){

            car.setAutoshop(optionalAutoshop.get());

            carRepository.save(car);

            return "Car added";

        }else {
            return "Autoshop not found";
        }



    }


    @RequestMapping(value = "/car/{id}", method = RequestMethod.PUT)
    public String editCar(@PathVariable Integer id, @RequestBody Cardto cardto) {

        Optional<Car> optionalCar = carRepository.findById(id);

        if (optionalCar.isPresent()){

            Car car = optionalCar.get();

            car.setModel(cardto.getModel());

            car.setYear(cardto.getYear());

            car.setPrice(cardto.getPrice());

            Optional<Autoshop> optionalAutoshop = autoshopRepository.findById(cardto.getAutoshop_id());

            if (optionalCar.isPresent()){

                car.setAutoshop(optionalAutoshop.get());

                carRepository.save(car);
                return "Car edited";

            }else {
                return "Auto shop not found";
            }


        }
        return "Car not found";


    }

    @RequestMapping(value = "/car/{id}", method = RequestMethod.DELETE)
    public String deleteCar(@PathVariable Integer id) {

          carRepository.deleteById(id);

        return "Car deleted";

    }



    }
