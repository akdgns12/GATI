import React from "react";
import { Grid, TextField, Button } from "@mui/material";
import { useSelector } from "react-redux";
import { useState } from "react";
import { useEffect } from "react";
import httpClient from "../../utils/axios";

export default function MyInfochange() {
  const { userId } = useSelector((state) => state.user.loginUser);
  const [userInfo, setUserInfo] = useState();
  const [loaded, setLoaded] = useState(false);

  useEffect(() => {
    httpClient
      .get(`/user/id/${userId}`)
      .then(({ data }) => {
        // console.log(data);
        if (data.msg === "success") {
          const rxInfo = data.userInfo;
          // console.log(rxInfo);
          // console.log({ ...userInfo, ...rxInfo });
          setUserInfo(rxInfo);
          // console.log(userInfo);
        }
      })
      .catch((error) => console.log(error));
  }, []);

  useEffect(() => {
    // console.log("data has been modified");
    console.log(userInfo);
    if (userInfo != undefined) setLoaded(true);
  }, [userInfo]);

  function handleSubmit(event) {
    event.preventDefault();
    console.log(event.target.nickname.value);
    console.log(event.target.email.value);
    console.log(event.target.phoneNumber.value);
    console.log(event.target.birth.value);
  }
  return (
    <Grid component="form" onSubmit={handleSubmit}>
      {loaded == true && (
        <>
          <Grid p={1} display="flex" justifyContent="center">
            <TextField
              label="닉네임"
              variant="outlined"
              name="nickname"
              defaultValue={userInfo.username}
            />
          </Grid>
          <Grid p={1} display="flex" justifyContent="center">
            <TextField
              label="이메일"
              variant="outlined"
              name="email"
              defaultValue={userInfo.email}
            />
          </Grid>
          <Grid p={1} display="flex" justifyContent="center">
            <TextField
              label="휴대폰 번호"
              variant="outlined"
              name="phoneNumber"
              defaultValue={userInfo.phoneNumber}
            />
          </Grid>
          <Grid p={1} display="flex" justifyContent="center">
            <TextField
              label="생년월일 yyyy-mm-dd"
              variant="outlined"
              name="birth"
              defaultValue={userInfo.birth}
            />
          </Grid>
          <Grid p={1} display="flex" justifyContent="center">
            <Button variant="outlined" type="submit">
              수정
            </Button>
          </Grid>
        </>
      )}
    </Grid>
  );
}
