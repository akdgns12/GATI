package com.family.gati.service;

import com.family.gati.dto.CityDto;
import com.family.gati.entity.City;
import com.family.gati.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService{
    private final CityRepository cityRepository;
    @Override
    public List<CityDto> findAllOrderByTagCntLimit10(){
        List<City> cities = cityRepository.findAllOrderByTagCntLimit10();
        List<CityDto> result = new ArrayList<>();
        for (int i=0; i<10;i++){
            City city = cities.get(i);
            CityDto cityDto = new CityDto.CityDtoBuilder(city).build();
            result.add(cityDto);
        }
        return result;
    }

    @Override
    public List<CityDto> apiResponseData() throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B551011/KorService/areaBasedList?numOfRows=10"); /*URL*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("MobileOS","UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8")); /*측정소 이름*/
        urlBuilder.append("&" + URLEncoder.encode("MobileApp","UTF-8") + "=" + URLEncoder.encode("ggati", "UTF-8")); /*요청 데이터기간 (하루 : DAILY, 한달 : MONTH, 3달 : 3MONTH)*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=QUz1fBx92fphiG1OGe/291wDWUP4wjIMHXmxwTOnUt+38pbi3npnb/l/CfKHtQlbKsfbIi68Oy8hw89JzbVYsg=="); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*버전별 상세 결과 참고문서 참조*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            //listen json and how convert?
            List<json>
            cityRepository.insertTo()
        }
        conn.disconnect();
        return null;
    }


}
