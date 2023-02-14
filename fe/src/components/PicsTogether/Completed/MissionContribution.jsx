import { Box } from '@mui/material';
import * as React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router';

export default function MissionContribution() {
  const navigate = useNavigate()
  const dispatch = useDispatch()
  const groupId = 1

  // MissionList 데이터 가져오기
  
  let getMissionList = useSelector(state=>{return state.picsTg.getMissionList})

  return (
    <Box
      sx={{
        display:'flex',
        flexDirection:'column',
        flexWrap:'wrap',
      }}>
        <Box></Box>
    </Box>
  );
}

