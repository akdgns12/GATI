import { React, useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import { Box, IconButton, Button } from "@mui/material";
import ArrowBackIosIcon from "@mui/icons-material/ArrowBackIos";
import { useNavigate, useParams } from "react-router";

import PhotoCard from "../../components/PhotoBook/PhotoCard";

import httpClient from "../../utils/axios";
import CommentInput2 from "./CommentInput2";
import CommentView from "../../components/Main/CommentView";
import { loadPhotoDetail } from "../../store/PhotoBoard/photoBoard";

const PhotoDetail = () => {
  const [loaded, setLoaded] = useState(false);
  const { photoDetail } = useSelector((state) => state.photoBoard);
  const { loginUser } = useSelector((state) => state.user);

  const navigate = useNavigate();
  const dispatch = useDispatch();
  const photoId = useParams().photoId;

  useEffect(() => {
    const reqData = {
      photoId: photoId,
      userId: loginUser.userId,
    };
    dispatch(loadPhotoDetail(reqData))
      .then((data) => {
        // console.log(data);
        setLoaded(true);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);
  // 게시물 삭제
  const DeletePhoto = () => {
    const reqData = photoId;
    httpClient
      .delete("/albums/album/" + reqData)
      .then((data) => {
        console.log(data);
        alert("album deleted");
        navigate("/photobook");
      })
      .catch((error) => {
        console.log(error);
        alert("failed to delete album");
      });
  };
  function mvBack() {
    navigate("/photobook");
  }

  return (
    <Box>
      <IconButton className="mv-back" onClick={mvBack}>
        <ArrowBackIosIcon />
      </IconButton>
      {loaded == true && (
        <>
          <Button onClick={DeletePhoto} variant="outlined">
            사진 삭제
          </Button>
          <PhotoCard
            photo={photoDetail}
            style={{ width: "auto" }}
            variant="detail"
          />
          <CommentInput2 photoId={photoDetail.id} />
        </>
      )}
      {photoDetail != null &&
        photoDetail.albumCommentDtos != null &&
        photoDetail.albumCommentDtos.map((comment, index) => {
          return <CommentView key={index} comment={comment} />;
        })}
    </Box>
  );
};

export default PhotoDetail;
