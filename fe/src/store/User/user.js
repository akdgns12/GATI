// action types
const LOGIN = "user/LOGIN";

// action creators
export const login = (user) => ({ type: LOGIN, user });

// initial state
const initialState = {
  logIn: false,
  loginUser: {
    userId: "",
  },
  groups: [],
  // token & other stuffs
};

// reducer
export default function user(state = initialState, action) {
  switch (action.type) {
    case LOGIN:
      return {
        ...state,
        loginUser: action.data.user,
        logIn: true,
      };
    default:
      return state;
  }
}
