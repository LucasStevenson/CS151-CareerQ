import React, { useState, useEffect } from "react";
import { Container, Row, Col, Button } from "react-bootstrap";
import styles from "../Module/EventManagement.module.css";

const EventManagement = () => {
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
    <Container>
      <Row>
        <Col>
        <h1> Event Management</h1>
          <table>
            <thead>
              <tr>
                <th>Event ID</th>
                <th>Host</th>
                <th>Start Time</th>
                <th>End Time</th>
                <th>Waiting List</th>
                <th>Participating Companies</th>
              </tr>
            </thead>
            <tbody>
              {events.map((event) => (
                <tr key={event.eventID}>
                  <td>{event.eventID}</td>
                  <td>{event.host}</td>
                  <td>{event.startTime}</td>
                  <td>{event.endTime}</td>
                  <td>{event.waitingList.join(', ')}</td>
                  <td>{event.participatingCompanies.join(', ')}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </Col>
      </Row>
      <Row>
        <Col>
          <Button className={styles.button} variant="primary" size="lg" block>
            Edit
          </Button>
        </Col>
        <Col>
          <Button className={styles.button} variant="danger" size="lg" block>
            Delete
          </Button>
        </Col>
      </Row>
    </Container>
  );
};

export default EventManagement;