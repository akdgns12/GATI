import React from "react";
import ReactDOM from "react-dom/client";
import CustomizedInputBase from "../../components/PhotoBook/SearchBar";
import BasicDatePicker from "../../components/PhotoBook/DatePicker";
class PhotoBook extends React.Component {
  render() {
    return (
      <div>
        <CustomizedInputBase/>
        <BasicDatePicker/>
      </div>
    )
  }
}

export default PhotoBook;
