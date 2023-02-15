import React from "react";
import { Routes, Route } from "react-router-dom";
import PhotoBook from "./PhotoBook";
import CreatePost from "../Main/CreatePost";
import PhotoDetail from "./PhotoDetail";

const PhotoBookPage = (props) => {
  return (
    <Routes>
      <Route path="/" element={<PhotoBook />} />
      <Route path="/create" element={<CreatePost api="album" />} />
      <Route path="/detail/:photoId" element={<PhotoDetail />} />
    </Routes>
  );
};

export default PhotoBookPage;
