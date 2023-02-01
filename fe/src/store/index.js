import { combineReducers } from "redux";
import board from "./Board/board.js";
import noti from "./Nofitication/noti.js";
import schedule from "./Schedule/schedule.js";

const rootReducer = combineReducers({
  board,
  noti,
  schedule,
});

export default rootReducer;
