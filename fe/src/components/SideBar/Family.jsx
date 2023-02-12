import { Box } from "@mui/material";
import React from "react";
import { useState } from "react";
import { useEffect } from "react";
import { useSelector } from "react-redux";
import httpClient from "../../utils/axios";

import FamilyItem from "./FamilyItem";

export default function Family() {
  const { userId } = useSelector((state) => state.user.loginUser);
  const [groupList, setGroupList] = useState([]);
  const [loaded, setLoaded] = useState(false);

  useEffect(() => {
    const userId_ = "akdgns12";
    httpClient
      .get(`/family/list/${userId_}`)
      .then(({ data }) => {
        console.log(data);
        setGroupList(data.familyList);
      })
      .catch((error) => console.log(error));
  }, []);

  useEffect(() => {
    if (groupList != null) {
      // console.log(groupList);
      setLoaded(true);
    }
  }, [groupList]);

  return (
    <Box>
      <div>Family</div>
      {loaded &&
        groupList.length > 0 &&
        groupList.map((group, index) => {
          return <FamilyItem key={index} group={group} />;
        })}
    </Box>
  );
}
