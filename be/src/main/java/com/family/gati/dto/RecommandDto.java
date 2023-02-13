package com.family.gati.dto;

import com.family.gati.entity.Recommand;
import com.family.gati.util.CommonBuilder;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
public class RecommandDto {
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

    public RecommandDto(RecommandDtoBuilder builder) {
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


    public static class RecommandDtoBuilder implements CommonBuilder<RecommandDto> {
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


        public RecommandDtoBuilder(Recommand recommand) {
            this.id = recommand.getId();
            this.addr1 = recommand.getAddr1();
            this.addr2 = recommand.getAddr2();
            this.areacode = recommand.getAreacode();
            this.booktour = recommand.getBooktour();
            this.cat1 = recommand.getCat1();
            this.cat2 = recommand.getCat2();
            this.cat3 = recommand.getCat3();
            this.contentid = recommand.getContentid();
            this.contenttypeid = recommand.getContenttypeid();
            this.createdtime = recommand.getCreatedtime();
            this.firstimage = recommand.getFirstimage();
            this.firstimage2 = recommand.getFirstimage2();
            this.mapx = recommand.getMapx();
            this.mapy = recommand.getMapy();
            this.mlevel = recommand.getMlevel();
            this.modifiedtime = recommand.getModifiedtime();
            this.readcount = recommand.getReadcount();
            this.sigungucode = recommand.getSigungucode();
            this.tel = recommand.getTel();
            this.title = recommand.getTitle();
            this.zipcode = recommand.getZipcode();
        }

        @Override
        public RecommandDto build() {
            return new RecommandDto(this);
        }
    }


}
