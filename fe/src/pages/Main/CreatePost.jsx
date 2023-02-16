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
      overflow: hidden;
      .photo-prev {
        max-width: 100%;
        max-height: 100%;
        margin: auto;
      }
    }
    .edit-btn {
      display: inline-block;
      vertical-align: bottom;
      color: #ff9494;
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
  const variant = props.variant == null ? "create" : props.variant;
  const API_URL = props.api == null ? "/boards/board" : "/albums/album";
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const [imgURL, setImgURL] = useState(null);
  const [loaded, setLoaded] = useState(false);
  const [serializedTag, setSerializedTag] = useState("");
  const [isIMG, setIsIMG] = useState(false);
  const [isContent, setIsContent] = useState(false);
  const [isTag, setIsTag] = useState(false);

  const articleId = useParams().articleId;
  const { article } = useSelector((state) => state.board);
  const { loginUser, mainGroup } = useSelector((state) => state.user);

  useEffect(() => {
    if (variant === "modify") {
      const reqData = {
        articleId: articleId,
        userId: loginUser.userId,
      };
      dispatch(loadPostDetail(reqData))
        .then((data) => {
          // console.log(data);
          // setImgURL(URL.createObjectURL(event.target.files[0]));
          var imgPath = process.env.REACT_APP_IMG_ROOT + "/" + data.payload.img;
          setImgURL(imgPath);
          setSerializedTag(serializeTag(data.payload.tag));
          setLoaded(true);
        })
        .catch((error) => {
          console.log(error);
        });
    }
  }, []);

  function serializeTag(tagObjs) {
    // console.log(tagObjs);
    var str = "";
    if (tagObjs != null) {
      tagObjs.map((tagObj) => {
        str += tagObj.tagContent;
        str += " ";
      });
    }
    // console.log(str);
    return str;
  }

  function handleSubmit(event) {
    event.preventDefault();

    const tagArr = parseTags(event.target.tag.value);
    const formData = new FormData();
    formData.append("content", event.target.content.value);

    for (let i = 0; i < tagArr.length; i++) {
      formData.append(`tagDtos[${i}].tagContent`, tagArr[i]);
    }

    if (event.target.img.files[0] != undefined) {
      formData.append("file", event.target.img.files[0], event.target.img.files[0].name);
    }

    if (variant === "create") {
      formData.append("userId", loginUser.userId);
      formData.append("groupId", mainGroup.id);
      // console.log(formData);
      httpClient
        .post(API_URL, formData)
        .then((data) => {
          alert("게시물이 등록되었습니다.");
          navigate(props.api == null ? "/" : "/photobook");
        })
        .catch((error) => {
          console.log(error);
          alert("게시물 작성에 실패하였습니다.\n다시 시도해 주세요.");
        });
    } else if (variant === "modify") {
      formData.append("id", articleId);
      httpClient
        .put(API_URL, formData)
        .then((data) => {
          // console.log(data);
          alert("게시물이 수정되었습니다.");
          navigate(props.api == null ? "/" : "/photobook");
        })
        .catch((error) => {
          console.log(error);
          alert("게시물 수정에 실패하였습니다.\n다시 시도해 주세요.");
        });
    }
  }

  function parseTags(str) {
    // console.log(str);
    const tags = str.split(" ");
    let ret = [];
    tags.map((tag, index) => {
      // console.log(tag);
      if (tag != "") ret.push(tag);
    });
    return ret;
  }

  function backToMain() {
    if (window.confirm("작성한 데이터는 저장되지 않습니다.\n취소 하시겠습니까?")) {
      // should be fixed
      navigate(-1);
    }
  }

  function handleIMGChange(event) {
    if (event.target.files[0] != undefined) setImgURL(URL.createObjectURL(event.target.files[0]));
    setIsIMG(true);
  }

  function handleContentChange(event) {
    if (event.target.value === "") setIsContent(false);
    else setIsContent(true);
  }

  function handleTagChange(event) {
    if (event.target.value === "") setIsTag(false);
    else setIsTag(true);
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
          defaultValue={variant === "modify" && loaded ? article.content : ""}
          onChange={handleContentChange}
        />
      </FormControl>
      <FormControl variant="standard" style={{ width: "100%" }}>
        <Box className="input-label">태그</Box>
        <OutlinedInput
          className="text-input"
          placeholder="사진의 태그를 입력하세요."
          multiline={true}
          name="tag"
          defaultValue={variant === "modify" && loaded ? serializedTag : ""}
          onChange={handleTagChange}
        />
      </FormControl>
      <Box className="button-group">
        <Button
          type="submit"
          variant="text"
          style={{ color: "black" }}
          disabled={variant == "modify" || (isIMG && isContent && isTag) ? false : true}
        >
          확인
        </Button>
        <Button style={{ color: "#ff9494" }} variant="text" onClick={backToMain}>
          취소
        </Button>
      </Box>
    </Box>
  );
};

export default CreatePost;
