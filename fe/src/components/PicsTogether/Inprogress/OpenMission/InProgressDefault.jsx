import React from "react";
import { useSelector } from 'react-redux';
import {Typography, Paper, Button, Box} from '@mui/material';
import SetMemberModal from './SetMemberModal';


export default function InProgressDefault() {
  // 이번주 Mission 데이터 가져오기
  const getMission = useSelector(state => {
    return state.picsTg.getMission
  })

  // 참여 버튼 클릭 -> SetMemberModal (인원 선택) 모달 띄우기
  const [modal, setModal] = React.useState(false)
  const openModal = () => {
      setModal(true)
  }

  return (
    <Paper
      elevation={2}
      sx={{
        padding:3,
        backgroundColor:'#FFF5E4',
        borderRadius: '20px',
      }}
    >
      <Typography align="center" fontFamily='ONE-Mobile-POP' style={{ fontWeight:'bold', marginBottom:'20px'}}>{getMission.title}</Typography>
      <Typography align="center" style={{ marginBottom:'30px'}}>
        {getMission.content}
      </Typography>
      <Box
        style={{
          display:'flex',
          justifyContent:'center',
          marginBottom:'30px'
        }}>
        <img
          src={getMission.img}
          alt='exampleImg'
          width='200px'/>
      </Box>
      <Box
        style={{
          display:'flex',
          justifyContent:'center',}}>
        <Button
          onClick={openModal} 
          size="medium" 
          variant="contained" 
          style={{ backgroundColor: '#FF9494', color: 'white' }}
          disableElevation
        >
          미션 참여하기
        </Button>
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
