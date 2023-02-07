import React from "react";
import { useSelector } from "react-redux";
import { Container,Box, Typography, Stack, IconButton, Button } from "@mui/material";
import ArrowDropDownIcon from '@mui/icons-material/ArrowDropDown';
import ArrowDropUpIcon from '@mui/icons-material/ArrowDropUp';
import PictureBox from "./PictureBox";

export default function OnMission(props) {
  // store.picsTg에서 mission 데이터 가져오기
  const {mission} = useSelector((state)=>state.picsTg)
  
  // Mission.description 보여주기 여부
  const [onDesc,setOnDesc] = React.useState(false)
  function switchDescription() {
    if (onDesc === false) {
      setOnDesc(true)
    } else {
      setOnDesc(false)
    }
  }
  
  // content Area : 업로드된 사진들이 놓여지는 공간
  let [uploadedNum,setUploadedNum] = React.useState(0)
  const picNum = props.picNum
  let content = []
  for(let i=0; i< picNum; i++){
    content.push(<PictureBox key={i} uploadedNum={()=>setUploadedNum(uploadedNum++)}></PictureBox>)
    // console.log(uploadedNum) 여기서부터 다시이이이이~
  }
  
  // 사진 업로드 Drawer
  // const [uploadDrawer,setUploadDrawer] = React.useState(false)
  // function openDrawer() {
    //   setUploadDrawer(true)
    // }
    
    return (
      <Container>
      <Box
        sx={{backgroundColor:'white', padding:'15px', margin:'auto', borderRadius:'8px', width:'70vw'}}>
          <Stack direction="row" spacing={2}>
            <Typography align='center' fontWeight='bold' fontSize='15px'> {mission.title}</Typography>
            <IconButton
              style={{marginLeft:'auto', padding:'0px'}}
              onClick={switchDescription}>
              {onDesc === false ? <ArrowDropDownIcon/>:<ArrowDropUpIcon/>}
            </IconButton>
          </Stack>
        {onDesc === true ? <Typography fontSize='13px'> {mission.description}</Typography> : null}
        <Typography marginTop="15px" color='#888888'> {mission.startdate} ~ {mission.enddate} </Typography>
      </Box>
      <Box
        sx = {{
          margin:'30px',
          display:'flex',
          flexWrap:'wrap',
          justifyContent:'center',
          }}>
        {content}
        { uploadedNum === picNum ? <Button>제출</Button> : null}
      </Box>
    </Container>
  )
};