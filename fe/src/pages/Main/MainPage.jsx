import { React, useState } from 'react';
import { Routes, Route } from 'react-router-dom';
import MainFeed from './MainFeed';
import CreatePost from './CreatePost';

const Home = (props) => {
  return (
    <Routes>
      <Route path='/' element={<MainFeed />} />
      <Route path='/post' element={<CreatePost />} />
    </Routes>
  );
};

export default Home;