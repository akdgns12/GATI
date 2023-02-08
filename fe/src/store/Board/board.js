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
      return response.data;
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
      console.log(reqData);
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
const initialState = {};

// slice
const boardSlice = createSlice({
  name: "board",
  initialState,
  reducers: {
    updateLike: (state, action) => {
      console.log(action.payload);
    },
  },
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
    builder.addCase(loadPostDetail.pending, (state) => {});
    builder.addCase(loadPostDetail.fulfilled, (state, action) => {
      state.article = action.payload;
    });
    builder.addCase(loadPostDetail.rejected, (state) => {});
  },
});

export default boardSlice.reducer;
export const { updateLike } = boardSlice.actions;
