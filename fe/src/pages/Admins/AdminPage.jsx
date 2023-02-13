import React, { useState } from 'react'

import { useNavigate, Link } from 'react-router-dom';
import { Container, Grid, TextField } from '@mui/material'

// 이미지 업로더 관련
import { FilePond, registerPlugin } from 'react-filepond';
import 'filepond/dist/filepond.min.css';
import FilePondPluginImageExifOrientation from 'filepond-plugin-image-exif-orientation';
import FilePondPluginImagePreview from 'filepond-plugin-image-preview';
import 'filepond-plugin-image-preview/dist/filepond-plugin-image-preview.css';
// mui
import Button from '@mui/material/Button';
import ButtonGroup from '@mui/material/ButtonGroup';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';

registerPlugin(FilePondPluginImageExifOrientation, FilePondPluginImagePreview);

export default function AdminPage() {
  // 관리자 페이지
  const navigate = useNavigate()

  function goCreate() {
    navigate("/admin/create")
  }
  function goEdit() {
    navigate("/admin/edit")
  }

  return (
    <Container container='true' sx={{height: '100vh'}} fixed maxWidth="md">
      <h2>관리자 페이지</h2>
      <Button onClick={goCreate} variant='outlined'>작성</Button>
      <Grid container sx={{border: 1, display: 'flex', justifyContent: 'center' }}>
        <Grid xs={11} item style={{borderRadius: '5px', backgroundColor: '#D9D9D9', height: '20%', margin: '10px'}}>
          <h3>진행중</h3>
          <h4>TITLE:</h4>
          <h5>설명란:</h5>
          <ButtonGroup variant="outlined">
            <Button onClick={goEdit}>수정</Button>
            <Button>삭제</Button>
          </ButtonGroup>
        </Grid>
        <Grid xs={11} item style={{borderRadius: '5px', backgroundColor: '#D9D9D9', height: '20%', margin: '10px'}}>
          <h3>다음주</h3>
          <h4>TITLE:</h4>
          
        </Grid>
        <Grid xs={11} item style={{borderRadius: '5px', backgroundColor: '#D9D9D9', height: '20%', margin: '10px'}}>
          <h3>2주후</h3>
          <h4>TITLE:</h4>
          
        </Grid>
        <Grid xs={11} item style={{borderRadius: '5px', backgroundColor: '#D9D9D9', height: '20%', margin: '10px'}}>
          <h3>3주후</h3>
          <h4>등록해줘</h4>
          
        </Grid>
        <Grid xs={11} item style={{borderRadius: '5px', backgroundColor: '#D9D9D9', height: '20%', margin: '10px'}}>
          <h3>4주후</h3>
          <h4>등록해줘</h4>
        </Grid>
      </Grid>
    </Container>
  )
}
