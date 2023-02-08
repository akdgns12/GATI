import * as React from 'react';
import { Paper } from '@mui/material';
import Divider from '@mui/material/Divider';
import IconButton from '@mui/material/IconButton';
import CalendarMonthIcon from '@mui/icons-material/CalendarMonth';
import Modal from '@mui/material/Modal';
import ModalDatePicker from './ModalDatePicker';
import InputSearchKeyword from './InputSearchKeyword';


const style = {
  backgroundColor:'white', 
  position: 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 400,
  bgcolor: 'white',
  padding: '10px',
};


export default function SearchBar() {
  const [open, setOpen] = React.useState(false)
  const OpenCalendar = () => {
    setOpen(true)
  }
  const CloseCalendar = () => {
    setOpen(false)
  }
  
  return (
    <div
      style={{
        flex:1,
        display:'flex',
        justifyContent:'center',
        alignItems:'center',
        margin: '10px',
        width: '90vw',
        height: '40px'}}>
      <InputSearchKeyword />
      <Divider sx={{ height: 28, m: 0.5, backgroundColor: '#E8E8E8'}} orientation="vertical" />
      <IconButton onClick={OpenCalendar} color="primary" sx={{ p: '10px'}} aria-label="directions">
        <CalendarMonthIcon/>
      </IconButton>
      <Modal
        open={open}
        onClose={CloseCalendar}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Paper style={style}>
          <ModalDatePicker />
        </Paper>
      </Modal>
    </div>
  );
}