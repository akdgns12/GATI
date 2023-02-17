import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import httpClient from "../../utils/axios";

// actions
export const loadMainFeed = createAsyncThunk(
  "board/loadMainFeed",
  async (reqData, { rejectWithValue }) => {
    // console.log(reqData);
    try {
      const response = await httpClient.get("/boards/page", {
        params: reqData,
      });
      return response == undefined ? response : response.data;
    } catch (error) {
      console.log(error);
      return rejectWithValue(error);
    }
  }
);

export const loadPostDetail = createAsyncThunk(
  "board/loadPostDetail",
  async (reqData, { rejectWithValue }) => {
    try {
      // console.log(reqData);
      const response = await httpClient.get(
        "/boards/board/" + reqData.articleId,
        { params: { userId: reqData.userId } }
      );
      return response.data;
    } catch (error) {
      console.log(error);
      return rejectWithValue(error);
    }
  }
);

// initial state
const initialState = {
  curPageNo: 0,
};

// slice
const boardSlice = createSlice({
  name: "board",
  initialState,
  reducers: {
    updatePageNo: (state, action) => {
      // console.log(action);
      state.curPageNo = action.payload;
    },
    clearFeed: (state) => {
      state.articles = [];
    },
    updateComment: (state, action) => {
      const newComment = action.payload;
      state.article.boardCommentDtos = [
        ...state.article.boardCommentDtos,
        newComment,
      ];
    },
  },
  extraReducers: (builder) => {
    builder.addCase(loadMainFeed.pending, (state) => {
      // console.log("pending...");
    });
    builder.addCase(loadMainFeed.fulfilled, (state, action) => {
      // console.log(action.payload);
      if (state.articles != null && state.articles.length > 0) {
        if (action.payload.length > 0) {
          var lastIdx = state.articles.findIndex(
            (item) => item.id === action.payload[0].id
          );
          lastIdx = lastIdx === -1 ? state.articles.length : lastIdx;
          state.articles = [
            ...state.articles.slice(0, lastIdx),
            ...action.payload,
          ];
        }
        // else console.log("Nothing to append");
      } else {
        // console.log("loading inital feeds");
        state.articles = action.payload;
      }
      // console.log(state.articles);
    });
    builder.addCase(loadPostDetail.fulfilled, (state, action) => {
      state.article = action.payload;
    });
  },
});

export default boardSlice.reducer;
export const { clearFeed, updatePageNo, updateComment } = boardSlice.actions;
