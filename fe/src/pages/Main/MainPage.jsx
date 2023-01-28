import React from "react";
import ReactDOM from "react-dom/client";
import { useDispatch, useSelector } from "react-redux";

import ArticleCard from "../../components/Main/ArticleCard";
import NotInGroup from "../../components/Main/NotInGroup";
import AddButton from "../../components/Main/AddButton";

const Home = () => {
  // console.log(state);
  const { articles } = useSelector(state => state.board);
  // const groupId = 11;
  const groupId = null;

  if (groupId) {
    return (
      <div>
        <AddButton />
        {articles.map((article, index) => {
          return (<ArticleCard key={index} article={article} style={{ 'margin-top': "10px" }} />)
        })}
      </div>
    );
  }
  else {
    return (
      <div>
        <NotInGroup />
      </div>
    )
  }
};

export default Home;
