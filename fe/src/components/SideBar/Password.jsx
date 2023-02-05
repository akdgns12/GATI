import React from 'react'
import { Grid, TextField, Button } from '@mui/material'

export default function Password() {
  return (
    <Grid >
        <Grid>
          <Grid  p={1} display='flex' justifyContent='center'>
            <TextField id="outlined-basic" label="현재 비밀번호" variant="outlined" />
          </Grid>
          <Grid  p={1} display='flex' justifyContent='center'>
            <TextField id="outlined-basic" label="새 비밀번호" variant="outlined" />
          </Grid>
          <Grid  p={1} display='flex' justifyContent='center'>
            <TextField helperText="*8~20자의 영문,숫자,특수문자를 사용하세요" id="outlined-basic" label="새 비밀번호 확인" variant="outlined" />
          </Grid>
        </Grid>
        <Grid  p={1} display='flex' justifyContent='center'>
          <Button variant="outlined">저장</Button>
        </Grid>
      </Grid>
  )
}
