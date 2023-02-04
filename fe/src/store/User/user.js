import { createAsyncThunk, createSlice, createAction } from "@reduxjs/toolkit";
import httpClient from "../../utils/axios";

// actions
export const doLoginUser = createAsyncThunk(
  "user/loginUser",
  async (userData, { rejectWithValue }) => {
    // const response = await httpClient.post(TXdata);
    try {
      const response = await httpClient.post("/user/login", {
        userId: userData.userId,
        password: userData.password,
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
  loading: "",
  logIn: false,
  loginUser: {
    userId: "",
    accessToken: "",
  },
  groups: [],
  // token & other stuffs
};

// slice
const userSlice = createSlice({
  name: "user",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(doLoginUser.pending, (state) => {
      state.loading = "pending";
    });
    builder.addCase(doLoginUser.fulfilled, (state, action) => {
      state.loading = "success";
      state.logIn = true;
      state.loginUser.accessToken = action.payload.createToken;
    });
    builder.addCase(doLoginUser.rejected, (state) => {
      state.loading = "rejected";
    });
  },
});

export default userSlice.reducer;
