

import React, { useState } from 'react'

import { useNavigate, Link } from 'react-router-dom';
import { Container, Grid, TextField, IconButton } from '@mui/material'

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
import ArrowBackIosIcon from '@mui/icons-material/ArrowBackIos';

registerPlugin(FilePondPluginImageExifOrientation, FilePondPluginImagePreview);



export default function AdminCreate() {
  // 네비게이트
  const navigate = useNavigate()
  // 시작날짜 끝날짜
  const [start, setStart] = React.useState(null);
  const [end, setEnd] = React.useState(null);
  // 이미지 업로더 filepond
  const [files, setFiles] = useState([])
  return (
    <Container container='true' sx={{height: '100vh'}} fixed maxWidth="md">
        <IconButton
        style={{ justifyContent:'flex-start'}} 
        onClick={() => navigate(-1)}>
        <ArrowBackIosIcon />
      </IconButton>
        <h2>작성 페이지</h2>
        
        <Grid container sx={{border: 1, display: 'flex', justifyContent: 'center' }}>
          <Grid xs={11} item style={{borderRadius: '5px', backgroundColor: '#D9D9D9', height: '20%', margin: '10px'}}>
            <TextField fullWidth label='title'/>
          </Grid>
          <Grid xs={11} item display='flex' justifyContent='center' style={{borderRadius: '5px', backgroundColor: '#D9D9D9', height: '20%', margin: '10px'}}>
            <Grid item xs={6} p={2}>
              <LocalizationProvider dateAdapter={AdapterDayjs}>
              <DatePicker
                label="시작날짜"
                value={start}
                onChange={(newValue) => {
                  setStart(newValue);
                }}
                renderInput={(params) => <TextField {...params} />}
              />
              </LocalizationProvider>
            </Grid>
            <Grid item xs={6} p={2}>
              <LocalizationProvider dateAdapter={AdapterDayjs}>
              <DatePicker
                label="끝날짜"
                value={end}
                onChange={(newValue) => {
                  setEnd(newValue);
                }}
                renderInput={(params) => <TextField {...params} />}
              />
              </LocalizationProvider>
            </Grid>
          </Grid>
          <Grid xs={11} item style={{borderRadius: '5px', backgroundColor: '#D9D9D9', height: '20%', margin: '10px'}}>
            <TextField height="100%" multiline fullWidth label='content'></TextField>
            
          </Grid>
          <h4>이미지 업로드</h4>
          <Grid xs={11} item style={{borderRadius: '5px', backgroundColor: '#D9D9D9', height: '20%', margin: '10px'}}>
            <FilePond fiies={files} onupdatefiles={setFiles} allowMultiple={true} maxFiles={6} name='files' server='./' />
            
          </Grid>
          <Grid xs={11} display='flex' style={{borderRadius: '5px', height: '20%', margin: '10px'}}>
            <Grid container xs={12} p={1}>
              <Button  variant='outlined'>등록</Button>
              <Button  variant='contained'>취소</Button>
            </Grid>

          </Grid>
        </Grid>
      </Container>
  )
}
