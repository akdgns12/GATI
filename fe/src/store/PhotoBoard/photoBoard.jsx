import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import httpClient from "../../utils/axios";

// actions
export const loadPhotoBook = createAsyncThunk(
  "album/loadPhotoBook",
  async (reqData, { rejectWithValue }) => {
    try {
      const response = await httpClient.get("/albums/page", {
        params: reqData,
      });
      // console.log(response);
      return response;
    } catch (error) {
      console.log(error);
      return rejectWithValue(error);
    }
  }
);

export const loadPhotoDetail = createAsyncThunk(
  "album/loadPhotoDetail",
  async (reqData, { rejectWithValue }) => {
    try {
      // console.log(reqData);
      const response = await httpClient.get("/albums/album/" + reqData.photoId, {
        params: { userId: reqData.userId },
      });
      return response.data;
    } catch (error) {
      console.log(error);
      return rejectWithValue(error);
    }
  }
);

// initial state
const initialState = {
  currentPage: 0,
};

// slice
const albumSlice = createSlice({
  name: "album",
  initialState,
  reducers: {
    updatePage: (state, action) => {
      state.currentPage = action.payload;
    },
    clearPhoto: (state) => {
      console.log("clear all");
      state.photoInfo = [];
    },
  },
  extraReducers: (builder) => {
    builder.addCase(loadPhotoBook.pending, (state) => {});
    builder.addCase(loadPhotoBook.fulfilled, (state, action) => {
      if (state.photoInfo != null && state.photoInfo.length > 0) {
        if (action.payload.length > 0) {
          var lastIdx = state.photoInfo.findIndex((item) => item.id === action.payload[0].id);
          lastIdx = lastIdx === -1 ? state.photoInfo.length : lastIdx;
          state.photoInfo = [...state.photoInfo.slice(0, lastIdx), ...action.payload];
        } else {
          console.log("Nothing to append");
        }
      } else {
        // console.log("loading inital feeds");
        // console.log(action.payload);
        state.photoInfo = action.payload;
      }
    });
    builder.addCase(loadPhotoBook.rejected, (state) => {
      console.log(state);
    });

    builder.addCase(loadPhotoDetail.pending, (state) => {});
    builder.addCase(loadPhotoDetail.fulfilled, (state, action) => {
      state.photoDetail = action.payload;
    });
    builder.addCase(loadPhotoDetail.rejected, (state) => {
      console.log(state);
    });
  },
});

export default albumSlice.reducer;
export const { updatePage, clearPhoto } = albumSlice.actions;
