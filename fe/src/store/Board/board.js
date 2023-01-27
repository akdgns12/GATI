// redux ducks

// action types
const RELOAD = 'board/RELOAD';
const LOAD_MORE = 'board/LOAD_MORE';

// action creators
export const reload = () => ({ type: RELOAD });
export const loadMore = () => ({ type: LOAD_MORE });

// initial state
const initialState = {
  // userId: "tester",
  articles: [
    { userId: "test", content: "description", tag: "#ex", img: "https://picsum.photos/400/300", like: 3, createTime: "95.01.01", comment: 4 },
    { userId: "test", content: "description", tag: "#ex", img: "..", like: 3, createTime: "95.01.01", comment: 4 },
  ],
}

// reducer
export default function board(state = initialState, action) {
  switch (action.type) {
    case RELOAD:
      return {
        ...state,
      }
    case LOAD_MORE:
      return {
        ...state,
      }
    default:
      return state;
  }
}