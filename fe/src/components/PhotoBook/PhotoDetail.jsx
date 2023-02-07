import { React } from 'react';
import { useSelector } from 'react-redux';
import { useParams } from 'react-router';
import { css } from "@emotion/react";
import { Box, IconButton } from '@mui/material';
import ArrowBackIosIcon from '@mui/icons-material/ArrowBackIos';
import { useNavigate } from 'react-router';
import ArticleCard from '../Main/ArticleCard';
import CommentInput from '../Main/CommentInput';
import CommentView from '../Main/CommentView';
import PhotoData from '../PicsTogether/PhotoData';

const PhotoDetail = () => {
  const {photoId} = useParams()
  const thisPhoto = PhotoData.find(p=> p.id == photoId)
  const navigate = useNavigate();


  return (
    <div style={{
      display:'flex',
      flexDirection:'column',
      alignContent:'flex-start'
      }}>
      <IconButton
        style={{ justifyContent:'flex-start'}} 
        onClick={() => navigate(-1)}>
        <ArrowBackIosIcon />
      </IconButton>
      <ArticleCard article={thisPhoto} style={{ width: '100%' }} variant="detail" mode="photobook"/>
      <CommentInput />
      {
        thisPhoto.comments.map((comm, index) => {
          return <CommentView key={index} comment={comm} />;
        })
      }
    </div>
  );
}

export default PhotoDetail