import { React } from 'react';
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";

import { Box } from '@mui/material';
import SubdirectoryArrowRightIcon from '@mui/icons-material/SubdirectoryArrowRight';

const contStyle = css`
  display: flex;
  margin-left: 5%;
  margin-top: 5px;
`;

const CommentView = (props) => {
  return (
    <Box css={contStyle}>
      <SubdirectoryArrowRightIcon />
      <b>{props.comment.userId}</b>
      : {props.comment.comment}
    </Box>
  );
}

export default CommentView;