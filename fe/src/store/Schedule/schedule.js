import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import httpClient from "../../utils/axios";

// const LOAD = "schedule/LOAD";

// export const load = () => ({ type: LOAD });

// actions
export const postCalendar = createAsyncThunk(
  "plan/createPlan",

  async (planData, { rejectWithValue }) => {
    console.log("aa");
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
      console.log("hello");

      return response.data;
    } catch (error) {
      console.log(error);
      return rejectWithValue(error);
    }
  }
);

const initialState = {
  planData: [
    {
      title: "가족 외식",
      start: "2023-01-15 16:00",
      end: "2023-01-18 18:30",
    },
    {
      title: "가족 여행",
      start: "2022-02-12 13:00",
      end: "2022-02-18 11:30",
    },
  ],
};

// slice
const userSlice = createSlice({
  name: "plan",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(postCalendar.fulfilled, (state, action) => {
      state.planData.push(action.payload);
    });
  },
});

export default userSlice.reducer;

// reducer
// export default function schedule(state = initialState, action) {
//   switch (action.type) {
//     case LOAD:
//       return {
//         ...state,
//       };
//     default:
//       return state;
//   }
// }
