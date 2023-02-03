
import React from "react";
import { Routes, Route, useLocation } from "react-router-dom";

import NavBar from "./components/NavBar";
import AppBar from "./components/AppBar";

import Home from "./pages/Main/MainPage"
import Calendar from "./pages/Calendar/CalendarPage";
import PhotoBookPage from "./pages/PhotoBook/PhotoBookPage";
import GoTogether from "./pages/GoTogether/GoTogetherPage";
import PictureTogether from "./pages/PictureTogether/PictureTogetherPage";
import Login from "./pages/LogIn/LoginPage";
import Box from '@mui/material/Box';

import { useSelector } from 'react-redux';

const App = () => {
  const location = useLocation();
  const { loginUser, logIn } = useSelector((state) => state.user);

  function excludeHeader() {
    if (location.pathname.startsWith("/login")) return true;
    else return false;
  };

  if (logIn === false) {
    return <Login />
  }

  return (
    <div className="App" >
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
}

export default App;