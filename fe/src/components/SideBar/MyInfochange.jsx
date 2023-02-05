import React from 'react'
import { Grid, TextField, Button } from '@mui/material'

export default function MyInfochange() {
  return (
    <Grid >
        <Grid p={1} display='flex' justifyContent='center'>
          <TextField id="outlined-basic" label="닉네임" variant="outlined" />
        </Grid>
        <Grid p={1} display='flex' justifyContent='center'>
          <TextField id="outlined-basic" label="이메일" variant="outlined" />
        </Grid>
        <Grid p={1} display='flex' justifyContent='center'>
          <TextField id="outlined-basic" label="휴대폰 번호" variant="outlined" />
        </Grid>
        <Grid p={1} display='flex' justifyContent='center'>
          <TextField id="outlined-basic" label="생년월일 yyyy-mm-dd" variant="outlined" />
        </Grid>
        <Grid  p={1} display='flex' justifyContent='center'>
          <Button variant="outlined">수정</Button>
        </Grid>
      </Grid>
  )
}
