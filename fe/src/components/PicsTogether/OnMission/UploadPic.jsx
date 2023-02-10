import {Box, IconButton} from '@mui/material';
import AddIcon from '@mui/icons-material/Add';
import { useRef, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { asyncPostImg } from '../../../store/PicsTogether/picsTg';

export default function UploadPic() {
  const dispatch = useDispatch()

  // 이미지 업로드 시 box border 없애기
  const [boxStyle,setBoxStyle] = useState({
    display:'flex',
    justifyContent:'center',
    alignItems:'center',
    width:'145px',
    height:'145px',
    border:'2px dashed #8888',
    borderRadius:'16px',
    margin:3
  })
  const changeBoxStyle = () => {
    setBoxStyle({...boxStyle, border:'2px dashed white'})
  }

  // 업로드된 이미지
  const [imgFile, setImgFile] = useState("");
  const imgRef = useRef();

  const missionId = useSelector(state=>{return state.picsTg.getMission}).id
  const userId = "1"
  
  const uploaded = () => {
    const file = imgRef.current.files[0];
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onloadend = () => {
        const reqData = {img:"file", missionId, userId}
        setImgFile(reader.result);
        dispatch(asyncPostImg(reqData))
      };
  };

  // 사진 업로드 Draw
  // const [openDrawer, setOpenDrawer] = useState()
  // let drawer = null
  // if (openDrawer === true) {
  //   drawer = < CamDrawer open={bottom} onClose={()=>setBottom(false)} onImgURL={(imgURL)=>setImgURL(imgURL)}/>
  // }

  return (
    <Box sx={boxStyle}>
      {imgFile ? <img style={{width:'100%', height:'100%', borderRadius:'16px'}} src={imgFile} objectfit="cover" alt="업로드된 이미지" loading="로딩중..." /> :
        <IconButton sx={{margin:'auto'}} component="label">
          <input hidden accept="image/*" type="file" onInput={uploaded} onChange={changeBoxStyle} ref={imgRef} />
          <AddIcon fontSize="large"/>
        </IconButton>}
    </Box>
  )
}
