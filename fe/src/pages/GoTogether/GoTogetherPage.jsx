import React, {Component} from "react";
import ReactDOM from "react-dom/client";
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import Slider from 'react-slick';
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';
import City from "../../components/GoTogether/City";
import { useSelector } from "react-redux";
import { borderRadius, margin } from "@mui/system";



export default function GoTogether() {
  
  const { cities } = useSelector((state) => state.city)
  const settings = {
    dots: true,
    infinite: true,
    speed: 500,
    slidesToShow: 3,
    slidesToScroll: 3,
    initialSlide: 0,
    responsive: [
      {
        breakpoint: 1024,
        settings: {
          // 한페이지에 몇장 띄울건지 정해주는 값
          slidesToShow: 3,
          slidesToScroll: 3,
          infinite: true,
          dots: true
        }
      },
      {
        breakpoint: 600,
        settings: {
          slidesToShow: 3,
          slidesToScroll: 3,
          initialSlide: 2
        }
      },
      {
        breakpoint: 480,
        settings: {
          slidesToShow: 3,
          slidesToScroll: 3
        }
      }
    ]
  };
  return (
    <Container>
      <Box sx={{ height: '100vh'}}>
        <Box sx={{bgcolor: 'white', borderRadius: 1, margin: 3}}>
          <h1>서울</h1>
          <Slider {...settings}>
            {cities.map((city, index) => {
              if (city.cityname === '서울')
                return <City key={index} city={city}/>
            })}
          </Slider>
        </Box>
        <Box sx={{bgcolor: 'white', borderRadius: 1, margin: 3}}>
          <h1>부산</h1>
          <Slider {...settings}>
            {cities.map((city, index) => {
              if (city.cityname === '부산')
                return <City key={index} city={city}/>
            })}
          </Slider>
        </Box>
      </Box>
    </Container>
  )
}

