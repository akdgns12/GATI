import React, { useEffect } from "react";
import {
  Grid,
  Typography,
  TextField,
  Button,
  Divider,
  Box,
  Container,
} from "@mui/material";
import { useDispatch, useSelector } from "react-redux";
import { FilePond } from "react-filepond";
import { useState } from "react";
import httpClient from "../../utils/axios";
import FamilyMemberItem from "./FamilyMemberItem";
import { updateMainGroup } from "../../store/User/user";

export default function FamilyInfo() {
  const { mainGroup, loginUser } = useSelector((state) => state.user);
  const [file, setFile] = useState(null);
  const [loaded, setLoaded] = useState(false);
  const [memberList, setMemberList] = useState([]);
  const dispatch = useDispatch();

  useEffect(() => {
    // console.log("LOAD FAMILY MEMBERS");
    setMemberList([]);
    //   newList.push({
    //     userId: `user${i}`,
    //     userBD: "YYYY-MM-DD",
    //     phoneNumber: "010-XXXX-XXXX",
    //   });
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
    const reqData = {
      id: mainGroup.id,
      img: "string",
      name: event.target.familyName.value,
    };
    // console.log(reqData);
    httpClient
      .put("/family", reqData)
      .then(({ data }) => {
        // console.log(data);
        alert("정보 수정이 완료되었습니다");
        // update main group info here
        const modifiedData = {
          name: reqData.name,
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
    httpClient.put("/family/", reqData).then(({ data }) => {
      if (data.msg === "success") {
        window.alert("초대 메세지가 성공적으로 전송되었습니다.");
        event.target.memberId.value = "";
      } else {
        window.alert("등록되지 않은 사용자 입니다. ID를 확인해 주세요.");
      }
    });
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
        <Grid item xs={6}>
          <FilePond
            stylePanelLayout={"compact circle"}
            files={file}
            onupdatefiles={setFile}
            allowMultiple={false}
          />
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
