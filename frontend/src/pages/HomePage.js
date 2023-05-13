import React from 'react';
import { Button, Container, Row, Col } from 'react-bootstrap';
import Navbar from "../components/Navbar";

const HomePage = () => {
    return (
        <div>
            <Navbar />
            <div style={{ textAlign: 'center', margin: '50px auto' }}>
                <div className="jumbotron jumbotron-fluid">
                    <div className="container">
                        <h1 className="display-4">Welcome to CareerQ</h1>
                        <p className="lead">A virtual queueing system that aims to improve the career fair experience for everyone</p>
                        <Button variant="primary" href={`/register`}>Sign Up Now</Button>
                    </div>
                </div>
                <div style={{ height: '50px' }}></div> {/* Add some space between the sections */}
                <Container>
                    <Row>
                        <Col>
                            <h2>Discover Events</h2>
                            <p>See if and when your school is hosting a career fair</p>
                        </Col>
                        <Col>
                            <h2>Browse Companies</h2>
                            <p>View the list of companies attending the career fair</p>
                        </Col>
                        <Col>
                            <h2>Join Queues</h2>
                            <p>Join queues for companies you're interested in</p>
                        </Col>
                    </Row>
                </Container>
            </div>
        </div>
    )
}

export default HomePage;
