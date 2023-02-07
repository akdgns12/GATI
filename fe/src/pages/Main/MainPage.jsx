import { React, useState } from 'react';
import { Routes, Route } from 'react-router-dom';
import MainFeed from './MainFeed';
import CreatePost from './CreatePost';
import PostDetail from './PostDetail';
import { Container } from '@mui/system';

const Home = (props) => {
  return (
    <Routes>
      <Route path='/' element={<MainFeed />} />
      <Route path='/post' element={<CreatePost />} />
      <Route path='/detail/:articleId' element={<PostDetail />} />
    </Routes>
  );
};

export default Home;