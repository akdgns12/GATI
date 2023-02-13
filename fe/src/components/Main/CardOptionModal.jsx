import * as React from "react";
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import Backdrop from "@mui/material/Backdrop";
import Box from "@mui/material/Box";
import Modal from "@mui/material/Modal";
import Fade from "@mui/material/Fade";

import httpClient from "../../utils/axios";
import { useNavigate } from "react-router";

const modalStyle = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: "70%",
  bgcolor: "background.paper",
  borderRadius: "10px",
  boxShadow: 20,
};

const btnStyle = css`
  line-height: 5vh;
  width: 100%;
  border-style: solid;
  border-width: 0 0 1px 0;
  border-color: rgba(168, 168, 168, 0.5);
  text-align: center;
`;

const CardOptionModal = (props) => {
  const open = props.open;
  const handleClose = () => props.setOpen(false);

  const navigate = useNavigate();

  function moveToModify() {
    console.log("modify btn clicked");
    navigate(`/modify/${props.articleId}`);
  }

  function deleteArticle(event) {
    if (window.confirm("Delete ?")) {
      console.log("Delete this article : " + props.articleId);
      httpClient.delete(`boards/board/${props.articleId}`)
        .then((res) => {
          alert(props.articleId + " has been deleted");
        })
        .catch((error) => {
          console.log(error);
        });
      props.setOpen(false);
    }
  }

  return (
    <Box>
      <Modal
        aria-labelledby="transition-modal-title"
        aria-describedby="transition-modal-description"
        open={open}
        onClose={handleClose}
        closeAfterTransition
      >
        <Fade in={open}>
          <Box sx={modalStyle}>
            <Box css={btnStyle} onClick={moveToModify}>
              수정
            </Box>
            <Box css={btnStyle} onClick={deleteArticle} sx={{ color: "red" }}>
              삭제
            </Box>
            <Box css={btnStyle} onClick={handleClose}>
              취소
            </Box>
          </Box>
        </Fade>
      </Modal>
    </Box>
  );
};

export default CardOptionModal;
