package com.family.gati.service;

import com.family.gati.dto.RecommandDto;
import com.family.gati.entity.Recommand;
import com.family.gati.repository.RecommandRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@Service
@RequiredArgsConstructor
public class RecommandServiceImpl implements RecommandService{
    private final RecommandRepository recommandRepository;

    @Override
    public void apiResponseData() throws Exception {

        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B551011/KorService/areaBasedList"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "="+URLEncoder.encode("QUz1fBx92fphiG1OGe%2F291wDWUP4wjIMHXmxwTOnUt%2B38pbi3npnb%2Fl%2FCfKHtQlbKsfbIi68Oy8hw89JzbVYsg%3D%3D")); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("MobileOS","UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8")); /*측정소 이름*/
        urlBuilder.append("&" + URLEncoder.encode("MobileApp","UTF-8") + "=" + URLEncoder.encode("AppTest", "UTF-8")); /*요청 데이터기간 (하루 : DAILY, 한달 : MONTH, 3달 : 3MONTH)*/
        urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*버전별 상세 결과 참고문서 참조*/
//        StringBuilder urlBuilder2 = new StringBuilder("http://apis.data.go.kr/B551011/KorService/areaBasedList"); /*URL*/
//        urlBuilder2.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "="+URLEncoder.encode("QUz1fBx92fphiG1OGe/291wDWUP4wjIMHXmxwTOnUt+38pbi3npnb/l/CfKHtQlbKsfbIi68Oy8hw89JzbVYsg==")); /*Service Key*/
//        urlBuilder2.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
//        urlBuilder2.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*페이지 번호*/
//        urlBuilder2.append("&" + URLEncoder.encode("MobileApp","UTF-8") + "=" + URLEncoder.encode("ggati", "UTF-8")); /*요청 데이터기간 (하루 : DAILY, 한달 : MONTH, 3달 : 3MONTH)*/
//        urlBuilder2.append("&" + URLEncoder.encode("MobileOS","UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8")); /*측정소 이름*/
//        urlBuilder2.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*버전별 상세 결과 참고문서 참조*/
        //http://apis.data.go.kr/B551011/KorService/areaBasedList?
        // ServiceKey = QUz1fBx92fphiG1OGe%2F291wDWUP4wjIMHXmxwTOnUt%2B38pbi3npnb%2Fl%2FCfKHtQlbKsfbIi68Oy8hw89JzbVYsg%3D%3D
        // &numOfRows= 15 &pageNo= 1 &MobileOS= ETC &MobileApp= ggati &_type =json &listYN =Y &arrange = C
        URL url = new URL(urlBuilder.toString());
//        URL url2 = new URL(urlBuilder2.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        conn.setConnectTimeout(500000000);
        System.out.println("Response code: " + conn.getResponseCode());
        RestTemplate restTemplate = new RestTemplate();
        //http://apis.data.go.kr/B551011/KorService/areaBasedList?ServiceKey=QUz1fBx92fphiG1OGe%2F291wDWUP4wjIMHXmxwTOnUt%2B38pbi3npnb%2Fl%2FCfKHtQlbKsfbIi68Oy8hw89JzbVYsg%3D%3D&numOfRows=15&pageNo=1&MobileOS=ETC&MobileApp=ggati&_type=json
        URI uri = new URI("http://apis.data.go.kr/B551011/KorService/areaBasedList?ServiceKey=QUz1fBx92fphiG1OGe%2F291wDWUP4wjIMHXmxwTOnUt%2B38pbi3npnb%2Fl%2FCfKHtQlbKsfbIi68Oy8hw89JzbVYsg%3D%3D&numOfRows=15&pageNo=1&MobileOS=ETC&MobileApp=ggati&_type=json");
        String jsonString = restTemplate.getForObject(uri, String.class);


//        BufferedReader br;
//        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
//            //listen json and how convert?
//            br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
//        }else{
//            br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
//        }
//        StringBuilder sb= new StringBuilder();
//        String line;
//        while((line=br.readLine())!=null){
//            sb.append(line);
//        }

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();
        SimpleDateFormat formmater = new SimpleDateFormat("yyyyMMddHHmmss");
        map = objectMapper.readValue(jsonString, new TypeReference<Map<String, Object>>(){});

        Map<String , Object> getRecommend = (Map<String, Object>) map.get("response");
        List<Map<String, Object>> itemList = (List<Map<String, Object>>) getRecommend.get("items");

        System.out.println(map.get("response"));
        System.out.println(getRecommend.get("body"));
        System.out.println(getRecommend.get("items"));

        List<RecommandDto> recommandDtos = new ArrayList<>();

        for (Map<String, Object> item : itemList){
            RecommandDto dto = new RecommandDto();
            dto.setAddr1(item.get("addr1").toString());
            dto.setAddr2(item.get("addr2").toString());
            dto.setAreacode(Integer.valueOf(item.get("areacode").toString()));
            dto.setBooktour(item.get("booktour").toString());
            dto.setCat1(item.get("cat1").toString());
            dto.setCat2(item.get("cat2").toString());
            dto.setCat3(item.get("cat3").toString());
            dto.setContentid(Integer.valueOf(item.get("contentid").toString()));
            dto.setContenttypeid(Integer.valueOf(item.get("contenttypeid").toString()));
            dto.setCreatedtime(formmater.parse(item.get("createdtime").toString()));
            dto.setFirstimage(item.get("firstimage").toString());
            dto.setFirstimage2(item.get("firstimage2").toString());
            dto.setMapx(Double.valueOf(item.get("mapx").toString()));
            dto.setMapy(Double.valueOf(item.get("mapy").toString()));
            dto.setMlevel(Integer.valueOf(item.get("mlevel").toString()));
            dto.setModifiedtime(formmater.parse(item.get("modifiedtime").toString()));
            dto.setReadcount(Integer.valueOf(item.get("readcount").toString()));
            dto.setSigungucode(Integer.valueOf(item.get("sigungucode").toString()));
            dto.setTel(item.get("tel").toString());
            dto.setTitle(item.get("title").toString());
            dto.setZipcode(Integer.valueOf(item.get("zipcode").toString()));
            recommandDtos.add(dto);
            Recommand recommand = new Recommand.RecommandBuilder(dto).build();
            recommandRepository.save(recommand);
        }

//        br.close();
        conn.disconnect();
    }
}
