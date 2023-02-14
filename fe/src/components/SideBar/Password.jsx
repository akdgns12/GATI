import React from "react";
import { Grid, TextField, Button } from "@mui/material";
import { useState } from "react";
import { useSelector } from "react-redux";
import httpClient from "../../utils/axios";
import { doLogOut } from "../../utils/logOutUtil";
import { useNavigate } from "react-router";

export default function Password() {
  const [newPw, setNwePW] = useState("");
  const [confirmed, setConfirmed] = useState(false);
  const { userId } = useSelector((state) => state.user.loginUser);
  const navigate = useNavigate();

  function handleSubmit(event) {
    event.preventDefault();
    const reqData = {
      userId: userId,
      password: event.target.curPW.value,
      changePassword: event.target.newPW.value,
    };
    console.log(reqData);
    httpClient
      .put("/user/account/password/", reqData)
      .then(({ data }) => {
        console.log(data);
        if (data.msg === "success") {
          window.alert("비밀번호가 변경되었습니다.");
          doLogOut();
          navigate("/login");
        } else {
          alert("비밀번호가 일치하지 않습니다");
        }
      })
      .catch((error) => console.log(error));
  }

  function modifyPW(event) {
    setNwePW(event.target.value);
  }

  function checkPW(event) {
    if (event.target.value === newPw) setConfirmed(true);
    else setConfirmed(false);
  }
  return (
    <Grid component="form" onSubmit={handleSubmit}>
      <Grid>
        <Grid p={1} display="flex" justifyContent="center">
          <TextField
            required
            label="현재 비밀번호"
            variant="outlined"
            type="password"
            name="curPW"
          />
        </Grid>
        <Grid p={1} display="flex" justifyContent="center">
          <TextField
            required
            label="새 비밀번호"
            variant="outlined"
            type="password"
            name="newPW"
            onChange={modifyPW}
          />
        </Grid>
        <Grid p={1} display="flex" justifyContent="center">
          <TextField
            required
            helperText="*8~20자의 영문,숫자를 사용하세요"
            label="새 비밀번호 확인"
            variant="outlined"
            type="password"
            name="confirmPW"
            onChange={checkPW}
          />
        </Grid>
      </Grid>
      <Grid p={1} display="flex" justifyContent="center">
        <Button variant="outlined" type="submit" disabled={confirmed ? false : true}>
          저장
        </Button>
      </Grid>
    </Grid>
  );
}
