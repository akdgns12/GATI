import React from "react";
import ReactDOM from "react-dom/client";
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import { Routes, Route } from "react-router";

import { Container } from "@mui/material";

import LoginFrom from "../../components/Login/LoginForm";
import SignUpForm from "../../components/Login/SignUpForm";
import FindID from "../../components/Login/FindID";
import FindPW from "../../components/Login/FindPW";
import FindResult from "../../components/Login/FindResult";
import imgPath from "../../static/home_with_door.png";

import Lottie from "react-lottie-player";
import Family from "../../static/family.json";

const contStyle = css`
  width: 100%;
  height: 100%;
  background-size: auto 100vh;
  background-position: center;
  text-align: center;
`;

const Login = () => {
  function doSth() {}
  return (
    <div css={contStyle}>
      {/* <div className="bg-text">A_805</div> */}
      {/* <Lottie
        loop
        animationData={Family}
        play
        style={{ margin: "0 auto", width: "60%", height: "50%" }}
      /> */}
      <Container className="router-cont">
        <Routes>
          <Route path="/" element={<LoginFrom />} />
          <Route path="/signup" element={<SignUpForm />} />
          <Route path="/findID" element={<FindID />} />
          <Route path="/findPW" element={<FindPW />} />
          <Route path="/findRes" element={<FindResult />} />
        </Routes>
      </Container>
    </div>
  );
};

export default Login;
