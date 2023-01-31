import React from "react";
import ReactDOM from "react-dom/client";
import CustomizedInputBase from "../../components/PhotoBook/SearchBar";
import BasicDatePicker from "../../components/PhotoBook/DatePicker";
import ImageList2 from '../../components/PhotoBook/ImageList2'

class PhotoBook extends React.Component {
  render() {
    return (
      <container
        style={{
          display:'flex',
          flexDirection: 'column',
          justifyContent:'center',
          alignItems:'center',}}>
        <CustomizedInputBase/>
        <ImageList2/>
      </container>
    )
  }
}

export default PhotoBook;
