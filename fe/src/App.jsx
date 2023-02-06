import React from "react";
import { Routes, Route, useLocation, Navigate } from "react-router-dom";

import NavBar from "./components/NavBar";
import AppBar from "./components/AppBar";

import Home from "./pages/Main/MainPage";
import Calendar from "./pages/Calendar/CalendarPage";
import PhotoBookPage from "./pages/PhotoBook/PhotoBookPage";
import GoTogether from "./pages/GoTogether/GoTogetherPage";
import PictureTogether from "./pages/PictureTogether/PictureTogetherPage";
import Login from "./pages/LogIn/LoginPage";
import Box from "@mui/material/Box";

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

  return (
    <div className="App">
      {doRedirect() && <Navigate to="/login" replace={true} />}
      {!excludeHeader() && <AppBar />}
      <Routes>
        <Route path="/*" element={<Home />} />
        <Route path="/calendar" element={<Calendar />} />
        <Route path="/photobook/*" element={<PhotoBookPage />} />
        <Route path="/gotg" element={<GoTogether />} />
        <Route path="/pictg" element={<PictureTogether />} />
        <Route path="/login/*" element={<Login />} />
      </Routes>
      {!excludeHeader() && <NavBar />}
    </div>
  );
};

export default App;
