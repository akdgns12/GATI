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
import { useDispatch, useSelector } from "react-redux";
import { updateMainGroup } from "../../store/User/user";

const contStyle = css`
  width: 100%;
  .MuiListItemText-root {
    text-color: red;
  }
`;

const FamilyItem = (props) => {
  const groupName = props.group.name;
  const { defaultGroup, mainGroup } = useSelector((state) => state.user);
  const dispatch = useDispatch();

  function setMainGroup(event) {
    event.preventDefault();
  }

  function mvToGroup(event) {
    event.preventDefault();
    dispatch(updateMainGroup(props.group));
    props.setSideOpen(false);
    // do sth about main group
  }

  return (
    <ListItem
      secondaryAction={
        <Button
          variant="contained"
          onClick={setMainGroup}
          style={{ width: "20%" }}
          disabled={props.group.id === defaultGroup.id ? true : false}
        >
          Main
        </Button>
      }
      disablePadding={true}
      css={contStyle}
    >
      <ListItemButton
        className="list-btn"
        onClick={mvToGroup}
        disableGutters
        disabled={mainGroup.id === props.group.id ? true : false}
      >
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
