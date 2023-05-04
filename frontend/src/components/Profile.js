import React from "react";
import { Button } from "react-bootstrap";
import styles from "../Module/Profile.module.css";
import { useNavigate } from "react-router-dom";

const Profile = () => {
  //Need to change this code so that it fetches the user's information from the database
  const user = {
    name: "John Doe",
    email: "test@example.com",
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
          <p>{user.email}</p>
        </div>
      </div>
      <Button variant="primary" size="sm" onClick={handleLogOut}>
        Log Out
      </Button>
    </div>
  );
};

export default Profile;
