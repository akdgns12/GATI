import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
<<<<<<< HEAD
import Badge from '@mui/material/Badge';
import MenuIcon from '@mui/icons-material/Menu';
import NotificationsIcon from '@mui/icons-material/Notifications';
import Img from "../static/user img.png";
import { Avatar, withStyles } from '@mui/material';
=======
import { Avatar, withStyles } from '@mui/material';
import MenuIcon from '@mui/icons-material/Menu';
import { styled, useTheme } from '@mui/material/styles';

import Drawer from '@mui/material/Drawer';
import Divider from '@mui/material/Divider';
import HomeOutlinedIcon from '@mui/icons-material/HomeOutlined';
import ChevronLeftIcon from '@mui/icons-material/ChevronLeft';
import ChevronRightIcon from '@mui/icons-material/ChevronRight';

import Button from '@mui/material/Button';
import Stack from '@mui/material/Stack';
import Container from '@mui/material/Container';
import Paper from '@mui/material/Paper';
import Grid from '@mui/material/Grid';
import Img from "../static/user img.png";
import { useState } from 'react';

import MyInfo from './SideBar/MyInfo';
import Family from './SideBar/Family';
import Logout from './SideBar/Logout';
import FamilyCreate from './SideBar/FamilyCreate';
>>>>>>> 5fa15d9e9f77c52496440f81edc660485d37a3da

const PrimaryAppBar = () => {

  const drawerWidth = '80%';
  const [open, setOpen] = useState(false)
  const [logout, setLogout] = useState(false)
  const [myinfo, setMyinfo] = useState(false)
  const [family, setFamily] = useState(false)
  const [familyinfo, setFamilyinfo] = useState(false)
  

  const theme = useTheme();

  const handleDrawerOpen = () => {
    setOpen(true)
  }

  const handleDrawerClose = () => {
    setOpen(false)
  }

  const openMyinfo = () => {
    setMyinfo(true)
    setFamily(false)
    setFamilyinfo(false)
    setLogout(false)
  }
  const openFamily = () => {
    setFamily(true)
    setMyinfo(false)
    setFamilyinfo(false)
    setLogout(false)
  }
  const openFamilyinfo = () => {
    setMyinfo(false)
    setFamily(false)
    setFamilyinfo(true)
    setLogout(false)
  }
  const openLogout = () => {
    setLogout(true)
    setMyinfo(false)
    setFamily(false)
    setFamilyinfo(false)
  }

<<<<<<< HEAD
  const list = (anchor) => (
    <Box
      sx={{ width: anchor === 'anchor' || anchor === 'bottom' ? 'auto' : 250 }}
      role="presentation"
      onClick={toggleDrawer(anchor, false)}
      onKeyDown={toggleDrawer(anchor, false)}
    >
    </Box>
  );
=======
>>>>>>> 5fa15d9e9f77c52496440f81edc660485d37a3da

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
            <Box display='flex' spacing={1} justifyContent='space-between' sx={{p: 1}}>
              <Button onClick={openMyinfo} variant='outlined'>내 정보</Button>
              <Button onClick={openFamily} variant='outlined'>가족 그룹</Button>
              <Button onClick={openFamilyinfo} variant='outlined'>가족 등록</Button>
              <Button onClick={openLogout} variant='outlined'>로그 아웃</Button>
            </Box>
          </Container>
          <Divider />
          <Container sx={{height: '70%'}}>
            {myinfo && <MyInfo/>}
            {family && <Family/>}
            {familyinfo && <FamilyCreate/>}
            {logout && <Logout/>}
          </Container>
          <Divider />
        </Drawer>
      </Box>
    </Box>
  );
}

export default PrimaryAppBar;