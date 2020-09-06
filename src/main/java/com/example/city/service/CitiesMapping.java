package com.example.city.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class CitiesMapping {
	
	public Map<String, List<String>> getCityMapping(Resource res) throws IOException {
		
		Map<String, List<String>> citiesMapping = new HashMap<>();
		List<String> lines = Files.readAllLines(Paths.get(res.getURI()),
                StandardCharsets.UTF_8);
		for (String line : lines) {
			String[] cities = line.split(",");
			if (citiesMapping.containsKey(cities[0].trim())) {
				List<String> destination = citiesMapping.get(cities[0].trim());
				destination.add(cities[1].trim());
				citiesMapping.put(cities[0].trim(), destination);				
			} else {
				List<String> destination = new ArrayList<>();
				destination.add(cities[1].trim());
				citiesMapping.put(cities[0].trim(), destination);
				
			}
		}
		return citiesMapping;
		
	}

}
