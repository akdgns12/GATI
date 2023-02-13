import React from 'react';
import { Routes, Route } from 'react-router-dom';
import PicsMainPage from './PicsMainPage';
import CongratsPage from './CongratsPage';
import MissionDetailPage from './MissionDetailPage';

const PicsTogetherPage = (props) => {
  return (
    <Routes>
      <Route path='/' element={<PicsMainPage />} />
      <Route path='/congrats' element={<CongratsPage />} />
      <Route path='/detail/:id' element={<MissionDetailPage />} />
    </Routes>
  );
};

export default PicsTogetherPage;