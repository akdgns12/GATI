const initialState = {
  cities: [
    {
      cityname: "서울",
      cityimg: "https://picsum.photos/300/200?random=1",
      placename: "한강",
    },
    {
      cityname: "서울",
      cityimg: "https://picsum.photos/300/200?random=2",
      placename: "가로수길",
    },
    {
      cityname: "서울",
      cityimg: "https://picsum.photos/300/200?random=3",
      placename: "멀티캠퍼스",
    },
    {
      cityname: "서울",
      cityimg: "https://picsum.photos/300/200?random=4",
      placename: "뚝섬",
    },
    {
      cityname: "부산",
      cityimg: "https://picsum.photos/300/200?random=5",
      placename: "명소1",
    },
    {
      cityname: "부산",
      cityimg: "https://picsum.photos/300/200?random=6",
      placename: "명소2",
    },
    {
      cityname: "부산",
      cityimg: "https://picsum.photos/300/200?random=7",
      placename: "광안리",
    },
    {
      cityname: "부산",
      cityimg: "https://picsum.photos/300/200?random=8",
      placename: "해운대",
    },
  ],
};

// reducer

export default function city(state = initialState, action) {
  switch (action.type) {
    default:
      return state;
  }
}
