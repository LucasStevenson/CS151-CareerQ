import { useState } from 'react'
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";


import LoginPage from "./components/LoginPage";

function App() {
  return (
    <Router>
        <Routes>
            <Route path="/" element={<LoginPage />} />
        </Routes>
    </Router>
  )
}

export default App;
