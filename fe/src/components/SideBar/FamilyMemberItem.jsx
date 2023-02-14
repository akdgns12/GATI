import { React } from "react";
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import PersonIcon from "@mui/icons-material/Person";
import { Box, Typography } from "@mui/material";

const contStyle = css`
  width: 100%;
  display: inline-flex;
  border-style: solid;
  border-width: 0 0 1px 0;
  border-color: rgba(192, 192, 192, 0.75);
  .text-box {
    width: 100%;
    display: inline-flex;
    justify-content: space-between;
    .name-text {
      width: 30%;
    }
    .number-text {
      width: 35%;
      white-space: nowrap;
      overflow: hidden;
      text-overflow: ellipsis;
    }
  }
`;

const FamilyMemberItem = (props) => {
  return (
    <Box css={contStyle}>
      <PersonIcon className="person-icon" />
      <Box className="text-box">
        <Typography className="name-text">{props.member.nickName}</Typography>
        <Typography variant="body2" className="number-text">
          {props.member.birth}
        </Typography>
        <Typography variant="body2" className="number-text">
          {props.member.phone}
        </Typography>
      </Box>
    </Box>
  );
};

export default FamilyMemberItem;
