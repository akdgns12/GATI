import {Box, IconButton} from '@mui/material';
import AddIcon from '@mui/icons-material/Add';
import { useEffect, useRef, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { asyncDeleteImg, asyncPostImg } from '../../../store/PicsTogether/picsTg';
import CancelIcon from '@mui/icons-material/Cancel';

export default function UploadPic() {
  const dispatch = useDispatch()

  // 이미지 업로드 시 box border 없애기 (디자인 요소)
  const [boxStyle,setBoxStyle] = useState({
    display:'flex',
    justifyContent:'center',
    alignItems:'center',
    width:'200px',
    height:'200px',
    border:'2px dashed #8888',
    borderRadius:'16px',
    margin:3
  })
  const changeBoxStyle = () => {
    setBoxStyle({...boxStyle, border:'2px dashed white'})
  }

  // reqData 
  const getMission = useSelector(state=>{return state.picsTg.getMission})
  const missionId = getMission.id
  const userId = '1'
  
  // 이미지 파일 업로드 함수
  const imgRef = useRef();
  const reader = new FileReader();

  const upload = () => {
    const file = imgRef.current.files[0];
    console.log('file',file)
    reader.onloadend = () => {
      // console.log('file',file)
      // console.log('DataUrl',reader.result)
      const formData = new FormData()
      formData.append('img', file)
      formData.append('missionId', missionId)
      formData.append('userId', userId)
      dispatch(asyncPostImg(formData))
    };
  };

  // myUpload : 내가 올린 사진 정보
  const uploadedImgList = getMission.missionImageDtos
  const myUpload = uploadedImgList.filter(obj => obj.userId === userId)
  console.log('myUpload',myUpload)
  
  // 이미지 삭제
  const deleteImg = () => {
    dispatch(asyncDeleteImg(myUpload[0].id))
  }
  
  // user가 이미 사진을 업로드 한 경우
  let content = null
  if (myUpload != 0) {
      const imgURL = myUpload[0].img
      content =
      <Box>
        <img style={{width:'100%', height:'100%', borderRadius:'16px'}} src={imgURL} objectfit="cover" alt="업로드된 이미지" loading="로딩중..." onLoad={changeBoxStyle} />
        <IconButton onClick={deleteImg} sx={{position: 'absolute', top: '49%', left: '75%',}}>
          <CancelIcon fontSize='medium' />
        </IconButton>
      </Box>
    }
  
  // user가 아직 업로드를 안 한 경우
  else {
    content =
    <IconButton sx={{margin:'auto'}} component="label">
      <input hidden accept="image/*" type="file" onInput={upload} onChange={changeBoxStyle} ref={imgRef} />
      <AddIcon fontSize="large"/>
    </IconButton>
  }

  return (
    <Box sx={boxStyle}>
      {content}
    </Box>
  )
}
