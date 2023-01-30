import { React, useState } from 'react';
import { useSelector } from 'react-redux';
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import { Box, IconButton } from '@mui/material';
import ArrowBackIosIcon from '@mui/icons-material/ArrowBackIos';
import { useNavigate } from 'react-router';

import ArticleCard from '../../components/Main/ArticleCard';
import CommentInput from '../../components/Main/CommentInput';
import CommentView from '../../components/Main/CommentView';

const contStyle = css`
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  .mv-back {
    margin: 10px;
  }
`;

const PostDetail = () => {
  const navigate = useNavigate();
  const { article } = useSelector((state) => state.board);

  function mvBack() {
    navigate(-1);
  }

  return (
    <Box css={contStyle}>
      <IconButton className='mv-back' onClick={mvBack}>
        <ArrowBackIosIcon />
      </IconButton>
      <ArticleCard article={article} style={{ width: '100%' }} variant="detail" />
      <CommentInput />
      {
        article.comments.map((comm, index) => {
          console.log(comm);
          return <CommentView key={index} comment={comm} />;
        })
      }
    </Box>
  );
}

export default PostDetail;