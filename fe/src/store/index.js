import { combineReducers } from "redux";
import board from "./Board/board.js";
import noti from "./Nofitication/noti.js";
import schedule from "./Schedule/schedule.js";
import 'font-awesome/css/font-awesome.min.css';

const rootReducer = combineReducers({
  board,
  noti,
  schedule,
});

export default rootReducer;
