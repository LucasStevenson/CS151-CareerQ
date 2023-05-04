import { useState, useEffect } from "react";
import { Container, Row, Col, Card, Button } from "react-bootstrap";

const StudentPage = () => {
  const [events, setEvents] = useState([]);

  useEffect(() => {
    async function fetchData() {
      let rawResponse = await fetch(`http://localhost:8080/student`, {
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
      <h1 className="text-center my-5">Student Dashboard</h1>
      <Row>
        {events.map((event) => (
          <Col key={event.id}>
            <Card className="mb-3">
              <Card.Img variant="top" src={event.image_url} />
              <Card.Body>
                <Card.Title>{event.title}</Card.Title>
                <Card.Text>{event.description}</Card.Text>
                <Button variant="primary" href={`/events/${event.id}`}>
                  View Details
                </Button>
              </Card.Body>
            </Card>
          </Col>
        ))}
      </Row>
    </Container>
  );
};

export default StudentPage;