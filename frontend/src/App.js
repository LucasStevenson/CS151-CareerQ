import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";

import LoginPage from "./pages/LoginPage";
import RegisterPage from "./pages/RegisterPage";
import EventsPage from "./pages/EventsPage";
import StudentPage from "./pages/StudentPage";
import AdminPage from "./pages/AdminPage";
import CompanyPage from "./pages/CompanyPage";
import SingleEventPage from "./pages/SingleEventPage";
import ProfilePage from "./pages/ProfilePage";
import HomePage from "./pages/HomePage";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage />} />
        <Route path="/student" element={<StudentPage />} />
        <Route path="/admin" element={<AdminPage />} />
        <Route path="/company" element={<CompanyPage />} />
        <Route path="/events" element={<EventsPage />} />
        <Route path="/events/:id" element={<SingleEventPage />} />
        <Route path="/profile" element={<ProfilePage />} />
      </Routes>
    </Router>
  );
}

export default App;
