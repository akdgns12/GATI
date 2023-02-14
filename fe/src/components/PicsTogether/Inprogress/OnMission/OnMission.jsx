import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";

import { Paper, Box, Typography, Stack, IconButton, Button, TextField } from "@mui/material";
import ArrowDropDownIcon from '@mui/icons-material/ArrowDropDown';
import ArrowDropUpIcon from '@mui/icons-material/ArrowDropUp';
import ShowPics from "./ShowPics";
import UploadPic from "./UploadPic";
import { asyncPostMission } from "../../../../store/PicsTogether/picsTg";
import CombinedImg from "./CombinedImg";


export default function OnMission() {
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
  let content = []
  let blankBox = 0

  if (getMission) {
    if (getMission.missionImageDtos) {
      getMission.missionImageDtos.map((dto, index) => content.push(<ShowPics key={index + 15} dto={dto} />));
      blankBox = getMission.memNumber - getMission.missionImageDtos.length;
    } else {
      blankBox = getMission.memNumber;
    }
  }

  for (let i=0; i<blankBox; i++) {
    content.push(<ShowPics key={i}/>)
  }

  // 사진을 모두 업로드하면 미션 완료 버튼 활성화
  let btnDisabled = true
  if (getMission.missionImageDtos && getMission.missionImageDtos.length === getMission.memNumber) {
    btnDisabled = false
  }

  // 미션 완료 버튼 누르면 유저가 업로드한 이미지들 합쳐주는 컴포넌트 실행
  const navigate = useNavigate()
  const missionComplete = () => {
    navigate('/pictg/congrats')
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
          marginTop:'20px'
        }}>
        <UploadPic />
        <Button
          disabled={btnDisabled}
          margin='30px'
          size="medium"
          variant="contained"
          onClick={missionComplete}
          disableElevation>
            미션 완료
        </Button>
      </Box>
    </Box>
  )
};