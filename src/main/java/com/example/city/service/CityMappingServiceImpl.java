package com.example.city.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class CityMappingServiceImpl implements CityMappingService, CommandLineRunner {

	@Value("classpath:city.txt")
	private Resource res;

	@Autowired
	private CitiesMapping citiesMapping;

	@Override
	public String isCityConnected(String origin, String destination) throws IOException {
		// Validation
		if ((origin == null || origin.equalsIgnoreCase(""))
				|| (destination == null || destination.equalsIgnoreCase(""))) {
			return "Both Origin and Destination are Mandatory";
		} else {
			Map<String, List<String>> citesMapping = citiesMapping.getCityMapping(res);
			String citiesConnected = "NO";
			if (citesMapping.containsKey(origin)) {
				List<String> destinations = citesMapping.get(origin);				
				if (destinations.contains(destination)) {
					citiesConnected = "YES";
				}
			}
			
			if (citesMapping.containsKey(destination)) {
				List<String> origins = citesMapping.get(destination);				
				if (origins.contains(origin)) {
					citiesConnected = "YES";
				}
			}
			return citiesConnected;
		}

	}

	@Override
	public void run(String... args) throws Exception {

		
		  Map<String, List<String>> words = citiesMapping.getCityMapping(res);
		  
		  for (String key : words.keySet()) {
		  
		  System.out.println(key + ": " + words.get(key)); }
		 
	}

}
