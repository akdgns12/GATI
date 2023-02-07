import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import Badge from '@mui/material/Badge';
import MenuIcon from '@mui/icons-material/Menu';
import NotificationsIcon from '@mui/icons-material/Notifications';
import Img from "../static/user img.png";
import { Avatar, withStyles } from '@mui/material';

const PrimaryAppBar = () => {
  const [anchorEl, setanchorEl] = React.useState(null);
  const [mobileMoreanchorEl, setMobileMoreanchorEl] = React.useState(null);
  const [state, setState] = React.useState({
    anchor: false,
  });

  const toggleDrawer = (anchor, open) => (event) => {
    if (
      event &&
      event.type === 'keydown' &&
      (event.key === 'Tab' || event.key === 'Shift')
    ) {
      return;
    }

    setState({ ...state, [anchor]: open });
  };

  const list = (anchor) => (
    <Box
      sx={{ width: anchor === 'anchor' || anchor === 'bottom' ? 'auto' : 250 }}
      role="presentation"
      onClick={toggleDrawer(anchor, false)}
      onKeyDown={toggleDrawer(anchor, false)}
    >
    </Box>
  );

  return (
    <Box
      sx={{flexGrow: 1, height:55}}>
      <AppBar position="fixed" style={{background: 'rgb(86, 113, 137)'}}>
        <Toolbar>
          <Avatar
            sx={{
            height: 40,
            }}
            alt="User img."
            src={Img}
          />
          <Typography
            variant="h6"
            noWrap
            component="div"
            sx={{ display: { xs: 'block', sm: 'block' } }}
            style={{padding:10}}
          >
            family name
          </Typography>
          <Box sx={{ flexGrow: 1 }} />
          <Box sx={{ display: { xs: 'flex', md: 'flex' } }}>
            <IconButton
                size="large"
                aria-label="show 17 new notifications"
                color="inherit"
            >
            <NotificationsIcon />
            <Badge badgeContent={17} color="error">
            </Badge>
          </IconButton>
          {/* <React.Fragment key={anchor}> */}
          <IconButton
          size="large"
          edge="start"
          color="inherit"
          aria-label="open drawer"
          sx={{ mr: 2 }}
          style={{
            margin:0
          }}
          // onClick={toggleDrawer("right", true)}>{anchor}
          >
          <MenuIcon>
          </MenuIcon>
          
          </IconButton>
          </Box>
        </Toolbar>
      </AppBar>
    </Box>
  );
}

export default PrimaryAppBar;