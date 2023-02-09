import React from "react";
import { Routes, Route, useLocation, Navigate } from "react-router-dom";

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

import Box from "@mui/material/Box";
import { useSelector } from "react-redux";

import { Container } from "@mui/material";
const App = () => {
  const location = useLocation();
  const { loginUser, logIn } = useSelector((state) => state.user);

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
      {doRedirect() && <Navigate to="/login" replace={true} />}
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
