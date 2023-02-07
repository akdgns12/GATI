import React from "react";
import { Routes, Route, useLocation, Navigate } from "react-router-dom";

import NavBar from "./components/NavBar";
import AppBar from "./components/AppBar";

import Home from "./pages/Main/MainPage";
import Calendar from "./pages/Calendar/CalendarPage";
import PhotoBookPage from "./pages/PhotoBook/PhotoBookPage";
import GoTogether from "./pages/GoTogether/GoTogetherPage";
import Login from "./pages/LogIn/LoginPage";
import Box from "@mui/material/Box";
import PictureTogetherPage from "./pages/PicsTogether/PicsTogetherPage"
import { useSelector } from "react-redux";

const App = () => {
  const location = useLocation();
  const { loginUser, logIn } = useSelector((state) => state.user);

  function excludeHeader() {
    if (location.pathname.startsWith("/login")) return true;
    else return false;
  }

  function doRedirect() {
    const isLoginPage = location.pathname.startsWith("/login") ? true : false;
    if (!logIn && !isLoginPage) return true;
    else return false;
  }

  // height:100vh 설정 값에 스크롤이 안 생기도록 조정
  function setScreenSize() {
    let vh = window.innerHeight * 0.01;
    document.documentElement.style.setProperty("--vh", `${vh}px`); //"--vh"라는 속성으로 정의해준다.
  }
  window.addEventListener('resize', () => setScreenSize());

  return (
    <Box className="App">
      {/* {doRedirect() && <Navigate to="/login" replace={true} />} */}
      {!excludeHeader() && <AppBar />}
      <Routes>
        <Route path="/*" element={<Home />} />
        <Route path="/calendar" element={<Calendar />} />
        <Route path="/photobook/*" element={<PhotoBookPage />} />
        <Route path="/gotg" element={<GoTogether />} />
        <Route path="/pictg/*" element={<PictureTogetherPage />} />
        <Route path="/login/*" element={<Login />} />
      </Routes>
      {!excludeHeader() && <NavBar />}
    </Box>
  );
};

export default App;
