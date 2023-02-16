package com.family.gati.entity;

import com.family.gati.dto.CityDto;
import com.family.gati.util.CommonBuilder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Table(name = "CITY")
public class City {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "TAG", nullable = false)
    private String tag;
    @ColumnDefault("0")
    @Column(name = "TAG_CNT", nullable = false)
    private Integer tagCnt;
    @Column(name = "TAG_ID", nullable = false)
    private Integer tagId;
    @Column(name = "SIGUNGUCODE", nullable = false)
    private Integer sigungucode;

    public void plusTagCnt(Integer num){ this.tagCnt += num; }

    public City(Recommand recommand, Integer mode) {
        if (mode == 0)
            this.tag = recommand.getAddr1().substring(0, 2);
        else {
            String addr = recommand.getAddr1().split(" ")[1];
            if (addr.charAt(2) != '시' & addr.charAt(2) != '군' & addr.charAt(2) != '구')
                this.tag = addr.substring(0, 3);
            else
                this.tag = addr.substring(0, 2);
        }
        this.tagCnt = 0;
        this.tagId = recommand.getAreacode();
        this.sigungucode = recommand.getSigungucode();
    }

    private City(CityBuilder builder){
        this.id = builder.id;
        this.tag = builder.tag;
        this.tagCnt = builder.tagCnt;
        this.tagId = builder.tagId;
        this.sigungucode = builder.sigungucode;
    }

    public static class CityBuilder implements CommonBuilder<City>{
        private Integer id;
        private String tag;
        private Integer tagCnt;
        private Integer tagId;
        private Integer sigungucode;

        public CityBuilder(CityDto cityDto){
            this.id = cityDto.getId();
            this.tag = cityDto.getTag();
            this.tagCnt = cityDto.getTagCnt();
            this.tagId = cityDto.getTagId();
            this.sigungucode = cityDto.getSigungucode();
        }
        public City build(){
            return new City(this);
        }
    }
}
