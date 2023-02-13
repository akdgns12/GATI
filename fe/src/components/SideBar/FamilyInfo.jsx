import React, { useEffect } from "react";
import { Grid, Typography, TextField, Button, Divider, Box } from "@mui/material";
import { useSelector } from "react-redux";
import { FilePond } from "react-filepond";
import { useState } from "react";
import httpClient from "../../utils/axios";

export default function FamilyInfo() {
  const { mainGroup } = useSelector((state) => state.user);
  const [file, setFile] = useState(null);
  const [loaded, setLoaded] = useState(false);
  const [memberList, setMemberList] = useState([]);

  useEffect(() => {
    console.log("LOAD FAMILY MEMBERS");
    setMemberList([]);
    // console.log(memberList);
    // dummy values
    for (var i = 0; i < 2; i++) {
      const newList = memberList;
      newList.push({
        userId: `user${i}`,
        userBD: "YYYY-MM-DD",
        phoneNumber: "010-XXXX-XXXX",
      });
      setMemberList(newList);
      // console.log(memberList);
    }
    setLoaded(true);
  }, []);

  function handleModify(event) {
    event.preventDefault();
    console.log("MODIFY");
    const reqData = {
      id: mainGroup.id,
      img: "string",
      name: event.target.familyName.value,
    };
    // console.log(reqData);
    httpClient
      .put("/family", reqData)
      .then(({ data }) => {
        console.log(data);
        alert("정보 수정이 완료되었습니다");
        // update main group info here
      })
      .catch((error) => console.log(error));
  }

  function handleInvitation(event) {
    event.preventDefault();
    console.log(event.target.memberId.value);
  }

  return (
    <Grid>
      <Box component="form" onSubmit={handleModify} id="modify-form">
        <Grid item m={1} xs={12} display="flex" justifyContent="start">
          <Typography>가족 프로필 설정</Typography>
        </Grid>
        <FilePond
          stylePanelLayout={"compact circle"}
          files={file}
          onupdatefiles={setFile}
          allowMultiple={false}
        />
        <Grid>
          <TextField
            size="small"
            fullWidth
            label="family name"
            name="familyName"
            defaultValue={mainGroup.name}
          />
        </Grid>
        <Button type="submit" hidden={true} />
      </Box>
      <Box>
        <Typography>가족 구성원</Typography>
        {loaded === true &&
          memberList.length > 0 &&
          memberList.map((member, index) => {
            // console.log(member);
            return <Box>{member.userId}</Box>;
          })}
      </Box>
      <Box>
        <Grid m={1} item xs={12} display="flex" justifyContent="start">
          <Typography>가족 구성원 초대</Typography>
        </Grid>
        <Grid component="form" onSubmit={handleInvitation}>
          <TextField size="small" fullWidth label="member-id" name="memberId" />
          <br />
          <Button fullWidth variant="outlined" type="submit">
            send
          </Button>
        </Grid>
      </Box>
      <Divider />
      <br />
      <Grid>
        <Button fullWidth variant="contained" type="submit" form="modify-form">
          save
        </Button>
      </Grid>
    </Grid>
  );
}
