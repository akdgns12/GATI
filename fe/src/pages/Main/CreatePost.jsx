import { React, useState } from "react";
import { useNavigate } from "react-router";
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import { Box, Button, FormControl, Input, OutlinedInput } from "@mui/material";

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
    .photo-prev {
      background-color: #dddddd;
      width: 200px;
      height: 200px;
      border-radius: 10px;
      text-align: center;
      display: inline-block;
    }
    .edit-btn {
      display: inline-block;
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

const CreatePost = () => {
  const navigate = useNavigate();
  const [imgURL, setImgURL] = useState(null);

  function doSth() {
    console.log("submit form");
    alert("article posted");
    navigate("/");
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
    <Box component="form" css={contStyle} onSubmit={doSth}>
      <Box className="photo">
        <Box className="photo-label">사진 선택</Box>
        <Box className="photo-prev">
          <img src={imgURL} alt="Here comes a photo" width={"200px"}></img>
        </Box>
        <Input
          inputProps={{ accept: "image/*" }}
          type="file"
          // ref={fileInput}
          id="select-img"
          style={{ display: "none" }}
          onChange={handleIMGChange}
        />
        <label htmlFor="select-img">
          <Button className="edit-btn" component="span">
            edit
          </Button>
        </label>
      </Box>
      <FormControl variant="standard" style={{ width: "100%" }}>
        <Box className="input-label">문구 입력</Box>
        <OutlinedInput
          className="text-input"
          placeholder="사진과 함께 기억하고 싶은 추억들을 이 곳에 기록하세요."
          multiline={true}
          style={{ height: "150px" }}
        />
      </FormControl>
      <FormControl variant="standard" style={{ width: "100%" }}>
        <Box className="input-label">태그</Box>
        <OutlinedInput
          className="text-input"
          placeholder="사진의 태그를 입력하세요."
          multiline={true}
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
