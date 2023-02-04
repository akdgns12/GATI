// redux ducks

// action types
const RELOAD = 'noti/RELOAD';
const ACCEPT = 'noti/ACCEPT';
const REJECT = 'noti/REJECT';

// action creators
export const reload = () => ({ type: RELOAD });
export const accept = () => ({ type: ACCEPT });
export const reject = () => ({ type: REJECT });

// initial state
const initialState = {
  // userId: "tester",
  notifications: {
    invitations: [
      { "hostId": "akdgns12", "groupName": "A805", "date": "2023-01-28 20:00" },
      // { "hostId": "seotai78", "groupName": "SSAFY", "date": "2023-01-28 20:00" },
    ],
    likes: [],
  }
}

// reducer
export default function noti(state = initialState, action) {
  switch (action.type) {
    case RELOAD:
      return {
        ...state,
      }
    case ACCEPT:
      return {
        ...state,
      }
    case REJECT:
      return {
        ...state,
      }
    default:
      return state;
  }
}