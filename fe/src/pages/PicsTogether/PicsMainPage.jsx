import * as React from 'react';
import {ToggleButtonGroup, ToggleButton, Box } from '@mui/material';
import InProgressDefault from '../../components/PicsTogether/Inprogress/OpenMission/InProgressDefault';
import OnMission from '../../components/PicsTogether/Inprogress/OnMission/OnMission';
import Completed from '../../components/PicsTogether/Completed/Completed';
import { useDispatch, useSelector } from 'react-redux';
import { asyncGetMission, changeMode } from '../../store/PicsTogether/picsTg';
import MissionCompleted from '../../components/PicsTogether/Inprogress/MissionComplete/MissionCompleted';


export default function PicsMainPage() {
  const dispatch = useDispatch();

  // 유저 정보 받아오기
  const userId = useSelector(state=>{return state.user.loginUser.userId})
  const mainGroup = useSelector(state=>{return state.user.mainGroup})
  console.log('userId', userId, 'mainGroup', mainGroup.id)

  React.useEffect(()=>{
    dispatch(asyncGetMission(mainGroup.id))
  },[])

  // 진행 중, 완료 모드 토글 전환
  const mode = useSelector(state => {
    // console.log(state) -> state는 redux 저장소에 모인 데이터들
    return state.picsTg.mode
  })

  // 진행 중 - 미션 전(InprogressDefault), 미션 중(OnMission) 모드 전환
  const missionStatus = useSelector(state => {
    return state.picsTg.getMission
  }).completed

  // test
  console.log('메인 mode',mode)
  console.log('메인 missionStatus',missionStatus)
  
  // mode에 따라 달라질 content
  let content = null
  if (mode === 'inprogress' && missionStatus === 0) {
    content = <InProgressDefault />
  } else if (mode === 'inprogress' && missionStatus === 1) {
    content = <OnMission />
  } else if (mode === 'inprogress' && missionStatus === 2) {
    content = <MissionCompleted />
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
