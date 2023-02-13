import { React } from "react";
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import {
  Avatar,
  Box,
  Button,
  ListItem,
  ListItemAvatar,
  ListItemButton,
  ListItemText,
} from "@mui/material";
import FolderIcon from "@mui/icons-material/Folder";
import { useSelector } from "react-redux";

const contStyle = css`
  width: 100%;
  .MuiListItemText-root {
    text-color: red;
  }
`;

const FamilyItem = (props) => {
  const groupName = props.group.name;
  const { userId } = useSelector((state) => state.user.loginUser);
  const { id } = useSelector((state) => state.user.mainGroup);

  function setMainGroup(event) {
    event.preventDefault();
  }

  function mvToGroup(event) {
    event.preventDefault();
    // do sth about main group
  }

  return (
    <ListItem
      secondaryAction={
        <Button
          variant="contained"
          onClick={setMainGroup}
          style={{ width: "20%" }}
          disabled={props.group.id === id ? true : false}
        >
          Main
        </Button>
      }
      disablePadding={true}
      css={contStyle}
    >
      <ListItemButton className="list-btn" onClick={mvToGroup} disableGutters>
        <ListItemAvatar>
          <Avatar className="group-img">
            <FolderIcon />
          </Avatar>
        </ListItemAvatar>
        <ListItemText
          primary={groupName}
          primaryTypographyProps={{
            style: {
              width: "70%",
              whiteSpace: "nowrap",
              overflow: "hidden",
              textOverflow: "ellipsis",
            },
          }}
        />
      </ListItemButton>
    </ListItem>
  );
};

export default FamilyItem;
