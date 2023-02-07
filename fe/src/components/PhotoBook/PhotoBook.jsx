import React from "react";
import ReactDOM from "react-dom/client";
import SearchBar from "./SearchBar";
import Img from './Img'
import NoImg from "./NoImg";
import PhotoData from "../PicsTogether/PhotoData";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import AddButton from "../Main/AddButton";

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
