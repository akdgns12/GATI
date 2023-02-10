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

    public void plusTagCnt(Integer num){ this.tagCnt += num; }

    private City(CityBuilder builder){
        this.id = builder.id;
        this.tag = builder.tag;
        this.tagCnt = builder.tagCnt;
        this.tagId = builder.tagId;
    }

    public static class CityBuilder implements CommonBuilder<City>{
        private Integer id;
        private String tag;
        private Integer tagCnt;
        private Integer tagId;

        public CityBuilder(CityDto cityDto){
            this.id = cityDto.getId();
            this.tag = cityDto.getTag();
            this.tagCnt = cityDto.getTagCnt();
            this.tagId = cityDto.getTagId();
        }
        public City build(){
            return new City(this);
        }
    }
}
