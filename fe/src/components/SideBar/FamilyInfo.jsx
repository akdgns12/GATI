import React, { useEffect } from "react";
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import {
  Grid,
  Typography,
  TextField,
  Button,
  Divider,
  Box,
  Container,
  Input,
} from "@mui/material";
import { useDispatch, useSelector } from "react-redux";
import { useState } from "react";
import httpClient from "../../utils/axios";
import FamilyMemberItem from "./FamilyMemberItem";
import { updateMainGroup } from "../../store/User/user";

const imgBoxStyle = css`
  width: 100%;
  // display: flex;
  justify-content: center;
  .photo-box {
    background-color: #dddddd;
    width: 150px;
    height: 150px;
    // padding-bottom: 80%;
    border-radius: 50%;
    border-style: solid;
    border-width: 1px;
    border-color: rgba(128,128,128, 0.5);
    text-align: center;
    display: inline-flex;
    overflow: hidden;
    .photo-prev {
      background-color: red;
      max-width: 100%;
      max-height: 100%;
      margin: auto;
    }
  }
  .edit-btn {
    // display: inline-block;
    vertical-align: bottom;
  }
  .
`;

export default function FamilyInfo() {
  const { mainGroup, loginUser } = useSelector((state) => state.user);
  const [loaded, setLoaded] = useState(false);
  const [memberList, setMemberList] = useState([]);
  const [imgURL, setImgURL] = useState(
    process.env.REACT_APP_IMG_ROOT + "/" + mainGroup.img
  );
  // const [imgURL, setImgURL] = useState(
  //   "https://i8a805.p.ssafy.io/image_dev/board/083100211_kakao_login_large_narrow.png"
  // );
  const dispatch = useDispatch();

  // console.log(imgURL);
  useEffect(() => {
    // console.log("LOAD FAMILY MEMBERS");
    setMemberList([]);
    httpClient
      .get(`/family/memberList/${mainGroup.id}`)
      .then(({ data }) => {
        // console.log(data);
        if (data.msg === "success") {
          setMemberList(data["Info result"]);
          setLoaded(true);
        }
      })
      .catch((error) => console.log(error));
    // setLoaded(true);
  }, []);

  function handleModify(event) {
    event.preventDefault();
    // console.log("MODIFY");
    // const reqData = {
    //   id: mainGroup.id,
    //   img: "string",
    //   name: event.target.familyName.value,
    // };
    const formData = new FormData();
    formData.append("id", mainGroup.id);
    formData.append("name", event.target.familyName.value);
    formData.append(
      "multipartFile",
      event.target.img.files[0],
      event.target.img.files[0].name
    );

    httpClient
      .put("/family", formData)
      .then(({ data }) => {
        // console.log(data);
        alert("정보 수정이 완료되었습니다");
        // update main group info here
        // console.log(data["modifedFamily: {}"]);
        const modifiedData = {
          name: event.target.familyName.value,
          img: data["modifedFamily: {}"].img,
        };
        dispatch(updateMainGroup(modifiedData));
      })
      .catch((error) => console.log(error));
  }

  function handleInvitation(event) {
    event.preventDefault();
    // console.log(event.target.memberId.value);
    const reqData = {
      familyId: mainGroup.id,
      groupName: mainGroup.name,
      nickName: loginUser.nickName,
      receiverId: event.target.memberId.value,
    };
    // console.log(reqData);
    httpClient.post("/family/", reqData).then(({ data }) => {
      if (data.msg === "success") {
        window.alert("초대 메세지가 성공적으로 전송되었습니다.");
        event.target.memberId.value = "";
      } else {
        window.alert("등록되지 않은 사용자 입니다. ID를 확인해 주세요.");
      }
    });
  }

  function handleIMGChange(event) {
    // console.log(event.target.files[0]);
    setImgURL(URL.createObjectURL(event.target.files[0]));
  }

  return (
    <Grid
      container
      justifyContent="center"
      alignItems="center"
      spacing={0}
      display="flex"
    >
      <Grid item m={1} xs={12}>
        <Typography>가족 프로필 설정</Typography>
      </Grid>
      <Grid
        container
        component="form"
        onSubmit={handleModify}
        justifyContent="center"
        id="modify-form"
      >
        <Grid item xs={12} p={1}>
          <Box css={imgBoxStyle}>
            <Box className="photo-box">
              <Box
                className="photo-prev"
                component="img"
                src={imgURL}
                alt="please select photo"
              />
            </Box>
            <label htmlFor="select-img">
              <Button className="edit-btn" component="span">
                edit
              </Button>
            </label>
            <Input
              inputProps={{ accept: "image/*" }}
              type="file"
              id="select-img"
              name="img"
              style={{ display: "none" }}
              onChange={handleIMGChange}
            />
          </Box>
        </Grid>
        <Grid item xs={12}>
          <TextField
            size="small"
            fullWidth
            label="family name"
            name="familyName"
            defaultValue={mainGroup.name}
          />
        </Grid>
        <Button type="submit" hidden={true} />
      </Grid>
      <Grid item xs={12}>
        <Typography>가족 구성원</Typography>
        {loaded === true &&
          memberList.length > 0 &&
          memberList.map((member, index) => {
            return <FamilyMemberItem member={member} key={index} />;
          })}
      </Grid>
      <Grid m={1} />
      <Grid item xs={12} display="flex">
        <Typography>가족 구성원 초대</Typography>
      </Grid>
      <Grid
        container
        component="form"
        onSubmit={handleInvitation}
        justifyContent="center"
      >
        <TextField size="small" fullWidth label="member-id" name="memberId" />
        <Grid item xs={6} m={1}>
          <Button fullWidth variant="outlined" type="submit">
            send
          </Button>
        </Grid>
      </Grid>

      <Divider />
      <br />
      <Grid item xs={6}>
        <Button fullWidth variant="contained" type="submit" form="modify-form">
          save
        </Button>
      </Grid>
    </Grid>
  );
}
