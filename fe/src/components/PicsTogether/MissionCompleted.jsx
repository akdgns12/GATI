import React from "react";
import { Typography } from "@mui/material"
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import imgPath from "../../static/MissionCompletedGIF.gif";

const contStyle = css`
  width: 100vw;
  height: 100vh;
  background-image: url(${imgPath});
  background-size:cover;
  background-position: center;
  text-align: center;
  .bg-test {
    width: 100%;
  }
`;

export default function MissionCompleted() {
  console.log('hi')
  return(
    <div css={contStyle}>
      <Typography variant="h3" color="white">이번주 미션 성공</Typography>
    </div>

  )
}