package com.family.gati.repository;

import com.family.gati.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

    @Query("select c from City as c order by c.tagCnt")
    List<City> findAllOrderByTagCntDesc();

}
