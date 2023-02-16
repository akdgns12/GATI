import { React } from "react";
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";

import { Box, OutlinedInput, IconButton } from "@mui/material";
import ArrowCircleUpIcon from "@mui/icons-material/ArrowCircleUp";

import httpClient from "../../utils/axios";
import { useDispatch, useSelector } from "react-redux";
import { updateComment } from "../../store/Board/board";

const contStyle = css`
  width: 100%;
  // margin-left: 5%;
  // align-item: center;
  .comment-area {
    width: 80%;
    height: 40px;
    font-size: meduim;
    background-color: #ededed;
  }
  .submit-btn {
    width: 15%;
  }
`;

const CommentInput = (props) => {
  const { loginUser } = useSelector((state) => state.user);
  const dispatch = useDispatch();

  function writeComment(event) {
    event.preventDefault();
    // console.log("write comment");
    // console.log(props.articleId);
    // console.log(event.target.comment.value);
    // console.log(loginUser.userId);
    httpClient
      .post("/boards/comment/", {
        boardId: props.articleId,
        content: event.target.comment.value,
        userId: loginUser.userId,
      })
      .then(({ data }) => {
        // console.log(data);
        dispatch(updateComment({ userId: loginUser.userId, content: event.target.comment.value }));
        window.alert("댓글이 작성되었습니다.");
        event.target.comment.value = "";
      })
      .catch((error) => {
        console.log(error);
      });
  }

  return (
    <Box component="form" onSubmit={writeComment} css={contStyle}>
      <Box
        style={{
          fontWeight: "bold",
          textAlign: "left",
          marginBottom: "8px",
        }}
      >
        {" "}
        Comments{" "}
      </Box>
      <OutlinedInput className="comment-area" placeholder="댓글을 입력하세요." name="comment" />

      <IconButton type="submit" className="submit-btn">
        <ArrowCircleUpIcon color="primary" fontSize="large" />
      </IconButton>
    </Box>
  );
};

export default CommentInput;
