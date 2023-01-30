import React, { Component } from 'react'
import Box from '@mui/material/Box';
import Fab from '@mui/material/Fab';
import AddIcon from '@mui/icons-material/Add';


export default class addButton extends Component {
  render() {
    return (
      <Box sx={{ '& > :not(style)': { m: 1 } }}>
        <Fab
         size="small" 
         color='secondary'
         aria-label="add"
         style={{
          zIndex: "1000", width: "60px", height: "60px",
          position: "fixed", right: "20px", bottom: "60px",
        }}
         >
          <AddIcon />
        </Fab>
      </Box>
    )
  }
}
