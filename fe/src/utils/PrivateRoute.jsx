import React from "react";
import { Navigate } from "react-router-dom";

function PrivateRoute({ authenticated, component: Component }) {
  console.log(authenticated);
  return authenticated ? Component : <Navigate to="/login" />;
}

export default PrivateRoute;
