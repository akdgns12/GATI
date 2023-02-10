import { Box, IconButton } from '@mui/material';
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
    <Box style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', width: '145px', height: '145px', border: '1px dashed #8888', margin: 3 }}>
      {imgFile ? <img style={{ width: '100%', height: '100%' }} src={imgFile} objectfit="cover" alt="업로드된 이미지" loading="로딩중..." /> :
        <IconButton sx={{ margin: 'auto' }} component="label">
          <input hidden accept="image/*" type="file" onChange={printFile} ref={imgRef} />
          <AddIcon fontSize="large" />
        </IconButton>}
    </Box>
  )
}
