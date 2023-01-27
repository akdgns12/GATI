import { combineReducers } from 'redux';
import board from './Board/board.js';

const rootReducer = combineReducers({
  board,
});

export default rootReducer;