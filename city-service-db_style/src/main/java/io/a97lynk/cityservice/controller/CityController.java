package io.a97lynk.cityservice.controller;


import io.a97lynk.cityservice.entity.City;
import io.a97lynk.cityservice.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody City city) {
        cityService.save(city);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<City>> getAll() throws SQLException {
        List<City> cities = cityService.getAll();
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<City> get(@PathVariable(value = "id") Long id) {
        City city = cityService.get(id);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public ResponseEntity<City> get(@PathVariable(value = "name") String name) {
        City city = cityService.getByName(name);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.DELETE)
    public ResponseEntity<City> delete(@PathVariable(value = "name") String name) {
        cityService.delete(name);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
