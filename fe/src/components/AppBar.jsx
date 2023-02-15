import * as React from "react";
import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import IconButton from "@mui/material/IconButton";
import Typography from "@mui/material/Typography";
import { Avatar, withStyles } from "@mui/material";
import MenuIcon from "@mui/icons-material/Menu";
import NotificationsOutlinedIcon from "@mui/icons-material/NotificationsOutlined";
import { styled, useTheme } from "@mui/material/styles";

import Drawer from "@mui/material/Drawer";
import Divider from "@mui/material/Divider";
import HomeOutlinedIcon from "@mui/icons-material/HomeOutlined";
import ChevronLeftIcon from "@mui/icons-material/ChevronLeft";
import ChevronRightIcon from "@mui/icons-material/ChevronRight";

import Button from "@mui/material/Button";
import Container from "@mui/material/Container";
import Img from "../static/big-family.png";
import { useState } from "react";

import MyInfo from "./SideBar/MyInfo";
import Family from "./SideBar/Family";
import Logout from "./SideBar/Logout";
import FamilyCreate from "./SideBar/FamilyCreate";
import NotificationMenu from "./Notification/NotificationMenu";
import { useNavigate } from "react-router";
import { doLogOut } from "../utils/logOutUtil";
import CreateFamilyModal from "./SideBar/CreateFamilyModal";
import { useDispatch, useSelector } from "react-redux";
import { useEffect } from "react";
import httpClient from "../utils/axios";
import { loadNotification } from "../store/Nofitication/noti";

const PrimaryAppBar = () => {
  const drawerWidth = "80%";
  const [open, setOpen] = useState(false);
  const [logout, setLogout] = useState(false);
  const [myinfo, setMyinfo] = useState(false);
  const [family, setFamily] = useState(false);
  const [familyinfo, setFamilyinfo] = useState(false);
  const [modalOpen, setModalOpen] = useState(false);
  const [anchorEl, setAnchorEl] = useState(null);
  const [groupIMG, setGroupIMG] = useState("");

  const { loginUser, mainGroup } = useSelector((state) => state.user);
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const theme = useTheme();

  useEffect(() => {
    dispatch(loadNotification(loginUser.userId));
  }, []);

  useEffect(() => {
    setGroupIMG(process.env.REACT_APP_IMG_ROOT + "/" + mainGroup.img);
  }, [mainGroup]);

  const handleDrawerOpen = () => {
    setOpen(true);
  };

  const handleDrawerClose = () => {
    setOpen(false);
  };

  const openMyinfo = () => {
    setMyinfo(true);
    setFamily(false);
    setFamilyinfo(false);
    setLogout(false);
  };
  const openFamily = () => {
    setFamily(true);
    setMyinfo(false);
    setFamilyinfo(false);
    setLogout(false);
  };
  const openFamilyinfo = () => {
    setMyinfo(false);
    setFamily(false);
    setFamilyinfo(true);
    setLogout(false);
  };
  const openLogout = async () => {
    setLogout(true);
    setMyinfo(false);
    setFamily(false);
    setFamilyinfo(false);

    if (window.confirm("LOG OUT ?")) {
      doLogOut();
      navigate("/login");
    }
  };

  const showmsg = (event) => {
    event.stopPropagation();
    navigate("/");
  };

  function handleNotiOpen(event) {
    event.preventDefault();
    setAnchorEl(event.currentTarget);
  }

  return (
    <Box sx={{ flexGrow: 1, height: "100px" }}>
      <AppBar open={open} position="fixed" style={{ background: "rgb(255, 255, 255, 1.0)" }}>
        <Toolbar
          sx={{
            display: "flex",
            flexWrap: "wrap",
            alignItems: "center",
            justifyContent: "space-between",
            height: "70px",
          }}
        >
          <Box
            sx={{
              display: "flex",
              alignItems: "center",
              width: "77%",
            }}
          >
            <Avatar
              onClick={showmsg}
              sx={{
                width: 50,
                height: 50,
                marginRight: 1.5,
              }}
              alt="gati img."
              src={groupIMG}
              style={{ display: "inline-block", cursor: "pointer" }}
            />
            <Typography
              variant="h6"
              sx={{ fontFamily: "ONE-Mobile-POP" }}
              color="rgb(32,32,32)"
              style={{ display: "inline-block" }}
            >
              {mainGroup != null && mainGroup.name != null && mainGroup.name}
            </Typography>
          </Box>
          <Box
            sx={{
              display: "flex",
              justifyContent: "center",
              alignItems: "center",
            }}
          >
            <IconButton>
              <NotificationsOutlinedIcon
                style={{ fontSize: "30px", color: "FF9494" }}
                onClick={handleNotiOpen}
              />
            </IconButton>
            <IconButton
              size="large"
              edge="start"
              color="rgb(0,0,0,0.75)"
              aria-label="open drawer"
              // sx={{ m: 0, p: 0 }}
              onClick={handleDrawerOpen}
              sx={{ mr: 2, ...(open && { display: "none" }), m: 0, p: 0 }}
            >
              <MenuIcon style={{ fontSize: "30px", color: "FF9494" }} />
            </IconButton>
          </Box>
        </Toolbar>
      </AppBar>
      <Box open={open}>
        <Drawer
          sx={{
            width: drawerWidth,
            flexShrink: 0,
            "& .MuiDrawer-paper": {
              width: drawerWidth,
            },
          }}
          variant="persistent"
          anchor="right"
          open={open}
        >
          <Container>
            <Box sx={{ display: "flex", justifyContent: "space-between" }}>
              <IconButton onClick={handleDrawerClose} sx={{ fontSize: "large" }}>
                {theme.direction === "rtl" ? <ChevronLeftIcon /> : <ChevronRightIcon />}
              </IconButton>
              <HomeOutlinedIcon fontSize="large" sx={{ p: 2 }} />
            </Box>
            <Typography variant="h5" sx={{ p: 2 }}>
              {loginUser != null ? loginUser.nickName : "?"} 님 안녕하세요
            </Typography>
          </Container>
          <Divider />
          <Container>
            <Box display="flex" spacing={1} justifyContent="space-between" sx={{ p: 1 }}>
              <Button onClick={openMyinfo} variant="outlined">
                내 정보
              </Button>
              <Button onClick={openFamily} variant="outlined">
                가족 그룹
              </Button>
              <Button onClick={openFamilyinfo} variant="outlined">
                가족 등록
              </Button>
              <Button onClick={openLogout} variant="outlined">
                로그 아웃
              </Button>
            </Box>
          </Container>
          <Divider />
          <Container sx={{ height: "70%" }}>
            {myinfo && <MyInfo />}
            {family && <Family setOpen={setModalOpen} setSideOpen={setOpen} />}
            {familyinfo && <FamilyCreate setOpen={setModalOpen} />}
            {logout && <Logout />}
          </Container>
          {/* <Divider /> */}
        </Drawer>
      </Box>
      <CreateFamilyModal open={modalOpen} setOpen={setModalOpen} />
      <NotificationMenu anchorEl={anchorEl} setAnchorEl={setAnchorEl} />
    </Box>
  );
};

export default PrimaryAppBar;
