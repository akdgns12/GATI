import React from "react";

import { Grid, Typography, Button, Avatar, Box } from "@mui/material";
import FamilyInfo from "./FamilyInfo";
import { useSelector } from "react-redux";
export default function FamilyCreate(props) {
  const setOpen = props.setOpen;
  const { mainGroup } = useSelector((state) => state.user);
  // console.log(mainGroup);
  // console.log(mainGroup.id == null);

  return (
    <Grid>
      {mainGroup.id == null ? (
        <>
          <Grid item m={1} xs={10}>
            <Typography>아직 속한 그룹이 없습니다.</Typography>
          </Grid>
          <Grid item m={1} xs={8}>
            <Button variant="contained" onClick={() => setOpen(true)}>
              가족 그룹 만들기
            </Button>
          </Grid>
        </>
      ) : (
        <FamilyInfo />
      )}
    </Grid>
  );
}
