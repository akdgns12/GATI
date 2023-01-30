import * as React from 'react';
import Paper from '@mui/material/Paper';
import InputBase from '@mui/material/InputBase';
import Divider from '@mui/material/Divider';
import IconButton from '@mui/material/IconButton';
import MenuIcon from '@mui/icons-material/Menu';
import SearchIcon from '@mui/icons-material/Search';
import CalendarMonthIcon from '@mui/icons-material/CalendarMonth';


export default function CustomizedInputBase() {
  return (
      <Paper
        component="form"
        sx={{mx: 'auto', p: '2px 4px', display: 'flex', alignItems: 'center', width: 400, }}
      >
        <InputBase
          sx={{ ml: 1, flex: 1 }}
          placeholder="검색어를 입력하세요."
          inputProps={{ 'aria-label': 'search keywords' }}
        />
        <IconButton type="button" sx={{ p: '10px' }} aria-label="search">
          <SearchIcon />
        </IconButton>
        {/* <Divider sx={{ height: 28, m: 0.5 }} orientation="vertical" />
        <IconButton color="primary" sx={{ p: '10px' }} aria-label="directions">
          <CalendarMonthIcon />
        </IconButton> */}
      </Paper>
  );
}