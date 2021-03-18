package com.example.demo.Controller;

import com.example.demo.Entity.Autoshop;
import com.example.demo.Entity.Car;
import com.example.demo.Entity.GM;
import com.example.demo.Payload.Autoshopdto;
import com.example.demo.Payload.Gmdto;
import com.example.demo.Repository.AutoshopRepository;
import com.example.demo.Repository.CarRepository;
import com.example.demo.Repository.GmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class AutoshopController {

    @Autowired
    AutoshopRepository autoshopRepository;

    @Autowired
    GmRepository gmRepository;

    @Autowired
    CarRepository carRepository;

    @RequestMapping(value = "/autoshop", method = RequestMethod.GET)
    public List<Autoshop> getAutoshop() {

        List<Autoshop> autoshopList = autoshopRepository.findAll();

        return autoshopList;

    }

    @RequestMapping(value = "autoshop/{id}",method = RequestMethod.GET)
    public Autoshop getAutoshop(@PathVariable Integer id) {

        Optional<Autoshop> optionalAutoshop = autoshopRepository.findById(id);

        if (optionalAutoshop.isPresent()){

            Autoshop autoshop = optionalAutoshop.get();

            return autoshop;

        }else {

            return new Autoshop();

        }

    }

    @RequestMapping(value = "autoshops/{gmid}",method = RequestMethod.GET)
    public List<Autoshop> getAutoshops(@PathVariable Integer gmid) {

        List<Autoshop> autoshopList = autoshopRepository.findAllByGm_Id(gmid);

        return autoshopList;

    }



    @RequestMapping(value = "/autoshop", method = RequestMethod.POST)
    public String addAutoshop(@RequestBody Autoshopdto autoshopdto) {


            Autoshop autoshop = new Autoshop();

            autoshop.setName(autoshopdto.getName());

            autoshop.setAddress(autoshopdto.getAddress());

        Optional<GM> optionalGM = gmRepository.findById(autoshopdto.getGm_id());

        if (optionalGM.isPresent()){

            autoshop.setGm(optionalGM.get());

        }else {

            return "GM not found";
        }

        List<Integer> carList = autoshopdto.getCarList();


        Set<Car> carSet = new HashSet<>();

        for (Integer carId: carList ) {

            Optional<Car> optionalCar =  carRepository.findById(carId);

            if(optionalCar.isPresent()){
                carSet.add(optionalCar.get());
            }
        }

        autoshop.setCarList(carSet);

        autoshopRepository.save(autoshop);

        return "Autoshop added";


    }


    @RequestMapping(value = "/autoshop/{id}", method = RequestMethod.PUT)
    public String editAutoshop(@PathVariable Integer id, @RequestBody Autoshopdto autoshopdto) {


        Optional<Autoshop> optionalAutoshop = autoshopRepository.findById(id);

        if (optionalAutoshop.isPresent()){

                 Autoshop autoshop =  optionalAutoshop.get();

                 autoshop.setName(autoshopdto.getName());

                 autoshop.setAddress(autoshopdto.getAddress());

            Optional<GM> optionalGM = gmRepository.findById(autoshopdto.getGm_id());

            if (optionalGM.isPresent()){

                autoshop.setGm(optionalGM.get());

            }else if (!optionalGM.isPresent()){

                return "Gm not found";

            }

            List<Integer> carList = autoshopdto.getCarList();


            Set<Car> carSet = new HashSet<>();

            for (Integer carId: carList ) {

                Optional<Car> optionalCar =  carRepository.findById(carId);

                if(optionalCar.isPresent()){

                    carSet.add(optionalCar.get());

                }

            }

            autoshop.setCarList(carSet);

            autoshopRepository.save(autoshop);

        }

        return "Autoshop edited";


    }



    @RequestMapping(value = "/autoshop/{id}", method = RequestMethod.DELETE)
    public String deleteAutoshop(@PathVariable Integer id) {

        autoshopRepository.deleteById(id);

        return "Autoshop deleted";

    }




    }
