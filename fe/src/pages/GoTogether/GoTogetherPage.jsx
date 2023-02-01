import React, {Component} from "react";
import ReactDOM from "react-dom/client";
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';

import Slider from 'react-slick';
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';

export default function GoTogether() {
  const settings = {
    dots: true,
    infinite: true,
    speed: 500,
    slidesToShow: 3,
    slidesToScroll: 3
  };
  return (
    <Grid>
      <Typography variant="h4" sx={{display: 'flex', justifyContent: 'start', m: 2}}>
        도시명
      </Typography>
      <Slider {...settings}>
          <Box>
            <Typography>1</Typography>
          </Box>
          <Box>
            <Typography>2</Typography>
          </Box>
          <Box>
            <Typography>3</Typography>
          </Box>
          <Box>
            <Typography>4</Typography>
          </Box>
        </Slider>
    </Grid>
  )
}

