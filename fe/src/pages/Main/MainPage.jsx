import React from "react";
import ReactDOM from "react-dom/client";
import ArticleCard from "../../components/Main/ArticleCard";

class Home extends React.Component {
  render() {
    let list = [1, 2, 3, 4, 5];
    return (
      <div>
        {list.map((item) => {
          return (<ArticleCard key={item} style={{ 'margin-top': "10px" }} />)
        })}
      </div>
    );
  };
};

export default Home;
