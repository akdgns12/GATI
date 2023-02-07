import * as React from 'react';
import {Box, Typography, Modal, Button, InputLabel, MenuItem, FormControl, Select} from '@mui/material';
import CloseIcon from '@mui/icons-material/Close';

const style = {
  display:'flex',
  flexDirection:'column',
  alignContent:'flex-end',
  position: 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: '50%',
  bgcolor: 'background.paper',
  boxShadow: 24,
  padding:'10px'
};

export default function SetMemberModal(props) {
  // 미션 참여 인원 수 설정
  const [picNum, setPicNum] = React.useState('');
  const handleChange = (event) => {
    setPicNum(event.target.value);
  };

  // 확인 버튼 -> 상위 컴포넌트에 selected=true(-> onMission 컴포넌트 전환), PicNum 전달
  const onConfirm = () => {
    props.onSelected()
    props.deliverPicNum(picNum)
  }

  return (
    <Modal
      open={props.open}
      aria-labelledby="modal-modal-title"
      aria-describedby="modal-modal-description"
    >
      <Box sx={style}>
        <CloseIcon style={{marginLeft:'auto', marginBottom:'30px'}} onClick={props.onClose} />
        <Typography fontSize='18px' align="center" variant="h6" component="h2">
          총 몇 장의 사진을 찍으실건가요?
        </Typography>
        <Typography fontSize='12px' align="center" sx={{ mt: 2 }}>
          <b>'가치 한 장'</b>을 구성할 사진의 장 수를 선택해 주세요.
        </Typography>
        <Box sx={{ minWidth: 120 }}>
          <FormControl fullWidth>
            <InputLabel id="demo-simple-select-label">Pics</InputLabel>
            <Select
              labelId="demo-simple-select-label"
              id="demo-simple-select"
              value={picNum}
              label="PicNum"
              onChange={handleChange}
            >
              <MenuItem value={1}>1</MenuItem>
              <MenuItem value={2}>2</MenuItem>
              <MenuItem value={3}>3</MenuItem>
              <MenuItem value={4}>4</MenuItem>
              <MenuItem value={5}>5</MenuItem>
              <MenuItem value={6}>6</MenuItem>
              <MenuItem value={7}>7</MenuItem>
              <MenuItem value={8}>8</MenuItem>
            </Select>
          </FormControl>
        </Box>
        <div
          style={{
            display:'flex',
            justifyContent:'center',
            margin:'20px 20px'
          }}>
          <Button onClick={onConfirm} size="medium" variant="contained" disableElevation>확인</Button>
        </div>
      </Box>
    </Modal>
  );
}

