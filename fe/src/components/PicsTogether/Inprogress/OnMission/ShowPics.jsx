import {Box, Typography} from '@mui/material'
import { useState } from 'react'
import { useSelector } from 'react-redux'


export default function ShowPics(props) {
  // 업로드된 이미지를 담는 Box 스타일
  const [boxStyle,setBoxStyle] = useState({
    width:'65px',
    height:'65px',
    border:'3px dashed white',
    borderRadius:'16px',
    margin:0.5,
  })
  const changeBoxStyle = () => {
    setBoxStyle({...boxStyle, border:'2px dashed white'})
  }

  console.log(props.dto)

  let variableBox = <Box sx={boxStyle} />
  if (props.dto) {
    const imgURL = 'https://i8a805.p.ssafy.io/' + props.dto.img
    const nickName = props.dto.nickName
    variableBox =
      <Box
        sx={{
          display:'flex',
          flexDirection:'column',
          alignItems:'center',
        }}
      >
        <Box sx={boxStyle}>
          <img src={imgURL} width="100%" height="100%" style={{borderRadius:'16px'}} alt='내가 업로드한 이미지' onLoad={changeBoxStyle} />
          {/* <Typography sx={{textAlign:'right', padding:0.3}} color="#8888">By {props.dto.userId}</Typography> */}
        </Box>
        <Typography sx={{textAlign:'right', padding:0.3}} color="#8888">{nickName}</Typography>
      </Box>
  }
  
  // getMission
  return (
    variableBox
  )
}