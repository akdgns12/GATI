import {Box, IconButton} from '@mui/material';
import AddIcon from '@mui/icons-material/Add';
import { useRef, useState } from 'react';

export default function PictureBox(props) {
  // 이미지 로드가 끝날 시 이미지 보여주기
  const [imgFile, setImgFile] = useState("");
  const imgRef = useRef();
  const printFile = () => {
    const file = imgRef.current.files[0];
    const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onloadend = () => {
          setImgFile(reader.result);
          props.uploadedNum()
      };
  };

  // 사진 업로드 Draw
  // const [openDrawer, setOpenDrawer] = useState()
  // let drawer = null
  // if (openDrawer === true) {
  //   drawer = < CamDrawer open={bottom} onClose={()=>setBottom(false)} onImgURL={(imgURL)=>setImgURL(imgURL)}/>
  // }

  return (
    <Box style={{display:'flex', justifyContent:'center', alignItems:'center', width:'100px', height:'100px', padding:'10px', border:'1px solid black'}}>
      {imgFile ? <img style={{width:'100px', height:'100px'}} src={imgFile} objectfit="cover" alt="업로드된 이미지" loading="로딩중..." /> :
        <IconButton sx={{margin:'auto'}} component="label">
          <input hidden accept="image/*" type="file" onChange={printFile} ref={imgRef} />
          <AddIcon fontSize="large"/>
        </IconButton>}
    </Box>
  )
}
