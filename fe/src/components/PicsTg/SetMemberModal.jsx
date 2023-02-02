import * as React from 'react';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import Button from '@mui/material/Button';
import CloseIcon from '@mui/icons-material/Close';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';

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
  const [pics, setPics] = React.useState('');

  const handleChange = (event) => {
    setPics(event.target.value);
  };

  const onConfirm = () => {
    props.onSelected()
    props.deliverPics(pics)

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
              value={pics}
              label="Pics"
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
          <Button onClick={onConfirm} size="medium" variant="contained"disableElevation>확인</Button>
        </div>
      </Box>
    </Modal>
  );
}

