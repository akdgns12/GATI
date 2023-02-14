import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import httpClient from "../../utils/axios";


// 주의 사항. axios + createAsyncThunk의 끔찍한 혼종 코드임.. axios는 error가 나도 항상 object 형식의 리턴 값을 보내주기 때문에 axiosError 리턴 값 받았을 때, resp.data는 undefined라서 action.payload가 undefined가 됨 -> fulfilled로 처리 됨. 일단은 fulfilled에서 action.payload 값에 따라 로직처리 해놨음.


// Inprogress 모드에섯 사용하는 비동기 actions
export const asyncGetMission = createAsyncThunk(
  'picsTgSlice/asyncGetMission',
  async (groupId) => {
    const resp = await httpClient.get("/missions/" + groupId)
    // const resp = await axios.get('https://i8a805.p.ssafy.io/api/missions/' + groupId)
    console.log('GetMission/Resp',resp)
    const data = await resp.data
    // console.log('data', data)
    return data;
  }
)
export const asyncPutMission = createAsyncThunk(
  'picsTgSlice/asyncPutMission',
  async (reqData) => {
    const resp = await httpClient.put("/missions/setMemNumber/", reqData)
    console.log('asyncPutMission/Resp',resp)
    const data = await resp.data
    // console.log('data', data)
    return data;
  }
)
export const asyncPostImg = createAsyncThunk(
  'picsTgSlice/asyncPostImg',
  async (formData) => {
    const resp = await httpClient.post(
      "/missions/image/",
      formData,
      { headers: {
          // "Authorization": "YOUR_API_AUTHORIZATION_KEY_SHOULD_GOES_HERE_IF_HAVE",
          "Content-type": "multipart/form-data",
        },
      }
    )
    console.log('asyncPostImg/Resp',resp)
    const data = await resp.data
    console.log('data', data)
    return data;
  }
)
export const asyncDeleteImg = createAsyncThunk(
  'picsTgSlice/asyncDeleteImg',
  async (id) => {
    const resp = await httpClient.delete("/missions/image/" + id)
    console.log('asyncDeleteImg/Resp',resp)
    const data = await resp.data
    // console.log('data', data)
    return data;
  }
)
export const asyncPostMission = createAsyncThunk(
  'picsTgSlice/asyncPostMission',
  async (formData) => {
    const resp = await httpClient.post(
      "/missions/complete/",
      formData,
      { headers: {
          // "Authorization": "YOUR_API_AUTHORIZATION_KEY_SHOULD_GOES_HERE_IF_HAVE",
          "Content-type": "multipart/form-data",
        },
      }
    )
    console.log('asyncPostMission/Resp',resp)
    const data = await resp.data
    // console.log('data', data)
    return data;
  }
)
export const asyncDeleteMission = createAsyncThunk(
  'picsTgSlice/asyncDeleteMission',
  async (id) => {
    const resp = await httpClient.delete("/missions/mission/" + id)
    console.log('asyncDeleteMission/Resp',resp)
    const data = await resp.data
    // console.log('data', data)
    return data;
  }
)

// Completed 모드에섯 사용하는 비동기 actions
export const asynGetMissionList = createAsyncThunk(
  'picsTgSlice/asynGetMissionList',
  async (groupId) => {
    const resp = await httpClient.get("/missions/list/" + groupId)
    console.log('asynGetMissionList/Resp',resp)
    const data = await resp.data
    // console.log('data', data)
    return data;
  }
)


// PicsTgSlice 정의
export const picsTgSlice = createSlice({
  name:'picsTgSlice',
  initialState:{
    mode:'inprogress',
    getMission:{completed:0},
    getMissionList:[],
  },
  reducers:{
    changeMode:(state, action) =>{
      if (action.payload === 'inprogress') {
        state.mode = 'inprogress'
      } else if (action.payload === 'completed'){
        state.mode = 'completed'
      }
    },
  },
  extraReducers: (builder) => {
    builder.addCase(asyncGetMission.fulfilled, (state,action)=>{
      if (action.payload) {
        state.getMission = action.payload
      } else {
        alert('에러가 발생했습니다.')
      }
      // console.log('picsTgSlice', picsTgSlice)
      // picsTgSlice에는 redux에 저장된 state 값들이 없음 ㅠㅠ state로 가야함
    })
    builder.addCase(asyncPutMission.fulfilled, (state,action)=>{
      if (action.payload) {
        state.getMission = action.payload
      } else {
        alert('에러가 발생했습니다.')
      }
    })
    builder.addCase(asyncPostImg.fulfilled, (state,action)=>{
      if (action.payload) {
        state.getMission = action.payload
      } else {
        alert('에러가 발생했습니다.')
      }
    })
    builder.addCase(asyncDeleteImg.fulfilled, (state,action)=>{
      if (action.payload) {
        state.getMission = action.payload
      } else {
        alert('에러가 발생했습니다.')
      }
    })
    builder.addCase(asyncPostMission.fulfilled, (state,action)=>{
      if (!action.payload) {
        alert('에러가 발생했습니다.')
      }
    })
    builder.addCase(asyncDeleteMission.fulfilled, (state,action)=>{
      if (action.payload) {
        state.getMissionList = action.payload
      } else {
        alert('에러가 발생했습니다.')
      }
    })
    // Completed 모드에서 사용하는 actions
    builder.addCase(asynGetMissionList.fulfilled, (state,action)=>{
      if (action.payload) {
        state.getMissionList = action.payload
      } else {
        alert('에러가 발생했습니다.')
      }
    })
  }
})

// index.js에서 rootReducer 만드는 데 사용
export default picsTgSlice.reducer;

// reducers는 action creators 자동 생성해주므로 export해서 사용
export const { changeMode } = picsTgSlice.actions;