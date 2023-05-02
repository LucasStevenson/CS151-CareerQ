import React, { useState } from "react";
import { Container, Form, Button } from "react-bootstrap";

const RegisterPage = () => {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [userType, setUserType] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [errMsg, setErrMsg] = useState("");

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
        // login was successful
        console.log(res)
    } catch (err) {   
        setErrMsg(err.message);
    }
  };

    return (
        <Container className="d-flex justify-content-center align-items-center vh-100">
      <Form onSubmit={handleSubmit}>
        <h1>Register</h1>
        <Form.Group className="mb-3" controlId="formBasicEmail">
          <Form.Label>Email address</Form.Label>
          <Form.Control
            type="email"
            placeholder="Enter email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </Form.Group>

        <Form.Group className="mb-3" controlId="formBasicPassword">
          <Form.Label>Password</Form.Label>
          <Form.Control
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </Form.Group>

        <Form.Group className="mb-3" controlId="formBasicName">
          <Form.Label>Name</Form.Label>
          <Form.Control
            type="text"
            placeholder="Name"
            value={name}
            onChange={(e) => setName(e.target.value)}
          />
        </Form.Group>

        <Form.Group className="mb-3" controlId="formBasicUserType">
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

        <Button variant="primary" type="submit">
          Register
        </Button>

        {errMsg && (
          <div className="alert alert-danger mt-3" role="alert">
            {errMsg}
          </div>
        )}
      </Form>
    </Container>
    );

};

export default RegisterPage;
