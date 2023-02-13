import React from "react";
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import { Box, Button } from "@mui/material";
import { useState } from "react";
import { useEffect } from "react";
import { useSelector } from "react-redux";
import httpClient from "../../utils/axios";

import FamilyItem from "./FamilyItem";

const contStyle = css`
  width: 100%;
  .list-header {
    width: 100%;
    display: flex;
    justify-content: space-between;
    .text-box {
      text-align: left;
    }
    .create-btn {
      width: 30%;
    }
  }
`;

export default function Family(props) {
  const setOpen = props.setOpen;
  const { userId } = useSelector((state) => state.user.loginUser);
  const [groupList, setGroupList] = useState([]);
  const [loaded, setLoaded] = useState(false);

  useEffect(() => {
    const userId_ = "akdgns12";
    httpClient
      .get(`/family/list/${userId}`)
      .then(({ data }) => {
        console.log(data);
        setGroupList(data.familyList);
      })
      .catch((error) => console.log(error));
  }, []);

  useEffect(() => {
    if (groupList != null && groupList.length > 0) {
      console.log(groupList);
      setLoaded(true);
    }
  }, [groupList]);

  return (
    <Box css={contStyle}>
      <Box className="list-header">
        <Box className="text-box">메인 그룹 설정</Box>
        <Button
          variant="outlined"
          onClick={() => setOpen(true)}
          className="create-btn"
        >
          <span
            style={{
              width: "100%",
              whiteSpace: "nowrap",
              overflow: "hidden",
              textOverflow: "ellipsis",
            }}
          >
            그룹 만들기
          </span>
        </Button>
      </Box>
      {loaded &&
        groupList.length > 0 &&
        groupList.map((group, index) => {
          return <FamilyItem key={index} group={group} />;
        })}
    </Box>
  );
}
