import React from "react";
import { Typography, Box } from "@mui/material"
import imgPath from "../../static/MissionCompletedGIF.gif";
import { useNavigate } from "react-router";
import CombinedImg from "../../components/PicsTogether/Inprogress/OnMission/CombinedImg";


export default function CongratsPage() {
  const navigate = useNavigate()
  setTimeout(()=>navigate('/pictg'),2500)

  
  return(
    <div
      style={{
        display:'flex',
        flexDirection:'column',
        justifyContent:'center',
        alignItems:'center',
        backgroundColor:'#ffe7b8',
        width:'100%',
        height:'100vh',
        }}>
      <Typography marginBottom="50px" variant="h4" color="#665c49" fontWeight="800">이번주 미션 성공</Typography>
      <img style={{width:'300px', height:'300px'}} src={imgPath} alt="congratulations"/>
      <Box sx={{ display:'none'}} >
        <CombinedImg />
      </Box>
    </div>

  )
}