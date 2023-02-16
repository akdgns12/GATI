import * as React from "react";
import Card from "@mui/material/Card";
import CardHeader from "@mui/material/CardHeader";
import CardActions from "@mui/material/CardActions";
import Typography from "@mui/material/Typography";
import { Button } from "@mui/material";

import httpClient from "../../utils/axios";
import { useSelector } from "react-redux";
import { useState } from "react";

export default function OtherNotification(props) {
  const { loginUser } = useSelector((state) => state.user);
  const [checked, setChecked] = useState(false);

  const noti = props.noti;
  var notiMsg;
  if (noti == null || noti.type == undefined) notiMsg = "";
  else if (noti.type === 2) notiMsg = `${noti.nickname} 님이 회원님의 게시글에 댓글을 남겼습니다`;
  else if (noti.type === 3) notiMsg = `${noti.nickname} 님이 회원님의 사진을 좋아합니다`;
  else if (noti.type === 4) notiMsg = `${noti.groupName}에서 이번주 미션이 시작외었어요!`;
  else if (noti.type === 5) notiMsg = `${noti.groupName}의 이번주 미션이 완료되었습니다!`;
  else notiMsg = "";

  if (!checked)
    return (
      <Card sx={{ maxWidth: "100%" }} style={{ marginBottom: "10px" }}>
        <CardHeader
          title={notiMsg}
          titleTypographyProps={{ variant: "body2" }}
          style={{
            textAlign: "left",
            borderWidth: "0 0 thin 0",
            borderStyle: "solid",
            borderColor: "#c8c8c8",
          }}
        />

        <CardActions disableSpacing={true}>
          <Typography variant="body2">
            {noti.createTime != null ? noti.createTime.split("T")[0] : ""}
          </Typography>
          <div style={{ marginLeft: "auto" }}></div>
        </CardActions>
      </Card>
    );
}
