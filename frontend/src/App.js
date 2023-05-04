import { useState } from 'react'
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";

import LoginPage from "./components/LoginPage";
import RegisterPage from "./components/RegisterPage";
import EventsPage from "./components/EventsPage";
import StudentDashboard from "./components/StudentDashboard";

function App() {
  return (
    <Router>
        <Routes>
            <Route path="/" element={<StudentDashboard />} />
            <Route path="/" element={<EventsPage />} />
            <Route path="/login" element={<LoginPage />} />
            <Route path="/events" element={<EventsPage />} />
        </Routes>
    </Router>
  )
}

export default App;
