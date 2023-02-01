import React from 'react';
import { Routes, Route } from 'react-router-dom';
import PhotoBook from "./PhotoBook";
import CreatePhoto from "./CreatePhoto";
import PhotoDetail from "./PhotoDetail";

const PhotoBookPage = (props) => {
  return (
    <Routes>
      <Route path='/' element={<PhotoBook />} />
      <Route path='/post' element={<CreatePhoto />} />
      <Route path='/:photoId' element={<PhotoDetail />} />
    </Routes>
  );
};

export default PhotoBookPage;