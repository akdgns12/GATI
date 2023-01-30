import React from "react";
import ReactDOM from "react-dom/client";
import CustomizedInputBase from "../../components/PhotoBook/SearchBar";
import BasicDatePicker from "../../components/PhotoBook/DatePicker";
import ImageList from '../../components/PhotoBook/ImageList'

class PhotoBook extends React.Component {
  render() {
    return (
      <div>
        <div
          style={{display:'flex', p:'20px'}}>
          <CustomizedInputBase/>
          <BasicDatePicker/>
        </div>
        <ImageList/>
      </div>
    )
  }
}

export default PhotoBook;
