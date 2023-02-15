import { React, useState, useEffect } from "react";
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
import KeyboardDoubleArrowRightIcon from "@mui/icons-material/KeyboardDoubleArrowRight";
import KeyboardDoubleArrowLeftIcon from "@mui/icons-material/KeyboardDoubleArrowLeft";
import ClearIcon from "@mui/icons-material/Clear";
import RefreshIcon from "@mui/icons-material/Refresh";

const PhotoBook = () => {
  const dispatch = useDispatch();
  const { loginUser } = useSelector((state) => state.user);
  const { currentPage, photoInfo } = useSelector((state) => state.photoBoard);
  const { search, setSearch } = useState("");
  const [loaded, setLoaded] = useState(false);
  const [target, setTarget] = useState(null);
  const { mainGroup } = useSelector((state) => state.user);

  const handleSearch = (event) => {
    setSearch(event.target.value);
  };
  useEffect(() => {
    // console.log("YOUR MAIN GROUP HAS BEEN MODIFIED");
    // console.log(curPageNo);
    if (mainGroup != null && mainGroup.id != null) {
      dispatch(
        loadPhotoBook({
          groupId: mainGroup.id,
          userId: loginUser.userId,
          page: 0,
        })
      )
        .then((res) => {
          // console.log(res);
          dispatch(updatePage(1));
        })
        .catch((error) => console.log(error));
    }
  }, []);

  // scroll
  useEffect(() => {
    console.log("add IOB");
    let iObserver;
    if (target) {
      iObserver = new IntersectionObserver(
        (entries) => onIntersect(entries, currentPage),
        {
          threshold: 1.0,
        }
      );
      iObserver.observe(target);
    } else {
      console.log("IOB target not found");
    }
    return () => iObserver && iObserver.disconnect();
  }, [target, currentPage]);

  useEffect(() => {
    console.log("SHOW PHOTOS");
    if (photoInfo != null && photoInfo.length > 0) {
      console.log("SHOW PHOTO");
      console.log(photoInfo);
      setLoaded(true);
    } else {
      console.log("NOTHING TO LOAD");
      setLoaded(false);
    }
  }, [photoInfo]);

  async function onIntersect(entries, nextPageNo) {
    console.log(entries);
    if (entries[0].isIntersecting) {
      setLoaded(false);
      console.log("LOAD MORE");
      dispatch(
        loadPhotoBook({
          groupId: mainGroup.id,
          userId: loginUser.userId,
          page: nextPageNo,
        })
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
        <SearchBar onchange={handleSearch} />
      </Grid>
      <Grid container spacing={2}>
        {photoInfo != null &&
          photoInfo.map((photo, index) => {
            return (
              <Grid item xs={6}>
                <PhotoCard
                  component="img"
                  photo={photo}
                  key={index}
                  image={photo.img}
                  sx={{ borderRadius: 1 }}
                  style={{ cursor: "pointer" }}
                />
              </Grid>
            );
          })}
      </Grid>
      {loaded && (
        <Box ref={setTarget} style={{ textAlign: "center" }}>
          <RefreshIcon />
        </Box>
      )}
      <AddButton />
    </Grid>
  );
};

export default PhotoBook;
