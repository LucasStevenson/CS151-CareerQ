import React, { useState, useEffect } from "react";
import { Container, Row, Col, Button, Modal, Form } from "react-bootstrap";
import styles from "../Module/EventManagement.module.css";

const EventManagement = () => {
  const [events, setEvents] = useState([]);
  const [show, setShow] = useState(false);

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

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  return (
    <Container>
      <Row>
        <Col>
          <div className={styles.header}>
            <h1> Event Management</h1>

            <Button
              className={styles.addButton}
              variant="primary"
              onClick={handleShow}
            >
              Add Event
            </Button>
          </div>

          <table className={styles.contentTable}>
            <thead>
              <tr>
                <th>Event ID</th>
                <th>Host</th>
                <th>Start Time</th>
                <th>End Time</th>
                <th>Waiting List</th>
                <th>Participating Companies</th>
                <th> </th>
              </tr>
            </thead>

            <tbody className={styles.scrollableTbody}>
              <tr>
                <td>001</td>
                <td>SJSU</td>
                <td>2023-01-01 10:00:00</td>
                <td>2023-01-01 12:00:00</td>
                <td>5</td>
                <td>Company A, Company B</td>
                <td>
                  <Button
                    className={styles.actionButton}
                    variant="primary"
                    size="sm"
                  >
                    Edit
                  </Button>
                  <Button
                    className={styles.actionButton}
                    variant="danger"
                    size="sm"
                  >
                    Delete
                  </Button>
                </td>
              </tr>
              <tr>
                <td>001</td>
                <td>SJSU</td>
                <td>2023-01-01 10:00:00</td>
                <td>2023-01-01 12:00:00</td>
                <td>5</td>
                <td>Company A, Company B</td>
                <td>
                  <Button
                    className={styles.actionButton}
                    variant="primary"
                    size="sm"
                  >
                    Edit
                  </Button>
                  <Button
                    className={styles.actionButton}
                    variant="danger"
                    size="sm"
                  >
                    Delete
                  </Button>
                </td>
              </tr>
              <tr>
                <td>001</td>
                <td>SJSU</td>
                <td>2023-01-01 10:00:00</td>
                <td>2023-01-01 12:00:00</td>
                <td>5</td>
                <td>Company A, Company B</td>
                <td>
                  <Button
                    className={styles.actionButton}
                    variant="primary"
                    size="sm"
                  >
                    Edit
                  </Button>
                  <Button
                    className={styles.actionButton}
                    variant="danger"
                    size="sm"
                  >
                    Delete
                  </Button>
                </td>
              </tr>
              <tr>
                <td>001</td>
                <td>SJSU</td>
                <td>2023-01-01 10:00:00</td>
                <td>2023-01-01 12:00:00</td>
                <td>5</td>
                <td>Company A, Company B</td>
                <td>
                  <Button
                    className={styles.actionButton}
                    variant="primary"
                    size="sm"
                  >
                    Edit
                  </Button>
                  <Button
                    className={styles.actionButton}
                    variant="danger"
                    size="sm"
                  >
                    Delete
                  </Button>
                </td>
              </tr>
              <tr>
                <td>001</td>
                <td>SJSU</td>
                <td>2023-01-01 10:00:00</td>
                <td>2023-01-01 12:00:00</td>
                <td>5</td>
                <td>Company A, Company B</td>
                <td>
                  <Button
                    className={styles.actionButton}
                    variant="primary"
                    size="sm"
                  >
                    Edit
                  </Button>
                  <Button
                    className={styles.actionButton}
                    variant="danger"
                    size="sm"
                  >
                    Delete
                  </Button>
                </td>
              </tr>
              <tr>
                <td>001</td>
                <td>SJSU</td>
                <td>2023-01-01 10:00:00</td>
                <td>2023-01-01 12:00:00</td>
                <td>5</td>
                <td>Company A, Company B</td>
                <td>
                  <Button
                    className={styles.actionButton}
                    variant="primary"
                    size="sm"
                  >
                    Edit
                  </Button>
                  <Button
                    className={styles.actionButton}
                    variant="danger"
                    size="sm"
                  >
                    Delete
                  </Button>
                </td>
              </tr>

              {
                //Populate the table with the events from the database
                /* {events.map((event) => (
                <tr key={event.eventID}>
                  <td>{event.eventID}</td>
                  <td>{event.host}</td>
                  <td>{event.startTime}</td>
                  <td>{event.endTime}</td>
                  <td>{event.waitingList.join(', ')}</td>
                  <td>{event.participatingCompanies.join(', ')}</td>
                  <td>
                  <Button className={styles.actionButton} variant="primary" size="sm">
                    Edit
                  </Button>
                  <Button className={styles.actionButton} variant="danger" size="sm">
                    Delete
                  </Button>
                </td>
                </tr>
              ))} */
              }
            </tbody>
          </table>

          {/* Modal (Popup) for adding an event */}
        </Col>
      </Row>
      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Add Event</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form>
            <Form.Group controlId="eventHost">
              <Form.Label>Host</Form.Label>
              <Form.Control type="text" placeholder="Enter host" />
            </Form.Group>

            <Form.Group controlId="eventStartTime">
              <Form.Label>Start Time</Form.Label>
              <Form.Control type="datetime-local" />
            </Form.Group>

            <Form.Group controlId="eventEndTime">
              <Form.Label>End Time</Form.Label>
              <Form.Control type="datetime-local" />
            </Form.Group>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button variant="primary" onClick={handleClose}>
            Add Event
          </Button>
        </Modal.Footer>
      </Modal>
    </Container>
  );
};

export default EventManagement;
