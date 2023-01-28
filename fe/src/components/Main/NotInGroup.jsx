import React from "react";
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import WarningAmberIcon from "@mui/icons-material/WarningAmber";
import { Typography } from "@mui/material";

const contStyle = css`
  text-align: center;
  width: 80%;
  margin-left: auto;
  margin-right: auto;
  .head {
    color: gray;
  }
`;

export default function NotInGroup() {
  return (
    <div css={contStyle}>
      <div className="head">
        <WarningAmberIcon />
        <Typography>아직 속한 그룹이 없습니다.</Typography>
      </div>
      <Typography>나만의 가족 그룹을 만들고 가족들과의 추억을 저장하세요!</Typography>
    </div>
  );
}
