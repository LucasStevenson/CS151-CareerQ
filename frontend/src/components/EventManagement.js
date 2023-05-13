import React, { useState, useEffect } from "react";
import { Container, Row, Col, Button, Modal, Form } from "react-bootstrap";
import styles from "../Module/EventManagement.module.css";

const EventManagement = () => {
  const [events, setEvents] = useState([]);
  const [loading, setLoading] = useState(true);
  const [rerun, setRerun] = useState(false); // this is for re-running the useEffect() method
  
  const [startTime, setStartTime] = useState("");
  const [endTime, setEndTime] = useState("");

  const [show, setShow] = useState(false);
  const [showEdit, setShowEdit] = useState(false);
  const [selectedEvent, setSelectedEvent] = useState(null);

  const token = localStorage.getItem("token");

  // create an event
  const createEvent = async () => {
      let rawResponse = await fetch(`http://localhost:8080/create-event`, {
          method: "POST",
          headers: {
              "Content-Type": "application/json",
              Authorization: `Bearer ${token}`
          },
          body: JSON.stringify({   // stringify the request body object
              "startTime": startTime,
              "endTime": endTime
          })
      })
      setRerun(!rerun); // we want to refetch the events after creating a new one
      handleClose();
  }

  // delete event function
  const deleteEvent = async (id) => {
    let rawResponse = await fetch(`http://localhost:8080/remove-event/${id}`, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`
      },
    })
    setEvents(events.filter((event) => event.eventID !== id)); // update the state
  };

  useEffect(() => {
    async function fetchData() {
      let rawResponse = await fetch(`http://localhost:8080/my-events`, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`
        },
      });
      let res = await rawResponse.json();
      setEvents(res);
      setLoading(false);
    }
    fetchData();
  }, [rerun]);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
  const handleShowEdit = (event) => {
    setSelectedEvent(event);
    setShowEdit(true);
  };
  const handleCloseEdit = () => setShowEdit(false);

  const formatDate = (dateString) => { // dateString: "2023-05-26T20:55"
      // convert `dateString` into a normal string with this format: mm/dd/yyyy at 00:00
      let [date, time] = dateString.split("T");
      let [year, month, day] = date.split("-");
      return `${month}/${day}/${year} at ${time}`;
  };

  if (loading) {
    return "Fetching data...";
  }

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
            {events.map((event) => (
              <tr key={event.eventID}>
                <td>{event.eventID}</td>
                <td>{event.host}</td>
                <td>{event.startTime}</td>
                <td>{event.endTime}</td>
                <td>{event.waitingList.length}</td>
                <td>{event.participatingCompanies.length}</td>
                <td>
                  <Button className={styles.actionButton} variant="primary" size="sm">
                    Edit
                  </Button>
                  <Button
                    className={styles.actionButton}
                    variant="danger"
                    size="sm"
                    onClick={() => deleteEvent(event.eventID)}
                  >
                    Delete
                  </Button>
                </td>
              </tr>
            ))}
              
            </tbody>
          </table>
        </Col>
      </Row>
      {/* Add Event Modal */}
      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Add Event</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form>
            <Form.Group controlId="eventStartTime">
              <Form.Label>Start Time</Form.Label>
              <Form.Control type="datetime-local" onChange={(e) => setStartTime(formatDate(e.target.value))}/>
            </Form.Group>

            <Form.Group controlId="eventEndTime">
              <Form.Label>End Time</Form.Label>
              <Form.Control type="datetime-local" onChange={(e) => setEndTime(formatDate(e.target.value))} />
            </Form.Group>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button variant="primary" onClick={createEvent}>
            Add Event
          </Button>
        </Modal.Footer>
      </Modal>

      {/* Edit Event Modal */}
      <Modal show={showEdit} onHide={handleCloseEdit}>
        <Modal.Header closeButton>
          <Modal.Title>Edit Event</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          {selectedEvent && (
            <Form>
              <Form.Group controlId="eventStartTime">
                <Form.Label>Start Time</Form.Label>
                <Form.Control
                  type="datetime-local"
                  defaultValue={selectedEvent.startTime}
                />
              </Form.Group>

              <Form.Group controlId="eventEndTime">
                <Form.Label>End Time</Form.Label>
                <Form.Control
                  type="datetime-local"
                  defaultValue={selectedEvent.endTime}
                />
              </Form.Group>
            </Form>
          )}
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleCloseEdit}>
            Close
          </Button>
          <Button variant="primary" onClick={handleCloseEdit}>
            Save Changes
          </Button>
        </Modal.Footer>
      </Modal>
    </Container>
  );
};

export default EventManagement;
