import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import httpClient from "../../utils/axios";

// 비동기 actions
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

// PicsTgSlice 정의
export const picsTgSlice = createSlice({
  name:'picsTgSlice',
  initialState:{
    mode:'inprogress',
    status:null,
    getMission:{completed:0}
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
    builder.addCase(asyncGetMission.pending, (state,action)=>{
      // console.log('pending')
      state.status = "로딩중"
    })
    builder.addCase(asyncGetMission.fulfilled, (state,action)=>{
      // console.log('fulfilled')
      state.status = '로딩완료'
      state.getMission = action.payload
      // console.log('picsTgSlice', picsTgSlice)
      // picsTgSlice에는 redux에 저장된 state 값들이 없음 ㅠㅠ state로 가야함
    })
    builder.addCase(asyncGetMission.rejected, (state,action)=>{
      // console.log('rejected')
      alert('에러가 발생했습니다.')
    })
    builder.addCase(asyncPutMission.pending, (state,action)=>{
      // console.log('pending')
      state.status = "로딩중"
    })
    builder.addCase(asyncPutMission.fulfilled, (state,action)=>{
      // console.log('fulfilled')
      state.status = '로딩완료'
      state.getMission = action.payload
    })
    builder.addCase(asyncPutMission.rejected, (state,action)=>{
      // console.log('rejected')
      alert('에러가 발생했습니다.')
    })
    builder.addCase(asyncPostImg.pending, (state,action)=>{
      // console.log('pending')
      state.status = "로딩중"
    })
    builder.addCase(asyncPostImg.fulfilled, (state,action)=>{
      // console.log('fulfilled')
      state.status = '로딩완료'
      state.getMission = action.payload
      // window.location.reload()
    })
    builder.addCase(asyncPostImg.rejected, (state,action)=>{
      // console.log('rejected')
      alert('에러가 발생했습니다.')
    })
    builder.addCase(asyncDeleteImg.pending, (state,action)=>{
      // console.log('pending')
      state.status = "로딩중"
    })
    builder.addCase(asyncDeleteImg.fulfilled, (state,action)=>{
      // console.log('fulfilled')
      state.status = '로딩완료'
      state.getMission = action.payload
      // window.location.reload()
    })
    builder.addCase(asyncDeleteImg.rejected, (state,action)=>{
      // console.log('rejected')
      alert('에러가 발생했습니다.')
    })

  }
})

// index.js에서 rootReducer 만드는 데 사용
export default picsTgSlice.reducer;

// reducers는 action creators 자동 생성해주므로 export해서 사용
export const { changeMode } = picsTgSlice.actions;



    
    // const dummy = {
    //   missions:[
    //     {
    //       id:1,
    //       startdate:'2023-01-30',
    //       enddate:'2023-02-05',
    //       title:'오늘의 저녁 메뉴는 무엇인가요?',
    //       description:'오늘 저녁으로 무엇을 드셨나요? 오늘의 저녁 식단을 올리고 가족들과 함께 공유해보세요! 이번주 가족들의 저녁 메뉴 식단을 모아 가치 한 장! 우리 가족의 재미있는 추억으로 남겨드립니다^_^'
    //     },
    //     {
    //       id:2,
    //       startdate:'2023-02-06',
    //       enddate:'2023-02-13',
    //       title:'이번주 하늘 보셨나요?',
    //       description:'오늘 하루 하늘을 올려다 보신 적이 있나요? 바쁜 삶 속에서도 잠시 하늘을 쳐다보는 여유를 가져보세요! 이번주 가족들의 하늘 사진을 모아 가치 한 장! 우리 가족의 재미있는 추억으로 남겨드립니다^_^'
    //     },
    //   ],
    // };