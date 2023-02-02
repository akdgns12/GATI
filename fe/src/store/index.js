import { combineReducers } from "redux";
import board from "./Board/board.js";
import noti from "./Nofitication/noti.js";
import schedule from "./Schedule/schedule.js";
import city from "./GoTogether/city.js";

const rootReducer = combineReducers({
  board,
  noti,
  schedule,
  city,
});

export default rootReducer;
