import React from "react";
import { Routes, Route } from "react-router";

import AdminPage from "./AdminPage";
import AdminCreate from "./AdminCreate";
import AdminEdit from "./AdminEdit";

import PrivateRouter from "../../utils/PrivateRouter";
import { doLoginUser } from "../../store/User/user";

export default function AdminRouter(props) {
  return (
    <Routes>
      <Route path="/" element={<AdminPage />} />
      <Route
        path="/edit"
        element={<PrivateRouter auth={doLoginUser.role} component={<AdminEdit />} />}
      />
      <Route path="/create" element={<AdminCreate />} />
    </Routes>
  );
}
