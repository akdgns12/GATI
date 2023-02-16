import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import httpClient from "../../utils/axios";

export const loadNotification = createAsyncThunk(
  "noti/loadNotification",
  async (reqData, { rejectWithValue }) => {
    try {
      const response = await httpClient.get(`/noti/${reqData}`);
      return response;
    } catch (error) {
      // console.log(error);
      return rejectWithValue(error);
    }
  }
);
// initial state
const initialState = {
  // userId: "tester",
  notifications: [],
};

const notiSlice = createSlice({
  name: "noti",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(loadNotification.fulfilled, (state, action) => {
      state.notifications = action.payload.data;
    });
    builder.addCase(loadNotification.rejected, (action) => {
      console.log("REJECTED");
    });
  },
});

export default notiSlice.reducer;
