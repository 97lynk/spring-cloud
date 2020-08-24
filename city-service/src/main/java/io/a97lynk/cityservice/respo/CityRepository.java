package io.a97lynk.cityservice.respo;

import io.a97lynk.cityservice.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {

    Optional<City> findById(Long id);

    City findByName(String name);

    void deleteByName(String name);
}
