package com.example.demo.Repository;

import com.example.demo.Entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CarRepository extends JpaRepository<Car,Integer> {


    @Query(value = "select * from car where autoshop_id=:autoshop_id",nativeQuery = true)
    List<Car> carsByAutoshop_id(Integer autoshop_id);

}
