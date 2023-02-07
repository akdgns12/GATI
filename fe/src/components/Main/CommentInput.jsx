import { React } from 'react';
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";

import { Box, CardContent, OutlinedInput, IconButton, FormControl } from '@mui/material';
import ArrowCircleUpIcon from '@mui/icons-material/ArrowCircleUp';

import httpClient from '../../utils/axios';
import { useNavigate } from 'react-router';

const contStyle = css`
  width: 100%;
  // margin-left: 5%;
  // align-item: center;
  .comment-area {
    width: 80%;
    height:40px;
    font-size: meduim;
    background-color: #ededed;
  }
  .submit-btn {
    width: 15%;
  }
`;

const CommentInput = (props) => {

  function writeComment(event) {
    event.preventDefault();
    console.log("write comment");
    console.log(props.articleId);
    console.log(event.target.comment.value);
    httpClient.post("/boards/comment/", {
      boardId: props.articleId,
      content: event.target.comment.value,
    })
      .then((data) => {
        console.log(data);
      })
      .catch((error) => {
        console.log(error);
      })
  }

  return (
    <CardContent component="form" onSubmit={writeComment} css={contStyle}>
      <Box style={{
        fontWeight: 'bold', textAlign: 'left', marginBottom: '8px',
      }}> Comments </Box>
      <OutlinedInput className='comment-area' placeholder="댓글을 입력하세요." name="comment" />

      <IconButton type='submit' className='submit-btn'>
        <ArrowCircleUpIcon color='primary' fontSize='large' />
      </IconButton>
    </CardContent>
  );
}

export default CommentInput;