import { React, useState, useEffect } from "react";
import ReactDOM from "react-dom/client";
import { useDispatch, useSelector } from "react-redux";
import { loadMainFeed, updatePageNo } from "../../store/Board/board";
import RefreshIcon from "@mui/icons-material/Refresh";
import { Box } from "@mui/material";

import ArticleCard from "../../components/Main/ArticleCard";
import NotInGroup from "../../components/Main/NotInGroup";
import AddButton from "../../components/Main/AddButton";
import NoGroupAlertDialog from "../../components/Main/NoGroupAlert";
import GroupInvitation from "../../components/Notification/GroupInvitation";

import { clearFeed } from "../../store/Board/board";

const MainFeed = () => {
  const dispatch = useDispatch();
  const { loginUser } = useSelector((state) => state.user);
  const [target, setTarget] = useState(null);
  const [loaded, setLoaded] = useState(false);
  const { curPageNo } = useSelector((state) => state.board);

  // console.log(state);
  const { articles } = useSelector((state) => state.board);
  // const groupId = 11;
  const { mainGroup } = useSelector((state) => state.user);
  // console.log(mainGroup);
  // const groupId = null;
  const { notifications } = useSelector((state) => state.noti);

  const [show, setShow] = useState(true);

  useEffect(() => {
    // console.log("YOUR MAIN GROUP HAS BEEN MODIFIED");
    // console.log(curPageNo);
    dispatch(clearFeed());
    dispatch(
      loadMainFeed({
        groupId: mainGroup.id,
        userId: loginUser.userId,
        page: 0,
      })
    )
      .then((res) => {
        console.log(res);
        dispatch(updatePageNo(1));
      })
      .catch((error) => console.log(error));
  }, [mainGroup]);

  useEffect(() => {
    let iObserver;
    if (target) {
      iObserver = new IntersectionObserver(
        (entries) => onIntersect(entries, curPageNo),
        {
          threshold: 1.0,
        }
      );
      iObserver.observe(target);
    }
    return () => iObserver && iObserver.disconnect();
  }, [target, curPageNo]);

  useEffect(() => {
    if (articles != null && articles.length > 0) {
      console.log("articles loaded");
      setLoaded(true);
    } else {
      setLoaded(false);
      console.log("No content to load");
    }
  }, [articles]);

  // useEffect(() => {
  //   console.log("YOUR MAIN GROUP HAS BEEN MODIFIED");
  //   dispatch(updatePageNo(0));
  // }, [mainGroup]);

  async function onIntersect(entries, nextPageNo) {
    // console.log(entries);
    if (entries[0].isIntersecting) {
      setLoaded(false);
      console.log("LOAD MORE");
      dispatch(
        loadMainFeed({
          groupId: mainGroup.id,
          userId: loginUser.userId,
          page: nextPageNo,
        })
      )
        .then(({ payload }) => {
          // console.log(payload);
          if (payload.length > 1) {
            console.log("new feeds");
            dispatch(updatePageNo(nextPageNo + 1));
            setLoaded(true);
          }
        })
        .catch((error) => console.log(error));
      // console.log(articles);
    }
  }

  if (mainGroup != null && mainGroup != undefined && mainGroup.id != null) {
    return (
      <div>
        <AddButton mode="feed" />
        {articles != null &&
          articles.map((article, index) => {
            return (
              <ArticleCard
                key={index}
                article={article}
                style={{ "margin-top": "10px" }}
                mode="feed"
              />
            );
          })}
        {loaded && (
          <Box ref={setTarget} style={{ textAlign: "center" }}>
            <RefreshIcon />
          </Box>
        )}
      </div>
    );
  } else {
    return (
      <div>
        {notifications.invitations != null
          ? notifications.invitations.map((invitation, index) => {
              return <GroupInvitation key={index} invitation={invitation} />;
            })
          : null}
        <NoGroupAlertDialog show={show} onClose={() => setShow(false)} />
        <div style={{ "margin-top": "40%" }}>
          <NotInGroup />
        </div>
      </div>
    );
  }
};

export default MainFeed;
