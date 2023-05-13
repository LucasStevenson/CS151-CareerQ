import React from "react";
import { Button } from "react-bootstrap";
import styles from "../Module/Profile.module.css";
import { useNavigate } from "react-router-dom";
import jwt_decode from "jwt-decode";

const Profile = () => {
  const token = localStorage.getItem("token");
  const decoded = jwt_decode(token);

  const user = {
    name: decoded.name,
    email: decoded.email,
    uType: decoded.uType,
    profilePic: "/profile.png",
  };

  const navigate = useNavigate();

  const handleLogOut = () => {
    localStorage.removeItem("token");
    navigate("/login");
  };

  return (
    <div className={styles.profile}>
      <h1>Profile</h1>
      <div className={styles.profileInfo}>
        <img
          src={user.profilePic}
          alt={`${user.name}'s profile`}
          className={styles.profilePicture}
        />
        <div className={styles.profileDetails}>
          <h2>{user.name}</h2>
          <p>Email: {user.email}<br />
            UserType: {user.uType}</p>
        </div>
      </div>
      <Button variant="primary" size="sm" onClick={handleLogOut}>
        Log Out
      </Button>
    </div>
  );
};

export default Profile;
