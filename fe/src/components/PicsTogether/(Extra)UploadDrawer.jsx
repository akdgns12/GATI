import * as React from 'react';
import Box from '@mui/material/Box';
import Drawer from '@mui/material/Drawer';
import Button from '@mui/material/Button';
import List from '@mui/material/List';
import Divider from '@mui/material/Divider';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import AddAPhotoIcon from '@mui/icons-material/AddAPhoto';
import PhotoLibraryIcon from '@mui/icons-material/PhotoLibrary';
import { useDispatch,useSelector } from 'react-redux';
// import { plus } from '../../store/PicsTogether/picsTg';

export default function UploadDrawer(props) {
  // const dispatch = useDispatch()
  // const {uploadedImgs} = useSelector(state=>state.picsTg)
  const imgRef = React.useRef()
  const list =
    <Box
      sx={{ width:'100%'}}
      role="presentation"
      onKeyDown={props.onClose}
    >
      <List>
        <ListItem disablePadding>
          <ListItemButton onClick={()=>alert('사진 촬영 모드 on')}>
            <ListItemIcon>
              <AddAPhotoIcon />
            </ListItemIcon>
            <ListItemText primary={'사진 촬영'} />
          </ListItemButton>
        </ListItem>
      </List>
      <Divider variant='fullWidth'/>
      <List>
        <ListItem disablePadding>
          <ListItemButton aria-label="upload picture" component="label">
            <input hidden accept="image/*" multiple type="file" ref={imgRef} onChange={onChange} />
            <ListItemIcon>
              <PhotoLibraryIcon />
            </ListItemIcon>
            <ListItemText primary={'보관함에서 선택'} />
          </ListItemButton>
        </ListItem>
      </List>
    </Box>
  
  function onChange(e) {
    const file = e.target.files[0]
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onloadend = () => {
      const data = reader.result
      // 사진 업로드 받아오는 거~ 다시 해야 함~
      // dispatch(plus(data))
      props.onImgURL(reader.result)
    }
  }

  return (
    <div>
      <Drawer
        PaperProps={{
          sx:{display:'flex', alignItems:'center', borderRadius:'20px 20px 0 0'}
        }}
        anchor="bottom"
        open={props.open}
        onClose={props.onClose}
      >
        {list}
      </Drawer>
    </div>
  );
}