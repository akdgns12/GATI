import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import httpClient from "../../utils/axios";

// actions
export const loadCity = createAsyncThunk(
  "cities/loadCities",
  async (reqData, { rejectWithValue }) => {
    // console.log(reqData);
    try {
      const response = await httpClient.get("/cities/tag", {
        // params: reqData,
      });
      console.log(response.data);
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
const citySlice = createSlice({
  name: "album",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(loadCity.pending, (state) => {});
    builder.addCase(loadCity.fulfilled, (state, action) => {
      state.cities = action.payload;
    });
    builder.addCase(loadCity.rejected, (state) => {
      console.log(state);
    });
  },
});

export default citySlice.reducer;
