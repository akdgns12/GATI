import React, { Component } from 'react'
import Box from '@mui/material/Box';
import Fab from '@mui/material/Fab';
import AddIcon from '@mui/icons-material/Add';
import AddCircleIcon from '@mui/icons-material/AddCircle';
import { useNavigate } from 'react-router';

export default function AddButton(props) {
  const navigate = useNavigate();
  
  function writeArticle() {
    if (props.mode === 'feed') {
      navigate('/post');
  } else {
    navigate('/photobook/create')
  }
  }
  
  return (
    <Box sx={{ '& > :not(style)': { m: 1 } }}>
      <Fab
        onClick={writeArticle}
        size="small" 
        aria-label="add"
        style={{
        zIndex: "1000", width: "60px", height: "60px",
        position: "fixed", right: "20px", bottom: "70px",
        backgroundColor: '#FF9494',
        boxShadow: '0px 4px 4px rgba(255, 0, 0, 0.25)'
      }}
        >
        <AddIcon sx={{fontSize:'2rem', color:'white'}}/>
      </Fab>
    </Box>
  );
}