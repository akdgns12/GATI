import "./App.css";
import React from "react";
import { Routes, Route } from "react-router-dom";

import NavBar from "./components/NavBar";
import AppBar from "./components/AppBar";

import Home from "./pages/Main/MainPage"
import Calendar from "./pages/Calendar/CalendarPage";
import PhotoBook from "./pages/PhotoBook/PhotoBookPage";
import GoTogether from "./pages/GoTogether/GoTogetherPage";
import PictureTogether from "./pages/PictureTogether/PictureTogetherPage";

class App extends React.Component {
  render() {
    return (
      <div className="App">
        <AppBar />
        <Routes>
          <Route path="/*" element={<Home />} />
          <Route path="/calendar" element={<Calendar />} />
          <Route path="/photobook" element={<PhotoBook />} />
          <Route path="/gotg" element={<GoTogether />} />
          <Route path="/pictg" element={<PictureTogether />} />
        </Routes>
        <NavBar />
      </div>
    );
  }
}

export default App;