import { React, useState } from "react";
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import { Box, Button, Container } from "@mui/material";

import { useNavigate, useLocation } from "react-router";

const contStyle = css`
  max-width: 300px;
  width: 100%;
  margin-top: 33vh;
  border-radius: 10px;
  background-color: rgba(255, 255, 255, 0.75);
  .res-box {
    width: 100%;
    height: 200px;
  }
  .btn-group {
    margin-top: 10px;
    .prev-btn {
      margin: 5px;
    }
  }
`;

const FindResult = () => {
  const navigate = useNavigate();
  const location = useLocation();

  function toLogin() {
    navigate("/login");
  }

  return (
    <Container css={contStyle}>
      <Box className="res-box">
        <h1>Check your email</h1>
        <Box>
          <b>{location.state.email}<br /></b>
          으로 {location.state.mode} 가 전송되었습니다.<br />
          e-mail을 확인해 주세요.
        </Box>
      </Box>
      <Box className="btn-group">
        <Button
          variant="outlined"
          color="primary"
          className="prev-btn"
          onClick={toLogin}
        >
          홈으로
        </Button>
      </Box>
    </Container>
  );
};

export default FindResult;