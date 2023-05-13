import { useState, useEffect } from "react";
import { Container, Row, Col, Button, Card } from "react-bootstrap";
import jwt_decode from "jwt-decode";

const Events = () => {
    const [events, setEvents] = useState([]);
    const token = localStorage.getItem("token");
    useEffect(() => {
        async function fetchData() {
            let rawResponse = await fetch(`http://localhost:8080/events`, {
                method: "GET",
                headers: {
                    "Content-Type": "application/json"
                },
            });
            let res = await rawResponse.json();
            setEvents(res);
            console.log(events);
        }
        fetchData();
    }, []) // this runs only on the first render
    
    const companyJoinEvent = async (eventID) => {
        let rawResponse = await fetch(`http://localhost:8080/join-event/${eventID}`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${token}`
            }
        });
        let res = await rawResponse.text();
        alert(res);
    }

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
                    <Card.Text>Start Time: {event.startTime}</Card.Text>
                    <Card.Text>End Time: {event.endTime}</Card.Text>
                    <Button variant="primary" href={`/events/${event.eventID}`}>
                      View Details
                    </Button>
                    {token !== null && jwt_decode(token).uType === "company" && (
                        <Button variant="primary" className="mx-3" onClick={() => companyJoinEvent(event.eventID)}>
                            Join Event
                        </Button>
                    )}
                  </Card.Body>
                </Card>
              </Col>
            ))}
          </Row>
        </Container>
      );
}

export default Events;
