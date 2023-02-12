import { React } from 'react';
import { useSelector } from 'react-redux';
import { useParams } from 'react-router';
import { css } from "@emotion/react";
import { Box, IconButton } from '@mui/material';
import ArrowBackIosIcon from '@mui/icons-material/ArrowBackIos';
import { useNavigate } from 'react-router';
import CommentInput from '../Main/CommentInput';
import CommentView from '../Main/CommentView';
import PhotoData from './PhotoData';
import PhotoCard from './PhotoCard';

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

    </div>
  );
}

export default PhotoDetail