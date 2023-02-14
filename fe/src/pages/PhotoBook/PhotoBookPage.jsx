import React from 'react';
import { Routes, Route } from 'react-router-dom';
import PhotoBook from "./PhotoBook";
import CreatePhoto from "./CreatePhoto";
import PhotoDetail from './PhotoDetail';

const PhotoBookPage = (props) => {
  return (
    <Routes>
      <Route path='/' element={<PhotoBook />} />
      <Route path='/create' element={<CreatePhoto/>} />
      <Route path='/detail/:photoId' element={<PhotoDetail/>} />
    </Routes>
  );
};

export default PhotoBookPage;