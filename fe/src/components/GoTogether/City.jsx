import React from "react";
import ReactDOM from "react-dom/client";
import Box from "@mui/material/Box";
import Grid from "@mui/material/Grid";
import { Card, CardContent, CardHeader, CardMedia } from "@mui/material";
import Typography from "@mui/material/Typography";
import "./City.css";
import Container from "@mui/material/Container";
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
export default function City(props) {
  // 도시정보
  const city = props.city;

  const settings = {
    dots: false,
    infinite: true,
    speed: 500,
    slidesToShow: 5,
    slidesToScroll: 5,
    initialSlide: 0,
    adaptiveHeight: true,

    responsive: [
      {
        breakpoint: 1024,
        settings: {
          // 한페이지에 몇장 띄울건지 정해주는 값
          slidesToShow: 3,
          slidesToScroll: 3,
          infinite: true,
          dots: false,
          // adaptiveHeight: true,
        },
      },
      {
        breakpoint: 600,
        settings: {
          infinite: true,
          slidesToShow: 2,
          slidesToScroll: 2,
          initialSlide: 2,
          // adaptiveHeight: true,
        },
      },
      {
        breakpoint: 480,
        settings: {
          infinite: true,
          slidesToShow: 1,
          slidesToScroll: 1,
          initialSlide: 1,
          // adaptiveHeight: true,
        },
      },
    ],
  };

  return (
    <Card
      elevation={3}
      sx={{ borderRadius: 5 }}
      style={{ marginBottom: "20px", width: "100%" }}
    >
      <Typography textAlign="center" p={1} variant="h6">
        {city.tag} top 10
      </Typography>
      <Slider {...settings} style={{ height: "25vh" }}>
        {city.recommandDtos.map((img, idx) => {
          return (
            <>
              <CardMedia height={170} component="img" image={img.firstimage} />
              <CardContent style={{ padding: "2px", margin: "2px" }}>
                <Typography textAlign="center" color="#FF9494">
                  {img.title}
                </Typography>
              </CardContent>
            </>
          );
        })}
      </Slider>
    </Card>
  );
}
