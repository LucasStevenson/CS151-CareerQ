import React, { useState } from "react";
import { Container, Row, Col, Form, Button } from "react-bootstrap";
import { Link } from "react-router-dom";
import jwt_decode from "jwt-decode";
import { useNavigate } from "react-router-dom";

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [errMsg, setErrMsg] = useState("");

  const navigate = useNavigate();

  // handle login logic (make post request to backend)
  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
        let rawResponse = await fetch(`http://localhost:8080/login`, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({   // stringify the request body object
                "email": email,
                "password": password
            })
        });
        let res = await rawResponse.text(); 
        if (!rawResponse.ok) { // if the http status code is not 2xx
            // `res` holds the error message
            throw new Error(res);
        }
        // at this point, the login was successful
        // we are given the jwt (stored in `res`)
        localStorage.setItem("token", res); // save token to localStorage

        // Decode the token to get the user data
        const decoded = jwt_decode(res);

        // Redirect to the appropriate dashboard based on user role
        if (decoded.uType === "school") {
          navigate("/admin");
        } else if (decoded.uType === "student") {
          navigate("/student");
        } else if (decoded.uType === "company") {
          navigate("/company");
        }
    } catch (err) {   
        setErrMsg(err.message);
    }
  };

  return (
    <Container fluid className="d-flex justify-content-center align-items-center vh-100 bg-light">
      <Form onSubmit={handleSubmit} className="bg-white rounded p-5 shadow-sm" style={{ maxWidth: "400px" }}>
        <h1 className="mb-4 font-weight-normal text-center">Login</h1>
        <Form.Group controlId="formBasicEmail">
          <Form.Label>Email address</Form.Label>
          <Form.Control type="email" placeholder="Enter email" value={email} onChange={(e) => setEmail(e.target.value)} required size="lg" className="form-control-lg"/>
        </Form.Group>
        <Form.Group controlId="formBasicPassword">
          <Form.Label>Password</Form.Label>
          <Form.Control type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} required size="lg" className="form-control-lg"/>
        </Form.Group>
        <Button variant="primary" type="submit" block size="lg" className="mt-2">
          Sign In
        </Button>
        {errMsg && (
          <div className="alert alert-danger mt-3" role="alert">
            {errMsg}
          </div>
        )}
        <div className="mt-3 text-center">
          <span className="mr-2">Don't have an account? </span>
          <Link to="/register" className="font-weight-bold">Register Here</Link>
        </div>
      </Form>
    </Container>
  );
};

export default Login;
