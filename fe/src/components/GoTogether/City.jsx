import React from 'react'
import ReactDOM from "react-dom/client";
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import "./City.css";
import Container from '@mui/material/Container';
export default function City(props) {
  // react-slick 설정값
  
  // 도시정보 
  const city = props.city;
  

  return (
      <div className="card">
        <div className="card-top">
          <img src={city.cityimg} alt="" />
        </div>
        <div className="card-bottom">
          <p>{city.placename}</p>
        </div>
      </div>
  )
}

