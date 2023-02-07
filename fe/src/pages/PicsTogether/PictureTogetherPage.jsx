import * as React from 'react';
import {ToggleButtonGroup, ToggleButton } from '@mui/material';
import InProgressDefault from '../../components/PicsTogether/InProgressDefault';
import OnMission from '../../components/PicsTogether/OnMission';
import Completed from '../../components/PicsTogether/Completed';

export default function PictureTogether() {
  // 진행 중, 완료 모드 전환
  const [mode, setMode] = React.useState('inprogress');
  const changeMode = () => {
    if (mode === 'inprogress') {
      setMode('completed');
    } else {
      setMode('inprogress')
    }}

  // 이번주 미션 시작 여부
  const [mission, setMission] = React.useState(false)
  const changeToMissionMode = () => {
    setMission(true)
  }

  // 선택한 인원 수(사진 수)
  const [picNum, setPicNum] = React.useState('')

  // mode에 따라 달라질 content
  let content = null
  if (mode === 'inprogress' && mission === false) {
    content = <InProgressDefault deliverPicNum={(_pic)=>setPicNum(_pic)} changeToMissionMode={changeToMissionMode} />
  } else if (mode === 'inprogress' && mission === true) {
    content = <OnMission picNum={picNum} />
  } else {
    content = <Completed />
  }

  return (
    <div
      style={{
        display:'flex',
        flexDirection:'column',
        alignItems:'center'
      }}>
      <div>
        <ToggleButtonGroup
          color='primary'
          value={mode}
          exclusive
          onChange={changeMode}
          aria-label="Platform"
          style={{
            flex:'auto',
            margin:'30px',
            width:'80vw'
          }}
          >
          <ToggleButton value="inprogress" style={{ flex:1, height:'40px', backgroundColor:'white', border:'1px solid'}}>진행 중</ToggleButton>
          <ToggleButton value="completed" style={{ flex:1, height:'40px', backgroundColor:'white', border:'1px solid'}}>완료</ToggleButton>
        </ToggleButtonGroup>
      </div>
      <div
        style={{
          display:'flex',
          flexDirection:'column',
          alignItems:'center',
          height:'70vh'
        }}>
        {content}
      </div>
    </div>
  );
}
