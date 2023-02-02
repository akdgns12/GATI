import React from "react";
import ReactDOM from "react-dom/client";
import SearchBar from "../../components/PhotoBook/SearchBar";
import Img from '../../components/PhotoBook/Img'
import NoImg from "../../components/PhotoBook/NoImg";
import PhotoData from "./PhotoData";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import AddButton from "../../components/Main/AddButton";

class PhotoBook extends React.Component {
  render() {

    const imgList = {
      display:'flex',
      flexWrap:'wrap',
      height:'100vh',
      width:'100vw',
      justifyContent:'flex-start',
      alignContent:"flex-start",
    }
    const container = {
      display:'flex',
      flexDirection: 'column',
      justifyContent:'center',
      alignItems:'center'
    }

    return (
      <div style={container}>
        <SearchBar/>
        <div style={imgList}>
          {PhotoData.map((item, index) => {
            return <Img key={index} item={item} />
          })}
        </div>
        {/* <NoImg/> */}
        <AddButton />
      </div>
    )
  }
}

export default PhotoBook;
