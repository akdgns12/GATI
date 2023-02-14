import React from "react";
import { Grid, TextField, Button } from "@mui/material";
import { useDispatch, useSelector } from "react-redux";
import { useState } from "react";
import { useEffect } from "react";
import httpClient from "../../utils/axios";
import { updateUserNickName } from "../../store/User/user";

export default function MyInfochange() {
  const { userId } = useSelector((state) => state.user.loginUser);
  const [userInfo, setUserInfo] = useState();
  const [loaded, setLoaded] = useState(false);
  const dispatch = useDispatch();

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
    const reqData = {
      birth: event.target.birth.value,
      email: event.target.email.value,
      nickName: event.target.nickName.value,
      phoneNumber: event.target.phoneNumber.value,
      userId: userId,
    };
    console.log(reqData);
    httpClient
      .put("/user/change/", reqData)
      .then((res) => {
        // console.log(res);
        window.alert("회원 정보가 수정되었습니다.");
        dispatch(updateUserNickName(event.target.nickName.value));
      })
      .catch((error) => console.log(error));
  }
  return (
    <Grid component="form" onSubmit={handleSubmit}>
      {loaded == true && (
        <>
          <Grid p={1} display="flex" justifyContent="center">
            <TextField
              label="닉네임"
              variant="outlined"
              name="nickName"
              defaultValue={userInfo.nickName}
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
