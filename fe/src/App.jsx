import React, { useEffect } from "react";
import { Routes, Route, useLocation, Navigate, useNavigate } from "react-router-dom";

import NavBar from "./components/NavBar";
import AppBar from "./components/AppBar";

import Home from "./pages/Main/MainPage";
import Calendar from "./pages/Calendar/CalendarPage";
import PhotoBookPage from "./pages/PhotoBook/PhotoBookPage";
import GoTogether from "./pages/GoTogether/GoTogetherPage";
import Login from "./pages/LogIn/LoginPage";
import AdminPage from "./pages/Admins/AdminPage";
import PictureTogetherPage from "./pages/PicsTogether/PicsTogetherPage"
import AdminRouter from "./pages/Admins/AdminRouter";

import { Container } from "@mui/material";

import { useSelector, useDispatch } from "react-redux";
import { updateToken } from "./store/User/user";

import httpClient from "./utils/axios";
import refreshClient from "./utils/refreshClient";
import { doLogOut } from "./utils/logOutUtil";

const App = () => {
  const location = useLocation();
  const { loginUser, logIn } = useSelector((state) => state.user);
  const dispatch = useDispatch();
  const navigate = useNavigate();

  useEffect(() => {
    httpClient.interceptors.response.use(
      function (response) {
        // console.log(response);
        return response;
      },
      function (error) {
        // console.log(error);
        // console.log(error.response.status);
        const originalRequest = error.config;
        if (error.response != null && error.response.status === 401) {
          console.log("UNAUTHORIZED !!!");
          const refreshToken = loginUser.refreshToken;
          // console.log(refreshToken);
          const response = refreshClient.get("/refresh",
            {
              headers: {
                token: refreshToken,
              }
            });
          response.then((res) => {
            console.log("REFRESH !!!")
            // console.log(res);
            if (res != null && res.data.msg === "success") {
              dispatch(updateToken(res.data['new accessToken']));
              console.log("NEW Access token : " + res.data['new accessToken']);
              originalRequest.headers.Authorization = `Bearer ${res.data['new accessToken']}`;
              // console.log(originalRequest);
              const finalResponse = httpClient(originalRequest);
              // finalResponse.then((res) => { console.log(res) }).catch((error) => console.log(error));
              console.log(finalResponse);
              return finalResponse;
            }
            else {
              console.log("FAILED TO REFRESH TOKEN");
              return error;
            }
          }).catch((error) => {
            console.log(error);
            doLogOut();
            navigate("/login");
          });
        }
        return error;
      }
    );
  }, []);

  useEffect(() => {
    console.log("Token modified");
    httpClient.defaults.headers.common["Authorization"] = `Bearer ${loginUser.accessToken}`;
  }, [loginUser]);

  function excludeHeader() {
    if (location.pathname.startsWith("/login")) return true;
    else return false;
  }

  function doRedirect() {
    const isLoginPage = location.pathname.startsWith("/login") ? true : false;
    if (isLoginPage) return false;
    if (logIn) {
      // console.log("add to header : " + loginUser.accessToken);
      httpClient.defaults.headers.common["Authorization"] = `Bearer ${loginUser.accessToken}`;
      return false;
    }
    return true;
  }

  return (
    <Container fixed maxWidth="lg" className="App">
      {/* {doRedirect() && <Navigate to="/login" replace={true} />} */}
      {!excludeHeader() && <AppBar />}
      <Routes>
        <Route path="/*" element={<Home />} />
        <Route path="/calendar" element={<Calendar />} />
        <Route path="/photobook/*" element={<PhotoBookPage />} />
        <Route path="/gotg" element={<GoTogether />} />
        <Route path="/pictg/*" element={<PictureTogetherPage />} />
        <Route path="/login/*" element={<Login />} />
        <Route path="/admin/*" element={<AdminRouter />} />
      </Routes>
      {!excludeHeader() && <NavBar />}
    </Container>
  );
};

export default App;
