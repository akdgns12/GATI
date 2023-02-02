import { combineReducers } from "redux";
import board from "./Board/board.js";
import noti from "./Nofitication/noti.js";
import schedule from "./Schedule/schedule.js";
<<<<<<< HEAD
import user from "./User/user.js";
import "font-awesome/css/font-awesome.min.css";
=======
import city from "./GoTogether/city.js";
>>>>>>> gotogether-carousel

const rootReducer = combineReducers({
  board,
  noti,
  schedule,
<<<<<<< HEAD
  user,
=======
  city,
>>>>>>> gotogether-carousel
});

export default rootReducer;
