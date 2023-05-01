import React, { useState } from "react";
import { Container, Row, Col, Form, Button } from "react-bootstrap";

const LoginPage = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [errMsg, setErrMsg] = useState("");

  // update email and password state
  const handleEmailChange = (event) => {
    setEmail(event.target.value);
  };
  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };

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
        console.log(rawResponse.status);
        let jsonResponse = await rawResponse.text();
        console.log(jsonResponse);
        // do something with the response
    } catch (err) {   
        setErrMsg(err.message);
    }
  };

    return (
        <div className="d-flex justify-content-center align-items-center h-100">
          <Form onSubmit={handleSubmit}>
            <h1 className="mb-4 font-weight-normal">Login</h1>
            <Form.Group controlId="formBasicEmail">
              <Form.Label>Email address</Form.Label>
              <Form.Control
                type="email"
                placeholder="Enter email"
                value={email}
                onChange={handleEmailChange}
                required
              />
            </Form.Group>

            <Form.Group controlId="formBasicPassword">
              <Form.Label>Password</Form.Label>
              <Form.Control
                type="password"
                placeholder="Password"
                value={password}
                onChange={handlePasswordChange}
                required
              />
            </Form.Group>

            <Button variant="primary" type="submit" block>
              Submit
            </Button>

            {errMsg && (
              <div className="alert alert-danger mt-3" role="alert">
                {errMsg}
              </div>
            )}
          </Form>
        </div>
  );

};

export default LoginPage;
