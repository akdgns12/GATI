import React from "react";
import { useNavigate, useParams } from "react-router";

import { useDispatch, useSelector } from 'react-redux';
import { asyncDeleteMission } from "../../store/PicsTogether/picsTg";

import Card from "@mui/material/Card";
import CardHeader from "@mui/material/CardHeader";
import CardMedia from "@mui/material/CardMedia";
import CardContent from "@mui/material/CardContent";
import CardActions from "@mui/material/CardActions";
import {Typography, Box, Stack, IconButton} from '@mui/material';
import ArrowDropDownIcon from '@mui/icons-material/ArrowDropDown';
import ArrowDropUpIcon from '@mui/icons-material/ArrowDropUp';
import ArrowBackIosIcon from '@mui/icons-material/ArrowBackIos';
import BookmarkIcon from '@mui/icons-material/Bookmark';
import FavoriteIcon from '@mui/icons-material/Favorite';
import DeleteIcon from '@mui/icons-material/Delete';


export default function MissionDetailPage() {
  const navigate = useNavigate()
  const dispatch = useDispatch()
  const { id } = useParams()

  // Mission 디테일 정보 가져오기
  const getMissionList = useSelector(state => {return state.picsTg.getMissionList})
  const mission = getMissionList.filter((mission)=> mission.id == id)[0]
  const imageURL = 'https://i8a805.p.ssafy.io/'

  console.log(getMissionList)
  console.log(mission)

  // 미션 삭제 함수
  const deleteMission = () => {
    if ( window.confirm('이번주 등록한 미션을 정말 삭제하시겠습니까?') == true) {
      dispatch(asyncDeleteMission(mission.id))
    }
  }

  // Mission.description 보여주기 토글
  const [onDesc,setOnDesc] = React.useState(false)
  function switchDescription() {
    if (onDesc === false) {
      setOnDesc(true)
    } else {
      setOnDesc(false)
    }
  }

  return (
    <Box sx={{
      display:'flex',
      flexDirection:'column',
      alignContent:'flex-start',
      padding:3,
    }}>
      <IconButton
        style={{ justifyContent:'flex-start', marginBottom:'20px'}} 
        onClick={() => navigate(-1)}>
        <ArrowBackIosIcon />
      </IconButton>
      <Card
      elevation={2}
      sx={{ borderRadius: 1 }}
      style={{ marginBottom: "10px", width: "100%" }}
      >
        <CardHeader
          action={
            <IconButton aria-label="delete" onClick={deleteMission}>
              <DeleteIcon />
            </IconButton>
          }
          style={{ textAlign: "left", padding: "10px"}}
        />
        <CardMedia
          component="img"
          width="100%"
          image={imageURL + mission.img}
          alt="Mission Img"
        />
        <CardActions sx={{borderBottom: "1px dashed #D9D9D9"}} disableSpacing={true}>
          {/* <IconButton aria-label="add to favorites" >
            <FavoriteIcon />
          </IconButton> */}
          <Box style={{ marginLeft: "auto" }}>
            <Typography
              variant="body4"
              style={{ fontWeight: "bold", marginRight: "10px" }}
            >
            {mission.endDate}
            </Typography>
            {/* <IconButton aria-label="add to album" >
              <BookmarkIcon />
            </IconButton> */}
          </Box>
        </CardActions>

        <CardContent>
          <Stack direction="row" spacing={2}>
            <Typography align='center' fontWeight='bold' fontSize='15px'> {mission.title}</Typography>
            <IconButton
              style={{marginLeft:'auto', padding:'0px'}}
              onClick={switchDescription}>
              {onDesc === false ? <ArrowDropDownIcon/>:<ArrowDropUpIcon/>}
            </IconButton>
          </Stack>
          {onDesc === true ? <Typography margin='20px' fontSize='13px'> {mission.content}</Typography> : null}
          {/* <Typography color='#888888'> {mission.startDate} ~ {mission.endDate} </Typography> */}
        </CardContent>
      </Card>
    </Box>
  )
}
