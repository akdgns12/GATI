import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import { Avatar, withStyles } from '@mui/material';
import MenuIcon from '@mui/icons-material/Menu';
import { styled, useTheme } from '@mui/material/styles';

import Drawer from '@mui/material/Drawer';
import MuiAppBar from '@mui/material/AppBar';
import List from '@mui/material/List';
import Divider from '@mui/material/Divider';
import HomeOutlinedIcon from '@mui/icons-material/HomeOutlined';
import ChevronLeftIcon from '@mui/icons-material/ChevronLeft';
import ChevronRightIcon from '@mui/icons-material/ChevronRight';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import InboxIcon from '@mui/icons-material/MoveToInbox';
import MailIcon from '@mui/icons-material/Mail';
import Button from '@mui/material/Button';
import Stack from '@mui/material/Stack';
import Container from '@mui/material/Container';
import Paper from '@mui/material/Paper';

import Img from "../static/user img.png";
import { useState } from 'react';


const PrimaryAppBar = () => {

  const drawerWidth = '80%';

  const [open, setOpen] = useState(false)

  const theme = useTheme();

  const handleDrawerOpen = () => {
    setOpen(true)
  }

  const handleDrawerClose = () => {
    setOpen(false)
  }

  return (
    <Box sx={{flexGrow: 1, height:55}}>
      <AppBar open={open} position="fixed" style={{background: 'rgb(86, 113, 137)'}}>
        <Toolbar sx={{ justifyContent: 'space-between' }}>
          <Avatar
            sx={{
            height: 40,
            }}
            alt="User img."
            src={Img}
          />
          <Typography
            variant="h4"
            fontWeight= '1000'
            color='skyblue'
            >
            가 티
          </Typography>
          <IconButton
            size="large"
            edge="start"
            color="inherit"
            aria-label="open drawer"
            sx={{ m: 0, p: 0 }}
            onClick={handleDrawerOpen}
            sx={{ mr: 2, ...(open && { display: 'none' }) }}
            >
            <MenuIcon />
          </IconButton>
        </Toolbar>
      </AppBar>
      <Box open={open}>
        <Drawer
          sx={{
            width: drawerWidth,
            flexShrink: 0,
            '& .MuiDrawer-paper': {
              width: drawerWidth,
            },
          }}
          variant="persistent"
          anchor="right"
          open={open}
        >
          <Container>
            <Box sx={{display: 'flex', justifyContent: 'space-between'}}>
              <IconButton onClick={handleDrawerClose} sx={{fontSize: 'large'}}>
                {theme.direction === 'rtl' ? <ChevronLeftIcon /> : <ChevronRightIcon />}
              </IconButton>
              <HomeOutlinedIcon fontSize='large' sx={{p: 2}}/>
            </Box>
            <Typography variant='h5' sx={{p: 2}}>
              username 님 안녕하세요
            </Typography>
          </Container>
          <Divider />
          <Container>
            <Box direction='row' spacing={2} justifyContent='space-between' sx={{p: 1}}>
              <Button variant='outlined'>내 정보</Button>
              <Button variant='outlined'>가족 그룹</Button>
              <Button variant='outlined'>가족 정보</Button>
              <Button variant='outlined'>로그 아웃</Button>
            </Box>
          </Container>
          <Container sx={{height: '70%', bgcolor:'skyblue'}}>
            
          </Container>
          <Divider />
        </Drawer>
      </Box>
    </Box>
  );
}

export default PrimaryAppBar;