import * as React from "react";
import Card from "@mui/material/Card";
import CardHeader from "@mui/material/CardHeader";
import CardMedia from "@mui/material/CardMedia";
import CardContent from "@mui/material/CardContent";
import CardActions from "@mui/material/CardActions";
import IconButton from "@mui/material/IconButton";
import Typography from "@mui/material/Typography";
import FavoriteIcon from "@mui/icons-material/Favorite";
import BookmarkBorderIcon from "@mui/icons-material/BookmarkBorder";
import MoreHorizIcon from "@mui/icons-material/MoreHoriz";
import { Button } from "@mui/material";

export default function GroupInvitation(props) {
  const invit = props.invitation;
  var invitMessage = invit.hostId + " 님이 당신을 " + invit.groupName + " 그룹으로 초대하였습니다.";

  function doSth() {
    console.log("sth happens");
  }

  return (
    <Card sx={{ maxWidth: "100%" }} style={{ marginBottom: "10px" }}>
      <CardHeader
        title={invitMessage}
        titleTypographyProps={{ variant: "body1" }}
        style={{
          textAlign: "left",
          borderWidth: "0 0 thin 0",
          borderStyle: "solid",
          borderColor: "#c8c8c8",
        }}
      />

      <CardActions disableSpacing={true}>
        <Typography>{invit.date}</Typography>
        <div style={{ marginLeft: "auto" }}>
          <Button variant="outlined" size="small" onClick={doSth} style={{ marginRight: "5px" }}>
            거절
          </Button>
          <Button variant="contained" size="small" onClick={doSth} style={{ marginRight: "5px" }}>
            수락
          </Button>
        </div>
      </CardActions>
    </Card>
  );
}
