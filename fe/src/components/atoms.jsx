import { atom } from "recoil";
import { recoilPersist } from "recoil-persist";

const { persistAtom } = recoilPersist();

export const blocksState = atom({
  key: "blocks",
  default: {},
  effects_UNSTABLE: [persistAtom],
});

export const modalState = atom({
  key: "modal",
  default: { state: false, id: null },
});

export const dataState = atom({
  key: "dataState",
  default: [
    {
      endDate: "",
      groupId: -1,
      memo: "",
      place: "",
      startDate: "",
      title: "",
      userId: "",
    },
  ],
});
