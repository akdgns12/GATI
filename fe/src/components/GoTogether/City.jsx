import React from "react";
import ReactDOM from "react-dom/client";
import Box from "@mui/material/Box";
import Grid from "@mui/material/Grid";
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
    slidesToShow: 2,
    slidesToScroll: 2,
    initialSlide: 0,
    responsive: [
      {
        breakpoint: 1024,
        settings: {
          // 한페이지에 몇장 띄울건지 정해주는 값
          slidesToShow: 2,
          slidesToScroll: 2,
          infinite: true,
          dots: true,
        },
      },
      {
        breakpoint: 600,
        settings: {
          slidesToShow: 2,
          slidesToScroll: 2,
          initialSlide: 2,
        },
      },
      {
        breakpoint: 480,
        settings: {
          slidesToShow: 2,
          slidesToScroll: 2,
        },
      },
    ],
  };

  return (
    <Box>
      <Typography variant="h6">{city.tag}</Typography>
      <Slider {...settings}>
        {city.recommandDtos.map((img, idx) => {
          return <img src={img.firstimage} alt="" />;
        })}
      </Slider>
    </Box>
  );
}
