import React from "react";
import { useSelector } from 'react-redux';
import {Typography, Paper, Button, Box} from '@mui/material';
import SetMemberModal from './SetMemberModal';

export default function InProgressDefault(props) {
  // 이번주 Mission 데이터 가져오기
  const thisWeekMission = useSelector(state => {
    return state.picsTg.thisWeekMission
  })

  // 참여 버튼 클릭 -> SetMemberModal (인원 선택) 모달 띄우기
  const [modal, setModal] = React.useState(false)
  const openModal = () => {
      setModal(true)
  }

  // 이번주 해당하는 미션 가져오는 함수
  // const getThisMon = (d) => {
  //   let thisMon = null
  //   const today = d
  //   const day = today.getDay() || 7
  //   if( day !== 1 ) {
  //     thisMon = today.setHours(-24 * (day - 1))
  //   } else {
  //     thisMon = today
  //   }
  //   return [thisMon.getFullYear(),thisMon.getMonth(), thisMon.getDate()].join('-')
  // }

  return (
    <Paper sx={{
      padding:3,
    }}>
      <Typography style={{ fontWeight:'bold', marginBottom:'20px'}}>{thisWeekMission.title}</Typography>
      <Typography style={{ marginBottom:'30px'}}>
        {thisWeekMission.description}
      </Typography>
      <Box
        style={{
          display:'flex',
          justifyContent:'center',
          marginBottom:'30px'
        }}>
        <img
          src={thisWeekMission.exampleImg}
          alt='exampleImg'
          width='200px'/>
      </Box>
      <Box
        style={{
          display:'flex',
          justifyContent:'center',}}>
        <Button onClick={openModal} size="medium" variant="contained" disableElevation>미션 참여하기</Button>
      </Box>
      { modal === true ?
        <SetMemberModal
          open={modal}
          onClose={()=>{ setModal(false)}}
        />
        : null
      }
    </Paper>
  )
}
