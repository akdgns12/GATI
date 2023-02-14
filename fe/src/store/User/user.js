import { createAsyncThunk, createSlice, createAction } from "@reduxjs/toolkit";
import { PURGE } from "redux-persist";
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
  logIn: false,
  loginUser: {
    userId: "",
    accessToken: "",
    refreshToken: "",
  },
  mainGroup: {},
  defaultGroup: {},
  // token & other stuffs
};

// slice
const userSlice = createSlice({
  name: "user",
  initialState,
  reducers: {
    updateToken: (state, action) => {
      // console.log(action);
      console.log("STATE TOKEN HAS BEEN MODIFIED !");
      state.loginUser.accessToken = action.payload;
      // console.log(state.loginUser.accessToken);
    },
    updateMainGroup: (state, action) => {
      // console.log(action);
      state.mainGroup = { ...state.mainGroup, ...action.payload };
    },
    updateDefaultGroup: (state, action) => {
      console.log(action);
      state.defaultGroup = action.payload;
    },
    updateUserNickName: (state, action) => {
      // console.log(action);
      state.loginUser.nickName = action.payload;
    },
  },
  extraReducers: (builder) => {
    builder.addCase(doLoginUser.pending, (state) => {});
    builder.addCase(doLoginUser.fulfilled, (state, action) => {
      state.logIn = true;
      state.loginUser = action.payload["user Info"];
      state.loginUser.accessToken = action.payload.accessToken;
      state.loginUser.refreshToken = action.payload.refreshToken;

      if (action.payload["mainGroup Info"].body.msg === "success") {
        state.mainGroup = action.payload["mainGroup Info"].body["Main family"];
        state.defaultGroup =
          action.payload["mainGroup Info"].body["Main family"];
      } else state.mainGroup = {};
    });
    builder.addCase(doLoginUser.rejected, (state) => {});
    builder.addCase(PURGE, (state) => {
      console.log("PURGE !");
      state = initialState;
    });
  },
});

export default userSlice.reducer;
export const {
  updateToken,
  updateMainGroup,
  updateDefaultGroup,
  updateUserNickName,
} = userSlice.actions;
