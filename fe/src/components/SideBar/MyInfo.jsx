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
      {/* <ToggleButtonGroup
        color='primary'
        value={mode}
        exclusive
        aria-label="Platform"
        style={{
          flex:'auto',
          margin:'0 0 30px 0',
          width:'80vw'
        }}
        >
        <ToggleButton
          onClick={passwordOpen}
          value="inprogress"
          style={{
            flex:1, 
            height:'40px', 
            color: mode === 'inprogress' ? 'white' :'#FF9494',
            backgroundColor: mode === 'inprogress' ? '#FF9494' : 'white',
            border:'1px solid',
            fontSize: mode === 'inprogress' ? '16px' :'14px',
          }}>
            내 정보 수정
        </ToggleButton>
        <ToggleButton
          onClick={passwordOpen}
          value="completed"
          style={{ 
            flex:1, 
            height:'40px', 
            color: mode === 'completed' ? 'white' :'#FF9494',
            backgroundColor: mode === 'completed' ? '#FF9494' : 'white',
            fontSize: mode === 'completed' ? '16px' :'14px',
            border:'1px solid'}}>
              비밀번호 변경
        </ToggleButton>
      </ToggleButtonGroup> */}
      <Stack marginY='20px' direction="row" spacing={1} justifyContent='center'>
        <Button sxargin="10px" onClick={passwordOpen} variant="outlined">내 정보 수정</Button>
        <Button onClick={passwordOpen} variant="outlined">비밀번호 변경</Button>
      </Stack>
      <Box marginBottom={1}>
        {show && <Password/>}
        {show1 && <MyInfochange/>}
      </Box>
    </Grid>
  )
}
