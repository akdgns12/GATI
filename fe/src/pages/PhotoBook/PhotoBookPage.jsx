import React from 'react';
import { Routes, Route } from 'react-router-dom';
import PhotoBook from "../../components/PhotoBook/PhotoBook";
import CreatePhoto from "../../components/PhotoBook/CreatePhoto";
import PhotoDetail from "../../components/PhotoBook/PhotoDetail";

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