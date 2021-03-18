package com.example.demo.Repository;

import com.example.demo.Entity.Autoshop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutoshopRepository extends JpaRepository<Autoshop,Integer> {

List<Autoshop> findAllByGm_Id(Integer gm_id);

}
