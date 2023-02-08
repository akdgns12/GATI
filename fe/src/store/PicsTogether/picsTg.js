import { createSlice } from "@reduxjs/toolkit";
import DinnerImg from "../../static/PicsTgExampleDinner.png";

// PicsTgSlice 정의
export const picsTgSlice = createSlice({
  name:'picsTgSlice',
  initialState:{
    mode:'inprogress',
    missionMode:false,
    setPictureNumber:4,
    thisWeekMission:{
      id:1,
      startdate:'2023-01-30',
      enddate:'2023-02-05',
      title:'오늘의 저녁 메뉴는 무엇인가요?',
      description:'오늘 저녁으로 무엇을 드셨나요? 오늘의 저녁 식단을 올리고 가족들과 함께 공유해보세요! 이번주 가족들의 저녁 메뉴 식단을 모아 가치 한 장! 우리 가족의 재미있는 추억으로 남겨드립니다^_^',
      exampleImg:DinnerImg,
    },
  },
  reducers:{
    changeMode:(state, action) =>{
      if (action.payload === 'inprogress') {
        state.picsTg.mode = 'completed'
      } else if (action.payload === 'completed'){
        state.picsTg.mode = 'inprogress'
      }
    },
    changeMissionMode:(state, action) =>{
      state.picsTg.missionMode = true
    },
    changeSetPictureNumber:(state, action) =>{
      state.picsTg.setPictureNumber = action.payload
    },
  },
})

// index.js에서 rootReducer 만드는 데 사용
export default picsTgSlice.reducer;

// reducers는 action creators 자동 생성해주므로 export해서 사용
export const {changeMode, changeMissionMode, changeSetPictureNumber } = picsTgSlice.actions;



    
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