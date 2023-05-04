import { useState, useEffect } from "react";
import { Container, Row, Col, Card, Button } from "react-bootstrap";

// Student Dashboard
const StudentPage = () => {
  const [companies, setCompanies] = useState([]);

  useEffect(() => {
    async function fetchData() {
      try {
        const response = await fetch(`http://localhost:8080/companies`, {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
          },
        });
        if (response.ok) {
          const data = await response.json();
          setCompanies(data);
        } else {
          throw new Error("Error fetching companies.");
        }
      } catch (error) {
        console.log(error);
      }
    }
    fetchData();
  }, []);

  return (
    <Container>
      <h1 className="my-4">Career Fair</h1>
      <Row>
        {companies.map((company) => (
          <Col key={company.id} md={6} className="my-2">
            <Card>
              <Card.Header as="h5">{company.name}</Card.Header>
              <Card.Body>
                <Card.Text>Queue Length: {company.queue.length}</Card.Text>
                <Card.Text>
                  Estimated Wait Time:{" "}
                  {company.queue.length * company.wait_time_per_person} mins
                </Card.Text>
                <Button variant="primary">Join Queue</Button>
              </Card.Body>
            </Card>
          </Col>
        ))}
      </Row>
    </Container>
  );
};
