import React from "react";
import ReactDOM from "react-dom/client";
import ArticleCard from "../../components/Main/ArticleCard";
import AddButton from "../../components/Main/AddButton";
import { useDispatch, useSelector } from "react-redux";

const Home = () => {
  // console.log(state);
  const { articles } = useSelector(state => state.board);
  
  return (
    <div>
      <AddButton />
      {articles.map((article, index) => {
        return (<ArticleCard key={index} article={article} style={{ 'margin-top': "10px" }} />)
      })}
    </div>
  );
};


export default Home;
