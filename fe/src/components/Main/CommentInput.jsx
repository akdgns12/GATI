import { React } from 'react';
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";

import { Box, OutlinedInput, IconButton, FormControl } from '@mui/material';
import ArrowCircleUpIcon from '@mui/icons-material/ArrowCircleUp';

const contStyle = css`
  width: 100%;
  margin-left: 5%;
  // align-item: center;
  .comment-area {
    width: 80%;
    font-size: meduim;
    background-color: #ededed;
  }
  .submit-btn {
    width: 15%;
  }
`;

const CommentInput = () => {

  function writeComment() {
    console.log("write comment");
  }

  return (
    <Box component="form" onSubmit={writeComment} css={contStyle}>

      <OutlinedInput className='comment-area' placeholder="댓글을 입력하세요." />

      <IconButton type='submit' className='submit-btn'>
        <ArrowCircleUpIcon color='primary' fontSize='large' />
      </IconButton>
    </Box>
  );
}

export default CommentInput;