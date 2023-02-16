package com.family.gati.entity;

import com.family.gati.dto.RecommandDto;
import com.family.gati.util.CommonBuilder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "RECOMMAND")
public class Recommand {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue
    private Integer id;
    @Column(name = "ADDR1")
    private String addr1;
    @Column(name = "ADDR2")
    private String addr2;
    @Column(name = "AREACODE")
    private Integer areacode;
    @Column(name = "BOOKTOUR")
    private String booktour;
    @Column(name = "CAT1")
    private String cat1;
    @Column(name = "CAT2")
    private String cat2;
    @Column(name = "CAT3")
    private String cat3;
    @Column(name = "CONTENT_ID")
    private Integer contentid;
    @Column(name = "CONTENT_TYPE_ID")
    private Integer contenttypeid;
    @Column(name = "CREATE_TIME")
    private Date createdtime;
    @Column(name = "FIRST_IMAGE")
    private String firstimage;
    @Column(name = "FIRST_IMAGE2")
    private String firstimage2;
    @Column(name = "MAP_X")
    private Double mapx;
    @Column(name = "MAP_Y")
    private Double mapy;
    @Column(name = "M_LEVEL")
    private Integer mlevel;
    @Column(name = "MODIFIED_TIME")
    private Date modifiedtime;
    @Column(name = "READCOUNT")
    private Integer readcount;
    @Column(name = "SIGUNGUCODE")
    private Integer sigungucode;
    @Column(name = "TEL")
    private String tel;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "ZIPCODE")
    private String zipcode;

    private Recommand(RecommandBuilder builder) {
        this.id = builder.id;
        this.addr1 = builder.addr1;
        this.addr2 = builder.addr2;
        this.areacode = builder.areacode;
        this.booktour = builder.booktour;
        this.cat1 = builder.cat1;
        this.cat2 = builder.cat2;
        this.cat3 = builder.cat3;
        this.contentid = builder.contentid;
        this.contenttypeid = builder.contenttypeid;
        this.createdtime = builder.createdtime;
        this.firstimage = builder.firstimage;
        this.firstimage2 = builder.firstimage2;
        this.mapx = builder.mapx;
        this.mapy = builder.mapy;
        this.mlevel = builder.mlevel;
        this.modifiedtime = builder.modifiedtime;
        this.readcount = builder.readcount;
        this.sigungucode = builder.sigungucode;
        this.tel = builder.tel;
        this.title = builder.title;
        this.zipcode = builder.zipcode;
    }

    public static class RecommandBuilder implements CommonBuilder<Recommand> {
        private Integer id;
        private String addr1;
        private String addr2;
        private Integer areacode;
        private String booktour;
        private String cat1;
        private String cat2;
        private String cat3;
        private Integer contentid;
        private Integer contenttypeid;
        private Date createdtime;
        private String firstimage;
        private String firstimage2;
        private Double mapx;
        private Double mapy;
        private Integer mlevel;
        private Date modifiedtime;
        private Integer readcount;
        private Integer sigungucode;
        private String tel;
        private String title;
        private String zipcode;


        public RecommandBuilder(RecommandDto recommandDto) {
            this.id = recommandDto.getId();
            this.addr1 = recommandDto.getAddr1();
            this.addr2 = recommandDto.getAddr2();
            this.areacode = recommandDto.getAreacode();
            this.booktour = recommandDto.getBooktour();
            this.cat1 = recommandDto.getCat1();
            this.cat2 = recommandDto.getCat2();
            this.cat3 = recommandDto.getCat3();
            this.contentid = recommandDto.getContentid();
            this.contenttypeid = recommandDto.getContenttypeid();
            this.createdtime = recommandDto.getCreatedtime();
            this.firstimage = recommandDto.getFirstimage();
            this.firstimage2 = recommandDto.getFirstimage2();
            this.mapx = recommandDto.getMapx();
            this.mapy = recommandDto.getMapy();
            this.mlevel = recommandDto.getMlevel();
            this.modifiedtime = recommandDto.getModifiedtime();
            this.readcount = recommandDto.getReadcount();
            this.sigungucode = recommandDto.getSigungucode();
            this.tel = recommandDto.getTel();
            this.title = recommandDto.getTitle();
            this.zipcode = recommandDto.getZipcode();
        }

        @Override
        public Recommand build() {
            return new Recommand(this);
        }
    }
}
