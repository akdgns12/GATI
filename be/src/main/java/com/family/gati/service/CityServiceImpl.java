package com.family.gati.service;

import com.family.gati.dto.CityDto;
import com.family.gati.dto.RecommandDto;
import com.family.gati.entity.City;
import com.family.gati.entity.Recommand;
import com.family.gati.repository.CityRepository;
import com.family.gati.repository.RecommandRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final RecommandRepository recommandRepository;

    @Override
    public List<CityDto> findAllOrderByTagCnt(){
        List<City> cities = cityRepository.findAllOrderByTagCntDesc();
        List<CityDto> result = new ArrayList<>();
        for (int i=0; i<10;i++){
            City city = cities.get(i);
            CityDto cityDto = new CityDto.CityDtoBuilder(city).build();
            result.add(cityDto);
        }
        return result;
    }

    @Override
    public void pushCityDb() {
        for (int i = 1; i <= 8; i++) {
            List<Recommand> recommands = recommandRepository.findAllByAreacodeOrderBySigungucode(i);
            if (recommands.size() == 0)
                continue;
            for (Recommand recommand : recommands) {
                if (recommand.getSigungucode() == -1)
                    continue;
                if (recommand.getSigungucode() != null
                        && recommand.getAddr1() != null && !recommand.getAddr1().isEmpty()) {
                    System.out.println(recommand.getAddr1());
                    System.out.println(recommand.getAddr1().isEmpty());
                    City city = new City(recommand, 0);
                    cityRepository.save(city);
                    break;
                }
            }
        }
        for (int i = 31; i <= 39; i++) {
            for (int j = 1; j <= 31; j++) {
                List<Recommand> recommands = recommandRepository.findAllByAreacodeAndSigungucode(i, j);
                if (recommands.size() == 0)
                    break;
                for (Recommand recommand : recommands) {
                    if (recommand.getAddr1() != null && !recommand.getAddr1().isEmpty()) {
                        System.out.println(recommand.getAddr1());
                        System.out.println(recommand.getAddr1().isEmpty());
                        City city = new City(recommand, 1);
                        cityRepository.save(city);
                        break;
                    }
                }
            }
        }
    }
}
