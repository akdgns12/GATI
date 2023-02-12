import React from "react";
import { Link } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";

import { Paper, Box, Typography, Stack, IconButton, Button, TextField } from "@mui/material";
import ArrowDropDownIcon from '@mui/icons-material/ArrowDropDown';
import ArrowDropUpIcon from '@mui/icons-material/ArrowDropUp';

// 이미지 업로드 filepond  라이브러리
import { FilePond, registerPlugin } from 'react-filepond';
import 'filepond/dist/filepond.min.css';
import FilePondPluginImageExifOrientation from 'filepond-plugin-image-exif-orientation';
import FilePondPluginImagePreview from 'filepond-plugin-image-preview';
import 'filepond-plugin-image-preview/dist/filepond-plugin-image-preview.css';
import UploadPic from "./UploadPic";

// Register the plugins
registerPlugin(FilePondPluginImageExifOrientation, FilePondPluginImagePreview);


// 업로드된 이미지를 담는 Box
const BoxStyle = {
  display:'flex',
  justifyContent:'center',
  alignItems:'center',
  width:'65px',
  height:'65px',
  border:'2px dashed #8888',
  borderRadius:'16px',
  margin:3,
}

export default function OnMission() {
  const dispatch = useDispatch()

  // getMission 데이터 가져오기
  const getMission = useSelector(state=>{return state.picsTg.getMission})
  
  // Mission.description 보여주기 토글
  const [onDesc,setOnDesc] = React.useState(false)
  function switchDescription() {
    if (onDesc === false) {
      setOnDesc(true)
    } else {
      setOnDesc(false)
    }
  }
  
  // content Area : 업로드된 사진들이 놓여지는 공간
  let [pic,setPic] = React.useState('')
  let content = []
  for(let i=0; i< getMission.memNumber; i++){
    content.push(<Box style={BoxStyle} key={i} ></Box>)
  }
    
  return (
    <Box>
      {/* 미션 설명하는 부분 */}
      <Paper sx={{
        padding:3,
        }}>
        <Stack direction="row" spacing={2}>
          <Typography align='center' fontWeight='bold' fontSize='15px'> {getMission.title}</Typography>
          <IconButton
            style={{marginLeft:'auto', padding:'0px'}}
            onClick={switchDescription}>
            {onDesc === false ? <ArrowDropDownIcon/>:<ArrowDropUpIcon/>}
          </IconButton>
        </Stack>
        {onDesc === true ? <Typography margin='20px' fontSize='13px'> {getMission.content}</Typography> : null}
        {/* <Typography color='#888888'> {getMission.startDate} ~ {getMission.endDate} </Typography> */}
      </Paper>

      {/* 업로드된 이미지 보여주는 부분*/}
      <Box
        sx={{
          display:'flex',
          justifyContent:'center',
          flexWrap:'wrap',
          margin:'20px 0 20px 0',
        }}>
        {content}
      </Box>

      {/* 이미지 업로드 form */}
      {/* <FilePond files={files} onupdatefiles={setFiles} allowMultiple={true} maxFiles={1} name='files' server='./' /> */}

      {/* 사진과 함께 description 넣을지 말지 고민
      <Box
        component="form"
        sx={{
          display:'flex',
          flexWrap:'wrap',
          '& .MuiTextField-root': { m:1, width:'100%' },
          marginBottom:'20px'
        }}
        noValidate
        autoComplete="off">
        <TextField
          fullwidth='true'
          id="outlined-textarea"
          label="문구 입력"
          placeholder="이번주 사진의 테마에 대해 짧은 문구를 남겨주세요. 문구는 사진과 함께 [가치 한 장 사진첩]에 저장됩니다."
          multiline />
        </Box> */}

      {/* 미션 제출 버튼 */}
      <Box
        sx={{
          display:'flex',
          flexDirection:'column',
          alignItems:'center',
        }}>
        <UploadPic />
        <Button margin='30px' size="medium" variant="contained" disableElevation>
          <Link to="/pictg/missionCompleted" style={{ textDecoration: 'none', color:'white' }}>미션 완료</Link>
        </Button>
      </Box>
    </Box>
  )
};