import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import httpClient from "../../utils/axios";

// actions
export const loadPhotoBook = createAsyncThunk(
  "album/loadPhotoBook",
  async (reqData, { rejectWithValue }) => {
    // console.log(reqData);
    try {
      const response = await httpClient.get("/albums/page", {
        params: reqData,
      });
      // console.log(response.data)
      return response.data;
      
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
      const response = await httpClient.get(
        "/albums/album/" + reqData.photoId,
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
const albumSlice = createSlice({
  name: 'album',
  initialState,
  reducers: {

  },
  extraReducers: (builder) => {
    builder.addCase(loadPhotoBook.pending, (state) => {
      
    })
    builder.addCase(loadPhotoBook.fulfilled, (state, action) => {
      state.photoInfo = action.payload
    })
    builder.addCase(loadPhotoBook.rejected, (state) => {
      console.log(state)
    });

    builder.addCase(loadPhotoDetail.pending, (state) => {
      
    })
    builder.addCase(loadPhotoDetail.fulfilled, (state, action) => {
      state.photoDetail = action.payload
    })
    builder.addCase(loadPhotoDetail.rejected, (state) => {
      console.log(state)
    });

  }
})

export default albumSlice.reducer;
