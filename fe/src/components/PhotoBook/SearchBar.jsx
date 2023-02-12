import * as React from 'react';
import { Paper, Container } from '@mui/material';
import Divider from '@mui/material/Divider';
import IconButton from '@mui/material/IconButton';
import CalendarMonthIcon from '@mui/icons-material/CalendarMonth';
import Modal from '@mui/material/Modal';
import ModalDatePicker from './ModalDatePicker';
import InputSearchKeyword from './InputSearchKeyword';

export default function SearchBar() {
  const [open, setOpen] = React.useState(false)
  const OpenCalendar = () => {
    setOpen(true)
  }
  const CloseCalendar = () => {
    setOpen(false)
  }
  
  return (
    <Container
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
        onClose={CloseCalendar}>
          
        <Paper>
          <ModalDatePicker />
        </Paper>
      </Modal>
    </Container>
  );
}