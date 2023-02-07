import React from "react";
import { useSelector } from "react-redux";
import { Container, Paper, Box, Typography, Stack, IconButton, Button } from "@mui/material";
import ArrowDropDownIcon from '@mui/icons-material/ArrowDropDown';
import ArrowDropUpIcon from '@mui/icons-material/ArrowDropUp';
import PictureBox from "./PictureBox";
import { Link } from "react-router-dom";

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
    content.push(<PictureBox key={i} ></PictureBox>)
    // console.log(uploadedNum) 여기서부터 다시이이이이~
  }

  
  // 사진 업로드 Drawer
  // const [uploadDrawer,setUploadDrawer] = React.useState(false)
  // function openDrawer() {
    //   setUploadDrawer(true)
    // }
    
    return (
      <Box>
        <Paper sx={{
          padding:3,
          }}>
          <Stack direction="row" spacing={2}>
            <Typography align='center' fontWeight='bold' fontSize='15px'> {mission.title}</Typography>
            <IconButton
              style={{marginLeft:'auto', padding:'0px'}}
              onClick={switchDescription}>
              {onDesc === false ? <ArrowDropDownIcon/>:<ArrowDropUpIcon/>}
            </IconButton>
          </Stack>
          {onDesc === true ? <Typography margin='20px' fontSize='13px'> {mission.description}</Typography> : null}
          <Typography color='#888888'> {mission.startdate} ~ {mission.enddate} </Typography>
        </Paper>
        <Box sx={{ marginTop:'50px', borderRadius:8}}>
          <Box sx={{
            display:'flex',
            justifyContent:'center',
            flexWrap:'wrap',
            margin:'20px 0 20px 0'
          }}>
            {content}
          </Box>
          <Box sx={{
            display:'flex',
            justifyContent:'center',
            }}>
            <Button margin='20px' size="medium" variant="contained" disableElevation>
              <Link to="/pictg/missionCompleted" style={{ textDecoration: 'none', color:'white' }}>미션 완료</Link>
            </Button>
          </Box>
        </Box>
      </Box>
  )
};