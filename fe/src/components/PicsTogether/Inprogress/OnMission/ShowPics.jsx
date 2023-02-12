import {Box, Typography} from '@mui/material'
import { useState } from 'react'
import { useSelector } from 'react-redux'

export default function ShowPics(props) {

  // 업로드된 이미지를 담는 Box 스타일
  const [boxStyle,setBoxStyle] = useState({
    width:'65px',
    height:'65px',
    border:'2px dashed #8888',
    borderRadius:'16px',
    margin:0.5,
  })
  const changeBoxStyle = () => {
    setBoxStyle({...boxStyle, border:'2px dashed white'})
  }
  
  let variableBox = <Box sx={boxStyle} />
  if (props.dto) {
    variableBox =
      <Box sx={boxStyle}>
        <img src={props.dto.img} width="100%" height="100%" style={{borderRadius:'16px'}} alt='내가 업로드한 이미지' onLoad={changeBoxStyle} />
        {/* <Typography sx={{textAlign:'right', padding:0.3}} color="#8888">By {props.dto.userId}</Typography> */}
        <Typography sx={{textAlign:'right', padding:0.3}} color="#8888">귀여운짱구</Typography>
      </Box>
  }
  
  // getMission
  return (
    variableBox
  )
}