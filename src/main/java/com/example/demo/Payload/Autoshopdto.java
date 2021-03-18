package com.example.demo.Payload;

import com.example.demo.Entity.Car;

import lombok.Data;


import java.util.List;

@Data
public class Autoshopdto {




    private String name;


    private String address;


    private Integer gm_id;


    private List<Integer> carList;


}
