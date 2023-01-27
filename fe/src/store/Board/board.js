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
    { userId: "test", content: "description", tag: "#ex", img: "https://picsum.photos/400/300", like: 3, createTime: "23.01.27", comment: 2 },
    { userId: "seotai78", content: "호에엥", tag: "#ex", img: "https://picsum.photos/400/300", like: 1, createTime: "23.01.01", comment: 5 },
    { userId: "akdgns12", content: "description", tag: "#ex", img: "..", like: 3, createTime: "22.12.31", comment: 0 },
    { userId: "akdgns13", content: "desc", tag: "#ex", img: "https://picsum.photos/400/300", like: 1, createTime: "22.12.31", comment: 1 },
    { userId: "gkagu12", content: "desc", tag: "#ex", img: null, like: 3, createTime: "23.12.25", comment: 4 },
    { userId: "podif", content: "description", tag: "#ex", img: "https://picsum.photos/400/300", like: 3, createTime: "23.12.01", comment: 4 },
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