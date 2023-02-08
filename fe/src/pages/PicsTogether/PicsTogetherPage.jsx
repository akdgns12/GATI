import React from 'react';
import { Routes, Route } from 'react-router-dom';
import PictureTogether from '../../components/PicsTogether/PictureTogether';
import MissionCompleted from '../../components/PicsTogether/OnMission/MissionCompleted';

const PicsTogetherPage = (props) => {
  return (
    <Routes>
      <Route path='/' element={<PictureTogether />} />
      <Route path='/missionCompleted' element={<MissionCompleted />} />
    </Routes>
  );
};

export default PicsTogetherPage;