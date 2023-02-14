import React from "react";
import ReactDOM from "react-dom/client";
import SearchBar from "../../components/PhotoBook/SearchBar";

import NoImg from "../../components/PhotoBook/NoImg";


import AddButton from "../../components/Main/AddButton";
import { Container, Grid, Box, Button } from "@mui/material";
import { useDispatch, useSelector } from "react-redux";
import { loadPhotoBook } from "../../store/PhotoBoard/photoBoard";
import { useEffect, useState } from "react";
import CardMedia from "@mui/material/CardMedia";
import CardContent from "@mui/material/CardContent";

import KeyboardDoubleArrowRightIcon from '@mui/icons-material/KeyboardDoubleArrowRight';
import KeyboardDoubleArrowLeftIcon from '@mui/icons-material/KeyboardDoubleArrowLeft';
import ClearIcon from '@mui/icons-material/Clear';
import PhotoCard from "../../components/PhotoBook/PhotoCard";

const PhotoBook = () => {
  const dispatch = useDispatch()
  const {loginUser} = useSelector((state) => state.user)
  const {photoInfo} = useSelector((state) => state.photoBoard)
  const {search, setSearch} = useState('')

  const handleSearch = (event) => {
    setSearch(event.target.value)
    
  }

  useEffect(() => {
    dispatch(loadPhotoBook({ groupId: 1, userId: loginUser.userId, page: 0 }))
      .then((data) => {
      })
      .catch((error) => console.log(error));
    return () => {
    };
  }, []);

  return (
    <Grid container>
      <Grid item xs={12}>
        <SearchBar onchange={handleSearch}/>
      </Grid>
      <Grid container spacing={2}>
        {photoInfo != null &&
          photoInfo.map((photo, index) => {
            return (
              <Grid item xs={6} >
                <PhotoCard
                  component="img"
                  photo={photo}
                  key={index}
                  image={photo.img}
                  sx={{ borderRadius: 1 }}
                  style={{cursor: 'pointer'}}
                  />
              </Grid>
              );
            })}
        </Grid>
        <AddButton/>
    </Grid>
  )
}

export default PhotoBook;
