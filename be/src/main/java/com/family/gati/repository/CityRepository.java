package com.family.gati.repository;

import com.family.gati.entity.City;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository {
    List<City> findAllOrderByTagCntLimit10();
}
