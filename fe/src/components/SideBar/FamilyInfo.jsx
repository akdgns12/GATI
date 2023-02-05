import React from 'react'
import { Grid, Typography, TextField, Button, Divider } from '@mui/material'
export default function FamilyInfo() {
  return (
    <Grid>
        <Grid item m={1} xs={12} display='flex' justifyContent='start'>
          <Typography>가족 프로필 설정</Typography>
        </Grid>
        <Grid>
          <TextField size='small' fullWidth label="family name" id="family name" />
        </Grid>
        <Grid m={1} item xs={12} display='flex' justifyContent='start'>
          <Typography>가족 구성원 초대</Typography>
        </Grid>
        <Grid>
          <TextField size='small' fullWidth label="member-id" id="member-id" />
        </Grid>
        <br />
        <Grid>
          <Button fullWidth variant='outlined'>send</Button>
        </Grid>
        <Divider/>
        <br />
        <Grid >
          <Button fullWidth variant='contained'>save</Button>
        </Grid>
    </Grid>
  )
}
