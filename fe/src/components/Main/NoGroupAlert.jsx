import * as React from "react";
import PropTypes from "prop-types";
import Button from "@mui/material/Button";
import { styled } from "@mui/material/styles";
import Dialog from "@mui/material/Dialog";
import DialogTitle from "@mui/material/DialogTitle";
import DialogContent from "@mui/material/DialogContent";
import DialogActions from "@mui/material/DialogActions";
import IconButton from "@mui/material/IconButton";
import CloseIcon from "@mui/icons-material/Close";

import NotInGroup from "./NotInGroup";

function DialogTitleWithClose(props) {
  const { children, onClose, ...other } = props;

  return (
    <DialogTitle sx={{ m: 0, p: 2 }} {...other}>
      {children}
      {onClose ? (
        <IconButton
          aria-label="close"
          onClick={onClose}
          sx={{
            position: "absolute",
            right: 8,
            top: 8,
            color: (theme) => theme.palette.grey[500],
          }}
        >
          <CloseIcon />
        </IconButton>
      ) : null}
    </DialogTitle>
  );
}

export default function NoGroupAlertDialog(props) {
  const handleClose = () => {
    props.onClose();
  };

  function mvToRegister() {
    console.log("move to group register page");
    alert("move page");
    props.onClose();
  }

  return (
    <div>
      <Dialog onClose={handleClose} aria-labelledby="customized-dialog-title" open={props.show}>
        <DialogTitleWithClose id="customized-dialog-title" onClose={handleClose}>
          알림
        </DialogTitleWithClose>
        <DialogContent dividers>
          <NotInGroup />
        </DialogContent>
        <DialogActions>
          <Button variant="outlined" size="small" onClick={handleClose}>
            취소
          </Button>
          <Button variant="contained" size="small" onClick={mvToRegister}>
            가족 그룹 만들기
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  );
}
