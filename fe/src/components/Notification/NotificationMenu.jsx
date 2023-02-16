import * as React from "react";
import Menu from "@mui/material/Menu";
import GroupInvitation from "./GroupInvitation";
import OtherNotification from "./OtherNotification";
import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";

import { loadNotification } from "../../store/Nofitication/noti";

export default function NotificationMenu(props) {
  const open = Boolean(props.anchorEl);
  const anchorEl = props.anchorEl;
  const { loginUser } = useSelector((state) => state.user);
  const { notifications } = useSelector((state) => state.noti);
  const dispatch = useDispatch();

  useEffect(() => {
    if (open) {
      dispatch(loadNotification(loginUser.userId)).catch((error) =>
        console.log(error)
      );
    }
  }, [open]);

  const handleClose = () => {
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
      PaperProps={{ style: { width: 300, maxHeight: "45vh" } }}
    >
      {Array.isArray(notifications) &&
        notifications.map((notification, index) => {
          if (notification.type === 1) {
            return (
              <GroupInvitation
                key={index}
                invitation={notification}
                variant="notification"
              />
            );
          } else return <OtherNotification key={index} noti={notification} />;
        })}
    </Menu>
  );
}
