import React from "react";
import ReactDOM from "react-dom/client";
import reportWebVitals from "./reportWebVitals";
// react-router
import { BrowserRouter } from "react-router-dom";
// react-redux
import { configureStore, getDefaultMiddleware } from "@reduxjs/toolkit";
import { Provider } from "react-redux";
import { persistReducer, persistStore } from "redux-persist";
import storage from "redux-persist/lib/storage";

import App from "./App";
import rootReducer from "./store";
import { PersistGate } from "redux-persist/integration/react";

// 달력용 recoil
import { RecoilRoot } from "recoil";
import { createTheme, ThemeProvider } from "@mui/material";

// CSS - 폰트
import './App.css'


const root = ReactDOM.createRoot(document.getElementById("root"));

const persistConfig = {
  key: "root",
  storage,
  whitelist: ["user"],
};

const _persistedReducer = persistReducer(persistConfig, rootReducer);

const customizedMiddleware = getDefaultMiddleware({
  serializableCheck: false,
});

const store = configureStore({
  reducer: _persistedReducer,
  devTools: process.env.NODE_ENV !== "production",
  middleware: customizedMiddleware,
});

// MUI 컴포넌트에 폰트 적용
const theme = createTheme({
  typography : {
    fontFamily:'ONE-Mobile-POP'
  }
})

export const persistor = persistStore(store);

root.render(
  <ThemeProvider theme={theme}>
    <React.StrictMode>
      <BrowserRouter>
        <RecoilRoot>
        <Provider store={store}>
          <PersistGate loading={null} persistor={persistor}>
            <App />
          </PersistGate>
        </Provider>
        </RecoilRoot>
      </BrowserRouter>
    </React.StrictMode>
  </ThemeProvider>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
