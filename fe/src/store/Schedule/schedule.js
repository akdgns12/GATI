const LOAD = "schedule/LOAD";

export const load = () => ({ type: LOAD });

const initialState = {
  planData: [
    {
      title: "가족 외식",
      content: "월요일 저녁7시 oo삼겹살 집 앞",
      start: "2023-01-15 16:00",
      end: "2023-01-18 18:30",
    },
    {
      title: "가족 여행",
      content: "화요일 김포공항 아침 8시",
      start: "2022-02-12 13:00",
      end: "2022-02-18 11:30",
    },
  ],
};

// reducer
export default function schedule(state = initialState, action) {
  switch (action.type) {
    case LOAD:
      return {
        ...state,
      };
    default:
      return state;
  }
}
