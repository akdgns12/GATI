import React from 'react'

import { Grid, Typography, Button, Avatar } from '@mui/material'
import FamilyInfo from './FamilyInfo'
export default function FamilyCreate() {
  return (
    <Grid container display='flex' justifyContent='center'>
      <Grid item m={1} xs={10}>
        <Typography>아직 속한 그룹이 없습니다.</Typography>
      </Grid>
      <Grid item m={1} xs={8}>
        <Button variant='contained'>가족 그룹 만들기</Button>
      </Grid>
      <FamilyInfo/>
    </Grid>
  )
}
