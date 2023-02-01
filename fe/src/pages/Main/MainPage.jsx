import { React, useState } from 'react';
import { Routes, Route } from 'react-router-dom';
import MainFeed from './MainFeed';
import CreatePost from './CreatePost';
import PostDetail from './PostDetail';

const Home = (props) => {
  console.log('Home')
  return (
    <Routes>
      <Route path='/' element={<MainFeed />} />
      <Route path='/post' element={<CreatePost />} />
      <Route path='/detail/:postId' element={<PostDetail />} />
    </Routes>
  );
};

export default Home;