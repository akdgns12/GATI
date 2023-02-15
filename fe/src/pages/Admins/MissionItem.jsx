import { Button, ButtonGroup, Grid } from "@mui/material";
import React, { useState } from "react";
import { useNavigate } from "react-router";

const MissionItem = (props) => {
  const navigate = useNavigate();
  // console.log(props);
  const idx = props.mskey;
  const mission = props.mission;

  // console.log(idx);
  // console.log(mission);

  const status = idx === 0 ? "진행중" : idx === 1 ? "다음주" : idx + 1 + "주 후";
  const titleContent = mission == null ? "" : mission.title;
  const descContent = mission == null ? "" : mission.content;

  function goEdit() {
    navigate("/admin/edit");
  }

  return (
    <>
      <Grid
        xs={11}
        item
        style={{ borderRadius: "5px", backgroundColor: "#D9D9D9", height: "20%", margin: "10px" }}
      >
        <h3>{status}</h3>
        <h4>TITLE: {titleContent}</h4>
        <h5>설명란: {descContent}</h5>
        <ButtonGroup variant="outlined">
          <Button onClick={goEdit}>수정</Button>
          <Button>삭제</Button>
        </ButtonGroup>
      </Grid>
    </>
  );
};

export default MissionItem;
