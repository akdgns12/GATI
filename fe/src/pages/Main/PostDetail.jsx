import { React, useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import { Box, IconButton } from '@mui/material';
import ArrowBackIosIcon from '@mui/icons-material/ArrowBackIos';
import { useNavigate, useParams } from 'react-router';

import ArticleCard from '../../components/Main/ArticleCard';
import CommentInput from '../../components/Main/CommentInput';
import CommentView from '../../components/Main/CommentView';

import { loadPostDetail } from '../../store/Board/board';

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
  const { article } = useSelector((state) => state.board);
  const { loginUser } = useSelector((state) => state.user);

  const navigate = useNavigate();
  const dispatch = useDispatch();
  const articleId = useParams().articleId;

  useEffect(() => {
    // console.log("detail page mounted");
    const reqData = {
      articleId: articleId,
      userId: loginUser.userId,
    };
    dispatch(loadPostDetail(reqData))
      .then((data) => {
        console.log(data);
        setLoaded(true);
      })
      .catch((error) => {
        console.log(error);
      })
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
          <CommentInput articleId={article.id} />
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