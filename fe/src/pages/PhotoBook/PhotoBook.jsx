import {React, useState, useEffect} from "react";
import { useDispatch, useSelector } from "react-redux";
import ReactDOM from "react-dom/client";

import SearchBar from "../../components/PhotoBook/SearchBar";
import NoImg from "../../components/PhotoBook/NoImg";
import AddButton from "../../components/Main/AddButton";
import PhotoCard from "../../components/PhotoBook/PhotoCard";
import { loadPhotoBook, updatePage } from "../../store/PhotoBoard/photoBoard";

import { Container, Grid, Box, Button } from "@mui/material";
import CardMedia from "@mui/material/CardMedia";
import CardContent from "@mui/material/CardContent";
import KeyboardDoubleArrowRightIcon from '@mui/icons-material/KeyboardDoubleArrowRight';
import KeyboardDoubleArrowLeftIcon from '@mui/icons-material/KeyboardDoubleArrowLeft';
import ClearIcon from '@mui/icons-material/Clear';

const PhotoBook = () => {
  const dispatch = useDispatch()
  const {loginUser} = useSelector((state) => state.user)
  const {photoInfo} = useSelector((state) => state.photoBoard)
  const {search, setSearch} = useState('')

  const [loaded, setLoaded] = useState(false);
  const [target, setTarget] = useState(null);
  const { currentPage } = useSelector((state) => state.photoBoard)
  // const {mainGroup} = useSelector((state) => {state.user})

  const handleSearch = (event) => {
    setSearch(event.target.value)
    
  }

  // scroll
  useEffect(() => {
    let iObserver;
    if (target) {
      iObserver = new IntersectionObserver(
        (entries) => onIntersect(entries, currentPage),
        {
          threshold: 1.0,
        }
      );
      iObserver.observe(target);
    }
    return () => iObserver && iObserver.disconnect();
  }, [target, currentPage]);

  async function onIntersect(entries, nextPageNo) {
    // console.log(entries);
    if (entries[0].isIntersecting) {
      setLoaded(false);
      console.log("LOAD MORE");
      dispatch(
        loadPhotoBook({ groupId: 1, userId: loginUser.userId, page: nextPageNo })
      )
        .then(({ payload }) => {
          // console.log(payload);
          if (payload.length > 1) {
            console.log("new feeds");
            dispatch(updatePage(nextPageNo + 1));
            setLoaded(true);
          }
        })
        .catch((error) => console.log(error));
      // console.log(articles);
    }
  }

  // useEffect(() => {
  //   dispatch(loadPhotoBook({ groupId: 1, userId: loginUser.userId, page: 0 }))
  //     .then((data) => {
  //     })
  //     .catch((error) => console.log(error));
  //   return () => {
  //   };
  // }, []);

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
