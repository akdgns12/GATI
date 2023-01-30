import * as React from 'react';
import { Paper, Box } from '@mui/material';
import InputBase from '@mui/material/InputBase';
import Divider from '@mui/material/Divider';
import IconButton from '@mui/material/IconButton';
import MenuIcon from '@mui/icons-material/Menu';
import SearchIcon from '@mui/icons-material/Search';
import CalendarMonthIcon from '@mui/icons-material/CalendarMonth';
import { css } from "@emotion/react";
import { borderRadius } from '@mui/system';


const SearchBar = css`
  display: flex;
  flex-direction: row;
  align-items: center;
  padding: 0px 11px;

`;


export default function CustomizedInputBase() {
  return (
      <container
        component="form"
        css={SearchBar}
        style={{
          margin: '20px',
          width: '311px',
          height: '40px',
          backgroundColor: '#E8E8E8',
          borderRadius: '8px'}}
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
      </container>
  );
}