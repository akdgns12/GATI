import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import httpClient from "../../utils/axios";

// actions
export const postCalendar = createAsyncThunk(
  "plan/createPlan",

  async (planData, { rejectWithValue }) => {
    const Data = {
      title: planData.title,
      startDate: planData.startDate,
      endDate: planData.endDate,
      groupId: planData.groupId,
      userId: planData.userId,
      memo: planData.memo,
      place: planData.place,
    };

    try {
      const response = await httpClient.post("/plan", Data);
      console.log(response.data);
      return response.plan;
    } catch (error) {
      console.log(error);
      return rejectWithValue(error);
    }
  }
);

const initialState = {};

// slice
const userSlice = createSlice({
  name: "plan",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(postCalendar.fulfilled, (state, action) => {
      state.push(action.payload);
    });
  },
});

export default userSlice.reducer;
