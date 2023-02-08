import * as React from 'react';
import {ToggleButtonGroup, ToggleButton, Box } from '@mui/material';
import InProgressDefault from './Inprogress/InProgressDefault';
import OnMission from './OnMission/OnMission';
import Completed from './Completed/Completed';
import { useDispatch, useSelector } from 'react-redux';
import { changeMode } from '../../store/PicsTogether/picsTg';

export default function PictureTogether() {
  const dispatch = useDispatch()
  // 진행 중, 완료 모드 전환
  const mode = useSelector(state => {
    console.log('state',state)
    return state.picsTg.mode
  })

  // 미션 전(InprogressDefault), 미션 중(OnMission) 모드 전환
  const missionMode = useSelector(state => {
    return state.picsTg.missionMode
  })

  // mode에 따라 달라질 content
  let content = null
  if (mode === 'inprogress' && missionMode === false) {
    content = <InProgressDefault />
  } else if (mode === 'inprogress' && missionMode === true) {
    content = <OnMission />
  } else {
    content = <Completed />
  }

  return (
    <Box
      style={{
        display:'flex',
        flexDirection:'column',
        alignItems:'center'
      }}>
      <ToggleButtonGroup
        color='primary'
        value={mode}
        exclusive
        aria-label="Platform"
        style={{
          flex:'auto',
          margin:'30px',
          width:'80vw'
        }}
        >
        <ToggleButton
          onClick={()=>{dispatch(changeMode('inprogress'))}}
          value="inprogress"
          style={{ flex:1, height:'40px', backgroundColor:'white', border:'1px solid'}}>진행 중
        </ToggleButton>
        <ToggleButton
          onClick={()=>{dispatch(changeMode('completed'))}}
          value="completed"
          style={{ flex:1, height:'40px', backgroundColor:'white', border:'1px solid'}}>완료
        </ToggleButton>
      </ToggleButtonGroup>
      <Box
        style={{width:'80vw'}}>
        {content}
      </Box>
    </Box>
  );
}
