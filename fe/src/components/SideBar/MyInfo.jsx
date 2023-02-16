import React from 'react'
import { Grid, Button, Typography, TextField, Stack, Divider, Box } from '@mui/material'
import { useState } from 'react'
import Password from './Password'
import MyInfochange from './MyInfochange'
export default function MyInfo() {
  const [show, setShow] = useState(false)
  const [show1, setShow1] = useState(true)

  const passwordOpen = () => {
    setShow(current => !current)
    setShow1(current => !current)
  }
  
  return (
    
    <Grid>
      <Stack marginY='20px' direction="row" spacing={1} justifyContent='center'>
        <Button sxargin="10px" onClick={passwordOpen} variant="outlined">내 정보 수정</Button>
        <Button onClick={passwordOpen} variant="outlined">비밀번호 변경</Button>
      </Stack>
      <Box marginBottom={1}>
        {show && <Password/>}
        {show1 && <MyInfochange/>}
      </Box>
      <Divider />
      <Box sx={{display:'flex', justifyContent:"center", p:3}} >
        <Button variant="outlined" size="large">회원 탈퇴</Button>
      </Box>
    </Grid>
  )
}
