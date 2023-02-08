import { React, useState } from "react";
import { useNavigate, useParams } from "react-router";
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import { Box, Button, FormControl, Input, OutlinedInput } from "@mui/material";

import httpClient from "../../utils/axios";
import { useDispatch, useSelector } from "react-redux";
import { useEffect } from "react";
import { loadPostDetail } from "../../store/Board/board";

const contStyle = css`
  text-align: left;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  margin: 30px 15px;
  .photo {
    margin: 10px 0px;
    .photo-label {
      padding-bottom: 10px;
    }
    .photo-box {
      background-color: #dddddd;
      width: 200px;
      height: 200px;
      border-radius: 10px;
      text-align: center;
      display: inline-flex;
      // display: flex;
      .photo-prev {
        max-width: 100%;
        max-height: 100%;
        margin: auto;
      }
    }
    .edit-btn {
      display: inline-block;
      vertical-align: bottom;
    }
  }
  .input-label {
    width: 100%;
    border-style: solid;
    border-width: 0 0 1px 0;
    padding-bottom: 10px;
  }
  .text-input {
    border-radius: 0 0 10px 10px;
    flex-direction: column;
    margin-bottom: 20px;
  }
  .button-group {
    width: 100%;
    display: flex;
    flex-flow: row nowrap;
    justify-content: center;
    > * {
      margin: 10px;
    }
  }
`;

const CreatePost = (props) => {
  const variant = (props.variant == null) ? "create" : props.variant;
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const [imgURL, setImgURL] = useState(null);
  const [loaded, setLoaded] = useState(false);

  const articleId = useParams().articleId;
  const { article } = useSelector((state) => state.board);
  const { loginUser } = useSelector((state) => state.user);

  useEffect(() => {
    console.log("mounted");
    if (variant === "modify") {
      const reqData = {
        articleId: articleId,
        userId: loginUser.userId,
      };
      dispatch(loadPostDetail(reqData))
        .then((data) => {
          // console.log(data);
          console.log(data.payload.img);
          // setImgURL(URL.createObjectURL(event.target.files[0]));
          setImgURL("https://picsum.photos/400/300");
          setLoaded(true);
        })
        .catch((error) => {
          console.log(error);
        })
    }
  }, [])

  function handleSubmit(event) {
    event.preventDefault();
    console.log("submit form");
    console.log(event.target.img.files[0].name);
    console.log(event.target.content.value);
    console.log(event.target.tag.value);
    const postData = {
      content: event.target.content.value,
      tagDtos: [{ tagContent: event.target.tag.value },],
      img: event.target.img.files[0].name,
      groupId: 1,
    }
    httpClient.post("/boards/board/", postData)
      .then((data) => {
        console.log(data)
        alert("article posted");
        navigate("/");
      })
      .catch((error) => {
        console.log(error)
        alert("failed to post article");
      });
  }

  function backToMain() {
    if (window.confirm("cancel ?")) {
      // should be fixed
      navigate(-1);
    }
  }

  function handleIMGChange(event) {
    console.log(event.target.files[0]);
    setImgURL(URL.createObjectURL(event.target.files[0]));
  }

  return (
    <Box component="form" css={contStyle} onSubmit={handleSubmit}>
      <Box className="photo">
        <Box className="photo-label">사진 선택</Box>
        <Box className="photo-box">
          <Box className="photo-prev" component="img" src={imgURL} alt="please select photo" />
        </Box>
        <label htmlFor="select-img">
          <Button className="edit-btn" component="span">
            edit
          </Button>
        </label>
        <Input
          inputProps={{ accept: "image/*" }}
          type="file"
          // ref={fileInput}
          id="select-img"
          name="img"
          style={{ display: "none" }}
          onChange={handleIMGChange}
        />
      </Box>
      <FormControl variant="standard" style={{ width: "100%" }}>
        <Box className="input-label">문구 입력</Box>
        <OutlinedInput
          className="text-input"
          placeholder="사진과 함께 기억하고 싶은 추억들을 이 곳에 기록하세요."
          multiline={true}
          name="content"
          style={{ height: "150px" }}
          value={(variant === "modify" && loaded) ? article.content : ""}
        />
      </FormControl>
      <FormControl variant="standard" style={{ width: "100%" }}>
        <Box className="input-label">태그</Box>
        <OutlinedInput
          className="text-input"
          placeholder="사진의 태그를 입력하세요."
          multiline={true}
          name="tag"
          value={(variant === "modify" && loaded) ? "some tag" : ""}
        />
      </FormControl>
      <Box className="button-group">
        <Button type="submit" variant="contained">
          확인
        </Button>
        <Button variant="outlined" onClick={backToMain}>
          취소
        </Button>
      </Box>
    </Box>
  );
};

export default CreatePost;
