import { React, useState, useEffect } from "react";
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

  const { articles } = useSelector((state) => state.board);
  const { mainGroup } = useSelector((state) => state.user);
  const { notifications } = useSelector((state) => state.noti);

  const [show, setShow] = useState(true);

  useEffect(() => {
    dispatch(clearFeed());
    // if (mainGroup != null && mainGroup.id != null) {
    dispatch(
      loadMainFeed({
        groupId: mainGroup.id,
        userId: loginUser.userId,
        page: 0,
      })
    )
      .then((res) => {
        dispatch(updatePageNo(1));
      })
      .catch((error) => console.log(error));
  }, [mainGroup]);

  useEffect(() => {
    let iObserver;
    if (target) {
      iObserver = new IntersectionObserver((entries) => onIntersect(entries, curPageNo), {
        threshold: 1.0,
      });
      iObserver.observe(target);
    }
    return () => iObserver && iObserver.disconnect();
  }, [target, curPageNo]);

  useEffect(() => {
    if (articles != null && articles.length > 0) {
      setLoaded(true);
    } else {
      setLoaded(false);
      console.log("No content to load");
    }
  }, [articles]);

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
          if (payload.length > 0) {
            console.log("new feeds");
            dispatch(updatePageNo(nextPageNo + 1));
            setLoaded(true);
          }
        })
        .catch((error) => console.log(error));
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
                style={{ marginTop: "10px" }}
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
        {notifications != null
          ? notifications.map((notification, index) => {
              // console.log(notification);
              if (notification.type === 1)
                return <GroupInvitation key={index} invitation={notification} />;
            })
          : null}
        <NoGroupAlertDialog show={show} onClose={() => setShow(false)} />
        <div style={{ marginTop: "40%" }}>
          <NotInGroup />
        </div>
      </div>
    );
  }
};

export default MainFeed;
