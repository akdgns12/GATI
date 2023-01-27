import React from "react";
import ReactDOM from "react-dom/client";
import ArticleCard from "../../components/Main/ArticleCard";
import AddButton from "../../components/Main/AddButton";

class Home extends React.Component {
  render() {
    let list = [1, 2, 3, 4, 5];
    return (
      <div>
        <AddButton />
        {list.map((item) => {
          return (<ArticleCard key={item} style={{ 'margin-top': "10px" }} />)
        })}
      </div>
    );
  };
};

export default Home;
