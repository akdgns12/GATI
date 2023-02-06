import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import httpClient from "../../utils/axios";

// actions
export const loadMainFeed = createAsyncThunk(
  "board/loadMainFeed",
  async (reqData, { rejectWithValue }) => {
    try {
      const response = await httpClient.get("/boards/" + reqData);
      return response.data;
    } catch (error) {
      console.log(error);
      return rejectWithValue(error);
    }
  }
);

// initial state
const initialState = {
  articles: [],
  article: {
    postId: 1,
    userId: "seotai78",
    content: "호에엥",
    tag: "#호엥",
    img: "https://picsum.photos/400/300",
    like: 3,
    createTime: "22.12.31",
    comment: 4,
    comments: [
      { userId: "akdgns12", comment: "ㅋ" },
      { userId: "gkagu12", comment: "ㅋㅋ" },
      { userId: "podif", comment: "호엥" },
    ],
  },
};

// slice
const boardSlice = createSlice({
  name: "board",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(loadMainFeed.pending, (state) => {
      // console.log("pending...");
    });
    builder.addCase(loadMainFeed.fulfilled, (state, action) => {
      state.articles = action.payload;
    });
    builder.addCase(loadMainFeed.rejected, (state) => {
      console.log(state);
    });
  },
});

export default boardSlice.reducer;

// {
//     userId: "test",
//     content: "description",
//     tag: "#ex",
//     img: "https://picsum.photos/400/300",
//     like: 3,
//     createTime: "23.01.27",
//     comment: 2,
//   },
//   {
//     postId: 1,
//     userId: "seotai78",
//     content: "호에엥",
//     tag: "#ex",
//     img: "https://picsum.photos/400/300",
//     like: 1,
//     createTime: "23.01.01",
//     comment: 3,
//   },
//   {
//     userId: "akdgns12",
//     content: "description",
//     tag: "#ex",
//     img: "..",
//     like: 3,
//     createTime: "22.12.31",
//     comment: 0,
//   },
//   {
//     userId: "akdgns13",
//     content: "desc",
//     tag: "#ex",
//     img: "https://picsum.photos/400/300",
//     like: 1,
//     createTime: "22.12.31",
//     comment: 1,
//   },
//   {
//     userId: "gkagu12",
//     content: "desc",
//     tag: "#ex",
//     img: null,
//     like: 3,
//     createTime: "23.12.25",
//     comment: 4,
//   },
//   {
//     userId: "podif",
//     content: "description",
//     tag: "#ex",
//     img: "https://picsum.photos/400/300",
//     like: 3,
//     createTime: "23.12.01",
//     comment: 4,
//   },