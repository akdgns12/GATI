import { persistor } from "../index.jsx";

export const doLogOut = async () => {
  console.log("DO LOG OUT");
  await setTimeout(() => persistor.purge(), 1000);
  console.log("OUT");
};
