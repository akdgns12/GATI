import { React } from 'react';
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";

import { CardContent, OutlinedInput, IconButton, FormControl } from '@mui/material';
import ArrowCircleUpIcon from '@mui/icons-material/ArrowCircleUp';

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

const CommentInput = () => {

  function writeComment() {
    console.log("write comment");
  }

  return (
    <CardContent component="form" onSubmit={writeComment} css={contStyle}>
      <div style={{
        fontWeight:'bold', textAlign:'left', marginBottom:'8px',
      }}> Comments </div>
      <OutlinedInput className='comment-area' placeholder="댓글을 입력하세요." />

      <IconButton type='submit' className='submit-btn'>
        <ArrowCircleUpIcon color='primary' fontSize='large' />
      </IconButton>
    </CardContent>
  );
}

export default CommentInput;