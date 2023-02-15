import * as React from 'react';
import {Box, Typography, Modal, Button, InputLabel, MenuItem, FormControl, Select, Paper,} from '@mui/material';
import CloseIcon from '@mui/icons-material/Close';
import { useDispatch, useSelector } from 'react-redux';
import { asyncPutMission } from '../../../../store/PicsTogether/picsTg';

const style = {
  display:'flex',
  flexDirection:'column',
  alignContent:'flexEnd',
  position: 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: '60%',
  bgcolor: 'background.paper',
  boxShadow: 24,
  padding:'10px'
};


export default function SetMemberModal(props) {
  const dispatch = useDispatch()

  // 미션 업로드 사진 수(인원 설정)
  const [picNum, setPicNum] = React.useState('1')
  const handleChange = (event) => {
    setPicNum(event.target.value)
  };
  
  // asyncPostMission 액션에 넣을 인자 값 reqData 만들기
  const groupMembers = useSelector(state=>{return state.picsTg.getMission}).memNumber
  const id = useSelector(state=>{return state.picsTg.getMission}).id
  console.log('groupMembers',groupMembers)
  const reqData = {id, memNumber:picNum}

  // 확인 버튼 누르면 asyncPostMission 액션 실행(선택한 인원 수 서버에 POST)
  const onConfirm = () => {
    console.log(groupMembers)
    if (picNum <= groupMembers) {
      dispatch(asyncPutMission(reqData))
    } else {
      alert('사진 수는 가족 인원 수를 초과할 수 없습니다.')
    }
  }

  return (
    <Modal
      PaperProps={{ style: { borderRadius: '40px' } }}
      open={props.open}
      aria-labelledby="modal-modal-title"
      aria-describedby="modal-modal-description"
    >
      <Paper sx={style}>
        <CloseIcon style={{marginLeft:'auto', marginBottom:'30px'}} onClick={props.onClose} />
        <Typography fontSize='18px' align="center" variant="h6" component="h2">
          총 몇 장의 사진을 <br></br>찍으실건가요?
        </Typography>
        <Typography fontSize='12px' align="center" sx={{ mt: 2 }}>
          <b>'가치 한 장'</b>을 구성할 사진의 <br></br>장 수를 선택해 주세요.
        </Typography>
        <Box sx={{ display:'flex', justifyContent:'center', minWidth: 120 }}>
          <FormControl
            sx={{marginTop:'30px', width:'60%'}}>
            <InputLabel
              id="demo-simple-select-label" 
            >
              Pics
            </InputLabel >
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
          <Button onClick={onConfirm} size="large" variant="contained" style={{ backgroundColor: '#FF9494', color: 'white' }} disableElevation>확인</Button>
        </div>
      </Paper >
    </Modal>
  );
}

