import React, { Component } from 'react'
import Box from '@mui/material/Box';
import Fab from '@mui/material/Fab';
import AddIcon from '@mui/icons-material/Add';
import { Opacity } from '@mui/icons-material';


export default class addButton extends Component {
  render() {
    return (
      <Box sx={{ '& > :not(style)': { m: 1 } }}>
        <Fab
         size="small" 
         aria-label="add"
         style={{
          zIndex: "1000", width: "60px", height: "60px",
          position: "fixed", right: "20px", bottom: "60px",
          backgroundColor: '#0095FF4D',
        }}
         >
          <AddIcon />
        </Fab>
      </Box>
    )
  }
}
