import { Outlet, Navigate } from "react-router-dom";

const ProtectedLayout = () => {
  const token = localStorage.getItem("token");

  // If no token → redirect to login
  if (!token) {
    return <Navigate to="/" replace />;
  }

  // If token exists → render child routes
  return <Outlet />;
};

export default ProtectedLayout;
