import React, { useEffect } from "react";
import { Routes, Route, useLocation, useNavigate } from "react-router-dom";

import NavBar from "./components/NavBar";
import AppBar from "./components/AppBar";
import Error from "./components/Error";
import Home from "./pages/Main/MainPage";

import Calendar2 from "./pages/Calendar/Calendar2";
import PhotoBookPage from "./pages/PhotoBook/PhotoBookPage";
import GoTogether from "./pages/GoTogether/GoTogetherPage";
import Login from "./pages/LogIn/LoginPage";
import AdminPage from "./pages/Admins/AdminPage";
import PictureTogetherPage from "./pages/PicsTogether/PicsTogetherPage";
import AdminRouter from "./pages/Admins/AdminRouter";

import { Container } from "@mui/material";

import { useSelector, useDispatch } from "react-redux";
import { updateToken, clearUserInfo } from "./store/User/user";

import httpClient from "./utils/axios";
import refreshClient from "./utils/refreshClient";
import { doLogOut } from "./utils/logOutUtil";

// css
import "./index.css";
import { useState } from "react";

const App = () => {
  const location = useLocation();
  const { loginUser, logIn } = useSelector((state) => state.user);
  const [prepared, setPrepared] = useState(false);
  const dispatch = useDispatch();
  const navigate = useNavigate();

  useEffect(() => {
    console.log("REFRESH TOKEN MODIFIED");
    console.log("HAS TOKEN, USE INTERCEPTOR");

    httpClient.interceptors.response.use(
      function (response) {
        return response;
      },
      function (error) {
        // console.log(error);
        // console.log(error.response.status);
        const originalRequest = error.config;
        if (error.response != null && error.response.status === 401) {
          console.log("UNAUTHORIZED !!!");
          const userStore = JSON.parse(
            window.localStorage.getItem("persist:root")
          );
          const user = JSON.parse(userStore.user);
          const refreshToken = user.loginUser.refreshToken;
          // console.log(refreshToken);
          const response = refreshClient.get("/refresh", {
            headers: {
              token: refreshToken,
            },
          });
          response
            .then((res) => {
              console.log("REFRESH !!!");
              if (res != null && res.data.msg === "success") {
                dispatch(updateToken(res.data["new accessToken"]));
                originalRequest.headers.Authorization = `Bearer ${res.data["new accessToken"]}`;
                // console.log(originalRequest);
                const finalResponse = httpClient(originalRequest);
                // finalResponse.then((res) => { console.log(res) }).catch((error) => console.log(error));
                console.log(finalResponse);
                return finalResponse;
              } else {
                console.log("FAILED TO REFRESH TOKEN");
                return error;
              }
            })
            .catch((error) => {
              console.log("REFRESH TOKEN EXPIRED, DO LOGOUT");
              console.log(error);
              dispatch(clearUserInfo());
              window.alert("세션이 만료되었습니다. 다시 로그인 해주세요");
              doLogOut();
              navigate("/login");
            });
        }
      }
    );
    setPrepared(true);
  }, []);

  // useEffect(() => {
  //   console.log("refreshToken modified");
  //   // if(loginUser.refreshToken == "") {
  //   //   httpClient.interceptors.response.eject(tokenInterceptor);
  //   // }
  // }, [loginUser.refreshToken]);

  useEffect(() => {
    console.log("ACCESS TOKEN MODIFIED");
    httpClient.defaults.headers.common[
      "Authorization"
    ] = `Bearer ${loginUser.accessToken}`;
  }, [loginUser.accessToken]);

  function excludeHeader() {
    if (location.pathname.startsWith("/login")) return true;
    else return false;
  }

  function doRedirect() {
    const isLoginPage = location.pathname.startsWith("/login") ? true : false;
    if (isLoginPage) return false;
    if (logIn) {
      return false;
    }
    return true;
  }

  if (prepared) {
    return (
      <Container fixed maxWidth="lg" className="App">
        {/* {doRedirect() && <Navigate to="/login" replace={true} />} */}
        {!excludeHeader() && <AppBar />}
        <Routes>
          <Route path="/*" element={<Home />} />
          <Route path="/calendar" element={<Calendar2 />} />
          <Route path="/photobook/*" element={<PhotoBookPage />} />
          <Route path="/gotg" element={<GoTogether />} />
          <Route path="/pictg/*" element={<PictureTogetherPage />} />
          <Route path="/login/*" element={<Login />} />
          <Route path="/admin/*" element={<AdminRouter />} />
          <Route path="/error" element={<Error />} />
        </Routes>
        {!excludeHeader() && <NavBar />}
      </Container>
    );
  } else {
    <Error />;
  }
};

export default App;
