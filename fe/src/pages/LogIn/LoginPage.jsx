import React from "react";
import ReactDOM from "react-dom/client";
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import { Routes, Route } from "react-router";

import LoginFrom from "../../components/Login/LoginForm";

const contStyle = css`
  width: 100%;
  .background-img {
    width: 100%;
    height: 100%;
    background-color: #c8c8c8;
  }
`;

const Login = () => {
  function doSth() { };
  return (
    <div css={contStyle}>
      <div className="background-img">Awesome Background</div>
      <Routes>
        <Route path="/" element={<LoginFrom />} />
      </Routes>
    </div>
  );
};

export default Login;