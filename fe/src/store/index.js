import { combineReducers } from 'redux';
import board from './Board/board.js';
import noti from './Nofitication/noti.js';

const rootReducer = combineReducers({
  board,
  noti,
});

export default rootReducer;