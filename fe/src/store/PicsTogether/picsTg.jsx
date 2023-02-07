// redux ducks

// action types
const PLUS = "picsTg/PLUS";

// action creators
export const plus = (payload) => ({ type: PLUS, payload: payload});

// initial state
const initialState = {
  // userId: "tester",
  mission:
    {
      id:1,
      startdate:'2023-01-30',
      enddate:'2023-02-05',
      title:'오늘의 저녁 메뉴는 무엇인가요?',
      description:'오늘 저녁으로 무엇을 드셨나요? 오늘의 저녁 식단을 올리고 가족들과 함께 공유해보세요! 이번주 가족들의 저녁 메뉴 식단을 모아 가치 한 장! 우리 가족의 재미있는 추억으로 남겨드립니다^_^'
    },
    // {
    //   id:2,
    //   startdate:'2023-02-06',
    //   enddate:'2023-02-13',
    //   title:'이번주 하늘 보셨나요?',
    //   description:'오늘 하루 하늘을 올려다 보신 적이 있나요? 바쁜 삶 속에서도 잠시 하늘을 쳐다보는 여유를 가져보세요! 이번주 가족들의 하늘 사진을 모아 가치 한 장! 우리 가족의 재미있는 추억으로 남겨드립니다^_^'
    // },
  uploadedImgs: [

  ]

};

// reducer
export default function picsTg(state = initialState, action) {
  switch (action.type) {
    case PLUS:
      const newState = {...state}
      newState.uploadedImgs.push(action.payload)
      return {
        newState
      };
    default:
      return state;
  }
}
