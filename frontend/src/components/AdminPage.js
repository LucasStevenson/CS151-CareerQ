import React, { useState, useEffect } from "react";
import { Container, Row, Col, Button } from "react-bootstrap";

const AdminPage = () => {
    const [events, setEvents] = useState([]);

    useEffect(() => {
      async function fetchData() {
        let rawResponse = await fetch(`http://localhost:8080/admin`, {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
          },
        });
        let res = await rawResponse.json();
        setEvents(res);
      }
      fetchData();
    }, []);

    
  return (
    <Container className="my-5">
      <Row>
        <Col>
          <h1>Admin</h1>
        </Col>
      </Row>
      <Row>
        <Col md={6} className="my-3">
          <Button variant="primary" size="lg" block>
            Manage Users
          </Button>
        </Col>
        <Col md={6} className="my-3">
          <Button variant="primary" size="lg" block>
            Manage Events
          </Button>
        </Col>
      </Row>
    </Container>
  );
};

export default AdminPage;