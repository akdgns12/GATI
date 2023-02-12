import React, {useState} from 'react'
import Card from "@mui/material/Card";
import { Box, Container, Grid, Button} from "@mui/material";
import CardHeader from "@mui/material/CardHeader";
import CardMedia from "@mui/material/CardMedia";
import CardContent from "@mui/material/CardContent";
import CardActions from "@mui/material/CardActions";
import IconButton from "@mui/material/IconButton";
import Typography from "@mui/material/Typography";
import FavoriteIcon from "@mui/icons-material/Favorite";
import BookmarkBorderIcon from "@mui/icons-material/BookmarkBorder";
import MoreHorizIcon from "@mui/icons-material/MoreHoriz";

import { useNavigate } from "react-router";
import { useSelector } from "react-redux";

import httpClient from "../../utils/axios";


export default function PhotoCard(props) {
  const navigate = useNavigate()
  const photo = props.photo
  // 클릭하면 디테일 페이지로
  const moveToDetail = () => {
    const url = '/photobook/detail/' + photo.id;
    navigate(url);
  }


  return (
      <Grid item xs={6}>

        <CardMedia
          component="img"
          height="150px"
          image={photo.img}
          sx={{ borderRadius: 1 }}
          style={{cursor: 'pointer'}}
          onClick={moveToDetail}
          />
      </Grid>
      
  )
}
