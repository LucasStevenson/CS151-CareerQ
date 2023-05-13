import React, { useState } from "react";
import { Container, Form, Button } from "react-bootstrap";
import { Link, useNavigate } from "react-router-dom";

const Register = () => {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [userType, setUserType] = useState("");
  const [password, setPassword] = useState("");
  const [errMsg, setErrMsg] = useState("");

  const navigate = useNavigate();

  // handle register logic 
  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
        let rawResponse = await fetch(`http://localhost:8080/register`, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({   // stringify the request body object
                "name": name,
                "email": email,
                "password": password,
                "userType": userType
            })
        });
        let res = await rawResponse.text();
        if (!rawResponse.ok) { // if the http status code is not 2xx
            // res holds the error message
            throw new Error(res);
        }
        // register was successful
        navigate("/login");
    } catch (err) {   
        setErrMsg(err.message);
    }
  };

  return (
    <Container
      fluid
      className="d-flex justify-content-center align-items-center vh-100 bg-light"
    >
      <Form
        onSubmit={handleSubmit}
        className="bg-white rounded p-5 shadow-sm"
        style={{ maxWidth: "400px" }}
      >
        <h1 className="mb-4">Register</h1>
        <Form.Group controlId="formBasicEmail">
          <Form.Label>Email address</Form.Label>
          <Form.Control
            type="email"
            placeholder="Enter email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </Form.Group>

        <Form.Group controlId="formBasicPassword">
          <Form.Label>Password</Form.Label>
          <Form.Control
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </Form.Group>

        <Form.Group controlId="formBasicName">
          <Form.Label>Name</Form.Label>
          <Form.Control
            type="text"
            placeholder="Name"
            value={name}
            onChange={(e) => setName(e.target.value)}
          />
        </Form.Group>

        <Form.Group controlId="formBasicUserType">
          <Form.Label>User Type</Form.Label>
          <Form.Control
            as="select"
            value={userType}
            onChange={(e) => setUserType(e.target.value)}
          >
            <option value="" disabled>
              Select User Type
            </option>
            <option value="student">Student</option>
            <option value="company">Company</option>
            <option value="school">School</option>
          </Form.Control>
        </Form.Group>

        <Button variant="primary" type="submit" block className="mt-2">
          Register
        </Button>

        {errMsg && (
          <div className="alert alert-danger mt-3" role="alert">
            {errMsg}
          </div>
        )}

        <div className="mt-3 text-center">
          <span className="mr-2">Already have an account? </span>
          <Link to="/login" className="font-weight-bold">
            Sign in
          </Link>
        </div>
      </Form>
    </Container>
  );
};

export default Register;
