import React from "react";
import { Routes, Route } from "react-router";

import AdminPage from "./AdminPage";
import AdminCreate from "./AdminCreate";
import AdminEdit from "./AdminEdit";

import PrivateRoute from "../../utils/PrivateRoute";
import { doLoginUser } from "../../store/User/user";

export default function AdminRouter(props) {
  return (
    <Routes>
      <Route path="/" element={<AdminPage />} />
      <Route
        path="/edit"
        element={<PrivateRoute auth={doLoginUser.role} component={<AdminEdit />} />}
      />
      <Route path="/create" element={<AdminCreate />} />
    </Routes>
  );
}
