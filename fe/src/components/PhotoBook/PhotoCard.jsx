import React, { useState } from "react";
import Card from "@mui/material/Card";
import { Box, Container, Grid, Button } from "@mui/material";
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
  const navigate = useNavigate();
  const photo = props.photo;
  var imgPath = process.env.REACT_APP_IMG_ROOT + "/" + photo.img;
  // 클릭하면 디테일 페이지로
  const moveToDetail = () => {
    const url = "/photobook/detail/" + photo.id;
    navigate(url);
  };

  return (
    <>
      {/* {photo.tag != null &&
        photo.tag.map((tag, index) => {
        return <>#{tag.tagContent}&nbsp;</>;
        })} */}
      <CardMedia
        component="img"
        height="150px"
        image={imgPath}
        sx={{ borderRadius: 1 }}
        style={{ cursor: "pointer" }}
        onClick={moveToDetail}
      />
    </>
  );
}
