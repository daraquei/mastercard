package com.example.city.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.city.service.CityMappingService;


@RestController
public class CityController {
	
	  @Autowired
	  CityMappingService cityMappingService;
	
	  @GetMapping(value = "/connected")
	  public String getCityConnected(@RequestParam String origin, @RequestParam String destination) throws IOException
	  {
	     return cityMappingService.isCityConnected(origin, destination);
	  }

}
