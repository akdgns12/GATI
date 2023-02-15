import * as React from "react";
import Menu from "@mui/material/Menu";
import MenuItem from "@mui/material/MenuItem";
import GroupInvitation from "./GroupInvitation";
import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";

import httpClient from "../../utils/axios";
import { loadNotification } from "../../store/Nofitication/noti";

export default function NotificationMenu(props) {
  const open = Boolean(props.anchorEl);
  const anchorEl = props.anchorEl;
  const { loginUser } = useSelector((state) => state.user);
  const { notifications } = useSelector((state) => state.noti);
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(loadNotification(loginUser.userId))
      .then((res) => {})
      .catch((error) => console.log(error));
  }, [props.anchorEl]);

  const handleClose = () => {
    // console.log("close");
    props.setAnchorEl(null);
  };

  return (
    <Menu
      id="basic-menu"
      anchorEl={anchorEl}
      open={open}
      onClose={handleClose}
      MenuListProps={{
        "aria-labelledby": "basic-button",
      }}
      PaperProps={{ style: { width: 300 } }}
    >
      {Array.isArray(notifications) &&
        notifications.map((notification, index) => {
          if (notification.type === 1) {
            return <GroupInvitation key={index} invitation={notification} />;
          }
        })}
    </Menu>
  );
}
