import { combineReducers } from "redux";
import board from "./Board/board.js";
import noti from "./Nofitication/noti.js";
import schedule from "./Schedule/schedule.js";
import user from "./User/user.js";
import "font-awesome/css/font-awesome.min.css";
import city from "./GoTogether/city.js";
import picsTg from "./PicsTogether/picsTg.jsx";

const rootReducer = combineReducers({
  board,
  noti,
  schedule,
  user,
  city,
  picsTg
});

export default rootReducer;
