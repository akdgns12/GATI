import React from "react";
import ReactDOM from "react-dom/client";

export default function UploadPics(props) {
  return (
    <div>
        <h1>UploadPics</h1>
        <p>사진 개수 : {props.pics}</p>
    </div>
  )
};