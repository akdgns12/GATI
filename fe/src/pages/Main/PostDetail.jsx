import { React, useEffect, useLayoutEffect, useState } from 'react';
import { useSelector } from 'react-redux';
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import { Box, IconButton } from '@mui/material';
import ArrowBackIosIcon from '@mui/icons-material/ArrowBackIos';
import { useNavigate, useParams } from 'react-router';

import ArticleCard from '../../components/Main/ArticleCard';
import CommentInput from '../../components/Main/CommentInput';
import CommentView from '../../components/Main/CommentView';

import httpClient from '../../utils/axios';

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
  const [loaded, setLoaded] = useState(false);
  const [article, setArticle] = useState({});

  const navigate = useNavigate();
  const articleId = useParams().articleId;

  useLayoutEffect(() => {
    console.log("detail page mounted");
    httpClient.get("/boards/board/" + articleId)
      .then((res) => {
        setArticle(res.data);
        setLoaded(true);
      })
      .catch((error) => {
        console.log(error);
      });
  }, [])

  function mvBack() {
    navigate(-1);
  }

  return (
    <Box css={contStyle}>
      <IconButton className='mv-back' onClick={mvBack}>
        <ArrowBackIosIcon />
      </IconButton>
      {
        loaded == true &&
        <>
          <ArticleCard article={article} style={{ width: '100%' }} variant="detail" />
          <CommentInput />
        </>
      }
      {
        article != null && article.comment != null &&
        article.comment.map((comm, index) => {
          return <CommentView key={index} comment={comm} />;
        })
      }
    </Box>
  );
}

export default PostDetail;