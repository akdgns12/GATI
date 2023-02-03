// redux ducks

// action types
const RELOAD = "photoBoard/RELOAD";
const LOAD_MORE = "photoBoard/LOAD_MORE";

// action creators
export const reload = () => ({ type: RELOAD });
export const loadMore = () => ({ type: LOAD_MORE });

// initial state
const initialState = {
  photoCards: [
    {
      postId:1,
      userId: "gina",
      content: "어쩌구저쩌구",
      tag: "# 전주",
      img: "https://picsum.photos/400/300",
      like: 3,
      createTime: "23.01.27",
      comment: 2,
      comments: [
        { userId: "akdgns12", comment: "ㅋ" },
        { userId: "gkagu12", comment: "ㅋㅋ" },
        { userId: "podif", comment: "호엥" },
      ],
    },
    {
      postId:2,
      userId: "tina",
      content: "블라블라",
      tag: "# 서울",
      img: "https://picsum.photos/400/300",
      like: 3,
      createTime: "23.01.27",
      comment: 2,
      comments: [
        { userId: "akdgns12", comment: "ㅋ" },
        { userId: "gkagu12", comment: "ㅋㅋ" },
        { userId: "podif", comment: "호엥" },
      ],
    },
  ]
}
// reducer
export default function photoBoard(state = initialState, action) {
  switch (action.type) {
    case RELOAD:
      return {
        ...state,
      };
    case LOAD_MORE:
      return {
        ...state,
      };
    default:
      return state;
  }
}
