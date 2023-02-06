import React from 'react'
import { Grid, Button, Typography, TextField } from '@mui/material'
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
      <Grid p={2} display='flex' justifyContent='center'>
        <Button onClick={passwordOpen} variant="outlined">내 정보 수정</Button>
        <Button onClick={passwordOpen} variant="outlined">비밀번호 변경</Button>
      </Grid>
      {show && <Password/>}
      {show1 && <MyInfochange/>}
    </Grid>
  )
}
