import * as React from "react";
import Card from "@mui/material/Card";
import CardHeader from "@mui/material/CardHeader";
import CardActions from "@mui/material/CardActions";
import Typography from "@mui/material/Typography";
import { Button } from "@mui/material";

import httpClient from "../../utils/axios";
import { useSelector } from "react-redux";
import { useState } from "react";

export default function GroupInvitation(props) {
  const { loginUser } = useSelector((state) => state.user);
  const [checked, setChecked] = useState(false);
  const invit = props.invitation;
  const variant = props.variant == null ? "main" : props.variant;
  var invitMessage =
    invit.nickname + " 님이 당신을 " + invit.groupName + " 그룹으로 초대하였습니다.";

  function acceptInvit(event) {
    event.preventDefault();
    // console.log("ACCEPT INVITATION");
    const reqData = {
      familyId: invit.groupId,
      groupName: invit.groupName,
      nickName: invit.nickname,
      receiverId: loginUser.userId,
    };
    // console.log(reqData);
    httpClient
      .post("/family/inviteAccpet", reqData)
      .then((res) => {
        // console.log(res);
        setChecked(true);
      })
      .catch((error) => {
        console.log(error);
        setChecked(true);
      });
  }

  function rejectInvit(event) {
    event.preventDefault();
    // console.log(invit);
    const reqData = {
      familyId: invit.groupId,
      userId: loginUser.userId,
    };
    // console.log(reqData);
    httpClient
      .delete("/family/inviteReject", { data: reqData })
      .then((res) => {
        // console.log(res);
        setChecked(true);
      })
      .catch((error) => {
        console.log(error);
        setChecked(true);
      });
  }

  if (!checked)
    return (
      <Card sx={{ maxWidth: "100%" }} style={{ marginBottom: "10px" }}>
        <CardHeader
          title={invitMessage}
          titleTypographyProps={{ variant: variant === "main" ? "body1" : "body2" }}
          style={{
            textAlign: "left",
            borderWidth: "0 0 thin 0",
            borderStyle: "solid",
            borderColor: "#c8c8c8",
          }}
        />

        <CardActions disableSpacing={true}>
          <Typography variant={variant == "main" ? "body1" : "body2"}>
            {invit.createTime != null ? invit.createTime.split("T")[0] : ""}
          </Typography>
          <div style={{ marginLeft: "auto" }}>
            <Button
              variant="outlined"
              size="small"
              onClick={rejectInvit}
              style={{ marginRight: "5px" }}
            >
              거절
            </Button>
            <Button
              variant="contained"
              size="small"
              onClick={acceptInvit}
              style={{ marginRight: "5px" }}
            >
              수락
            </Button>
          </div>
        </CardActions>
      </Card>
    );
}
