import React from "react";
import { Link } from "react-router-dom";
import jwt_decode from "jwt-decode";

const Navbar = () => {
    const token = localStorage.getItem("token");
    let dashboardLink;
    if (token) {
      let decodedToken = jwt_decode(token);
      let userType = decodedToken.uType;
      switch(userType) {
        case "school":
          dashboardLink = "/admin";
          break;
        case "company":
          dashboardLink = "/company";
          break;
        case "student":
          dashboardLink = "/student";
          break;
        default:
          break;
      }
    } else {
      // user doesn't have jwt
      // redirect to login page
      dashboardLink = "/login";
    }

    return (
        <nav className="navbar navbar-expand-lg navbar-light bg-light">
          <div className="container">
            <Link to="/" className="navbar-brand">
              CareerQ
            </Link>
            <button
              className="navbar-toggler"
              type="button"
              data-toggle="collapse"
              data-target="#navbarNav"
              aria-controls="navbarNav"
              aria-expanded="false"
              aria-label="Toggle navigation"
            >
              <span className="navbar-toggler-icon"></span>
            </button>
            <div className="collapse navbar-collapse justify-content-end" id="navbarNav">
              <ul className="navbar-nav">
                <li className="nav-item">
                  <Link to={dashboardLink} className="nav-link">
                    Dashboard
                  </Link>
                </li>
                <li className="nav-item">
                  <Link to="/profile" className="nav-link">
                    Profile
                  </Link>
                </li>
                <li className="nav-item">
                  <Link to="/events" className="nav-link">
                    Events
                  </Link>
                </li>
              </ul>
            </div>
          </div>
        </nav>
      );
      
}

export default Navbar;
