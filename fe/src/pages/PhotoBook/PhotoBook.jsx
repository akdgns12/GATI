import { React, useState, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";

import SearchBar from "../../components/PhotoBook/SearchBar";
import AddButton from "../../components/Main/AddButton";
import PhotoCard from "../../components/PhotoBook/PhotoCard";
import {
  loadPhotoBook,
  updatePage,
  clearPhoto,
} from "../../store/PhotoBoard/photoBoard";

import { Grid, Box } from "@mui/material";
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
    console.log("YOUR MAIN GROUP HAS BEEN MODIFIED");
    // console.log(curPageNo);
    dispatch(clearPhoto());
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
  }, [mainGroup]);

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
    } else {
      console.log("IOB target not found");
    }
    return () => iObserver && iObserver.disconnect();
  }, [target, currentPage]);

  useEffect(() => {
    if (photoInfo != null && photoInfo.length > 0) {
      setLoaded(true);
    } else {
      console.log("NOTHING TO LOAD");
      setLoaded(false);
    }
  }, [photoInfo]);

  async function onIntersect(entries, nextPageNo) {
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
          if (payload.length > 0) {
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
      <AddButton />
      {loaded && (
        <Box ref={setTarget} style={{ textAlign: "center" }}>
          <RefreshIcon />
        </Box>
      )}
    </Grid>
  );
};

export default PhotoBook;
