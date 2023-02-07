import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import httpRequest from "../../utils/test";


// const LOAD = "schedule/LOAD";

// export const load = () => ({ type: LOAD });

// actions
export const postCalendar = createAsyncThunk(
  "plan/createPlan",
  
  async (planData, {rejectWithValue}) => {
    // console.log('aa')
    const Data = {
        title: planData.title,
        start: planData.start,
        end: planData.end,
    }
    try {
      const response = await httpRequest.post('plan.json?', {
        Data
      })
      console.log(response.data)
      
      return response.data
    } catch (error) {
      console.log(error)
      return rejectWithValue(error)
    }
  }
)

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
  name: 'plan',
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(postCalendar.fulfilled, (state, action) => {
      state.planData.push(action.payload)
      console.log(state.planData)
      
    })
  },
})

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
