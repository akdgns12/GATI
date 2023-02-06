import { React, useState, useEffect } from "react";
import ReactDOM from "react-dom/client";
import { useDispatch, useSelector } from "react-redux";
import { loadMainFeed } from "../../store/Board/board";

import ArticleCard from "../../components/Main/ArticleCard";
import NotInGroup from "../../components/Main/NotInGroup";
import AddButton from "../../components/Main/AddButton";
import NoGroupAlertDialog from "../../components/Main/NoGroupAlert";
import GroupInvitation from "../../components/Notification/GroupInvitation";

const MainFeed = () => {

  const dispatch = useDispatch();

  useEffect(() => {
    console.log("main feed mounted");
    dispatch(loadMainFeed(1))
      .then((data) => {
        console.log("data loaded")
        console.log(data.payload);
      })
      .catch((error) => console.log(error));
    return () => {
      console.log("main feed umnounted");
    }

  }, []);

  // console.log(state);
  const { articles } = useSelector((state) => state.board);
  const groupId = 11;
  // const groupId = null;
  const { notifications } = useSelector((state) => state.noti);

  const [show, setShow] = useState(true);

  if (groupId) {
    return (
      <div>
        <AddButton mode="feed" />
        {articles.map((article, index) => {
          return <ArticleCard key={index} article={article} style={{ "margin-top": "10px" }} mode="feed" />;
        })}
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
