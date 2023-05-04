import { useState, useEffect } from "react";
import { Container, Row, Col, Button, Card } from "react-bootstrap";

const Events = () => {
    const [events, setEvents] = useState([]);
    useEffect(() => {
        async function fetchData() {
            let rawResponse = await fetch(`http://localhost:8080/events`, {
                method: "GET",
                headers: {
                    "Content-Type": "application/json"
                },
            });
            console.log(rawResponse);
            let res = await rawResponse.json();
            setEvents(res);
            console.log(events);
        }
        fetchData();
    }, []) // this runs only on the first render
    // how to make it so that it runs whenever a new event is added?

    return (
        <Container>
          <h1 className="text-center my-5">Events</h1>
          <Row>
            {events.map((event, index) => (
              <Col key={event.eventID}>
                <Card className="mb-3">
                  <Card.Img variant="top" src={event.image_url} />
                  <Card.Body>
                    <Card.Title>{event.host}</Card.Title>
                    <Card.Text>Day: {event.day}</Card.Text>
                    <Card.Text>Time Frame: {event.startTime} - {event.endTime}</Card.Text>
                    <Button variant="primary" href={`/events/${event.eventID}`}>
                      View Details
                    </Button>
                  </Card.Body>
                </Card>
              </Col>
            ))}
          </Row>
        </Container>
      );
}

export default Events;
