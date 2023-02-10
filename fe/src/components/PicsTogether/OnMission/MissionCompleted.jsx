import React from "react";
import { Typography } from "@mui/material"
import imgPath from "../../../static/MissionCompletedGIF.gif";
import { useNavigate } from "react-router";

export default function MissionCompleted() {
  const navigate = useNavigate()
  setTimeout(()=>navigate('/pictg'),2500)

  return(
    <div style={{display:'flex', flexDirection:'column', justifyContent:'center', alignItems:'center', backgroundColor:'#ffe7b8', width:'100vw', height:'100vh'}}>
      <Typography marginBottom="50px" variant="h4" color="#665c49" fontWeight="800">이번주 미션 성공</Typography>
      <img style={{width:'390px', height:'400px'}} src={imgPath} alt="congratulations"/>
    </div>

  )
}