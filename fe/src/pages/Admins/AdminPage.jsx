import React, { useState } from "react";

import { useNavigate, Link } from "react-router-dom";
import { Container, Grid, TextField } from "@mui/material";

// 이미지 업로더 관련
import { FilePond, registerPlugin } from "react-filepond";
import "filepond/dist/filepond.min.css";
import FilePondPluginImageExifOrientation from "filepond-plugin-image-exif-orientation";
import FilePondPluginImagePreview from "filepond-plugin-image-preview";
import "filepond-plugin-image-preview/dist/filepond-plugin-image-preview.css";
// mui
import Button from "@mui/material/Button";
import ButtonGroup from "@mui/material/ButtonGroup";
import { useEffect } from "react";

import httpClient from "../../utils/axios";
import MissionItem from "./MissionItem";

registerPlugin(FilePondPluginImageExifOrientation, FilePondPluginImagePreview);

export default function AdminPage() {
  // 관리자 페이지
  const [loaded, setLoaded] = useState(false);
  const [missionList, setMissionList] = useState([]);
  const navigate = useNavigate();
  // const today = new Date();
  // console.log(today.getFullYear());
  // console.log(today.getMonth());
  // console.log(today.getDate());
  // console.log(today.getDay());

  // var missionList = new Array(5).fill();

  useEffect(() => {
    httpClient
      .get("/adminMissions/list")
      .then((res) => {
        // console.log(res);
        if (Array.isArray(res.data)) {
          // console.log(res.data);
          for (let i = 0; i < res.data.length; i++) {
            // console.log(res.data[i]);
            setMissionList([...missionList, res.data[i]]);
            console.log(missionList);
          }
          setLoaded(true);
        } else console.log("Failed to LOAD data");
      })
      .catch((error) => console.log(error));
  }, []);

  function goCreate() {
    navigate("/admin/create");
  }

  return (
    <Container container="true" sx={{ height: "100vh" }} fixed maxWidth="md">
      <h2>관리자 페이지</h2>
      <Button onClick={goCreate} variant="outlined">
        작성
      </Button>
      <Grid container sx={{ border: 1, display: "flex", justifyContent: "center" }}>
        {loaded &&
          missionList.length > 0 &&
          missionList.map((mission, index) => {
            console.log(index);
            return <MissionItem mskey={index} mission={mission} />;
          })}
      </Grid>
    </Container>
  );
}
