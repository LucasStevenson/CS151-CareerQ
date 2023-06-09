import React, { useState } from 'react';
import { Container, Row, Col, Button, Modal, Form } from "react-bootstrap";
import styles from "../Module/EventManagement.module.css";
import { Link, useNavigate } from "react-router-dom";

const Company = () => {
    const [queue, setQueue] = useState([]);
    const [threshold, setThreshold] = useState(0);
    const [studentsInQueue, setStudentsInQueue] = useState(0);
    const [companyName, setCompanyName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [companyDescription, setCompanyDescription] = useState('');
    const [queueThreshold, setQueueThreshold] = useState(0);

    const handleAcceptStudent = (id) => {
        // implementation
    };

    const handleRemoveStudent = (id) => {
        // implementation
    };

    const useEffect = (id) => {
        // implementation
    };

    // get queue information from backend
    useEffect(() => {
        async function fetchData() {
            let rawResponse = await fetch(`http://localhost:8080/queue`, {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                },
            });
            let res = await rawResponse.json();
            setQueue(res.queue);
            setThreshold(res.threshold);
            setStudentsInQueue(res.queue.length);
        }
        fetchData();
    }, []);

    // remove a student from the queue
    const removeStudent = async (index) => {
        let updatedQueue = [...queue];
        updatedQueue.splice(index, 1);

        let rawResponse = await fetch(`http://localhost:8080/queue`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                queue: updatedQueue,
                threshold,
            }),
        });

        if (rawResponse.ok) {
            setQueue(updatedQueue);
            setStudentsInQueue(updatedQueue.length);
        }
    };

    // accept the first student in the queue
    const acceptFirstStudent = async () => {
        let updatedQueue = [...queue];
        updatedQueue.shift();

        let rawResponse = await fetch(`http://localhost:8080/queue`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                queue: updatedQueue,
                threshold,
            }),
        });

        if (rawResponse.ok) {
            setQueue(updatedQueue);
            setStudentsInQueue(updatedQueue.length);
        }
    };

    // set the maximum number of students in the queue
    const handleSetThreshold = async (event) => {
        event.preventDefault();
        let rawResponse = await fetch(`http://localhost:8080/queue`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                queue,
                threshold,
            }),
        });

        if (rawResponse.ok) {
            setThreshold(Number(event.target.threshold.value));
        }
    };

    // register the company for the career fair
    const handleRegister = async (event) => {
        event.preventDefault();
        let rawResponse = await fetch(`http://localhost:8080/register`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                name: event.target.name.value,
                industry: event.target.industry.value,
            }),
        });

        if (rawResponse.ok) {
            alert("Company Description registered!");
        }
    };

    return (
        <>
            <h1 className="mb-4 font-weight-normal text-center">Company Dashboard</h1>
            <div classname="d-flex justify-content-center">
                <h3 className="mb-4 font-weight-normal text-center" style={{ paddingTop: "20px" }}> Live Queue Status </h3>
                <h5 className="d-flex justify-content-center" style={{ paddingTop: "20px" }} >Number of students in queue: {queue.length} </h5>
                <div className="d-flex justify-content-center" style={{ paddingTop: "20px" }}>
                    <Button className="mb-4 font-weight-normal text-center" variant="primary" onClick={handleAcceptStudent}>Accept Next Student in Queue</Button>
                </div>
                <Form onSubmit={handleSetThreshold}>
                    <Form.Group controlId="queueThreshold">
                        <Form.Label className="d-flex justify-content-center" style={{ paddingTop: "20px" }}>Set Queue Threshold</Form.Label>
                        <div className="d-flex justify-content-center">
                            <Form.Control as="textarea" placeholder="Enter maximum number of students in queue" required onChange={(e) => setQueueThreshold(e.target.value)}
                                style={{ height: '30px', width: '100px' }} />
                        </div>

                    </Form.Group>

                    <div className="d-flex justify-content-center" style={{ paddingTop: "20px", paddingBottom: "50px" }} >
                        <Button variant="primary" type="submit">
                            Set Threshold
                        </Button>
                    </div>
                </Form>
            </div>
            <h3 className="mb-4 font-weight-normal text-center">Queue Management</h3>

            <Container>
                <Row>
                    <Col>
                        <div>
                            <table className={`${styles.contentTable} mx-auto`}>
                                <thead>
                                    <tr>
                                        <th>Student Name</th>
                                        <th>Position in Queue</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody className={styles.scrollableTbody}>
                                    {queue.map((student, index) => {
                                        return (
                                            <tr key={index}>
                                                <td>{student.name}</td>
                                                <td>{index + 1}</td>
                                                <td>
                                                    <Button variant="primary" onClick={() => handleRemoveStudent(index)}>Remove</Button>
                                                </td>
                                            </tr>
                                        );
                                    })}
                                    <tr>
                                        <td>Test Student 1</td>
                                        <td>1</td>
                                        <td>
                                            <div style={{ display: 'flex', gap: '30px' }}>
                                                <Button
                                                    className={styles.actionButton}
                                                    variant="primary"
                                                    size="sm"
                                                >
                                                    Accept
                                                </Button>
                                                <Button
                                                    className={styles.actionButton}
                                                    variant="danger"
                                                    size="sm"
                                                >
                                                    Deny
                                                </Button>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Test Student 2</td>
                                        <td>2</td>
                                        <td>
                                            <div style={{ display: 'flex', gap: '30px' }}>
                                                <Button
                                                    className={styles.actionButton}
                                                    variant="primary"
                                                    size="sm"
                                                >
                                                    Accept
                                                </Button>
                                                <Button
                                                    className={styles.actionButton}
                                                    variant="danger"
                                                    size="sm"
                                                >
                                                    Deny
                                                </Button>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Test Student 3</td>
                                        <td>3</td>
                                        <td>
                                            <div style={{ display: 'flex', gap: '30px' }}>
                                                <Button
                                                    className={styles.actionButton}
                                                    variant="primary"
                                                    size="sm"
                                                >
                                                    Accept
                                                </Button>
                                                <Button
                                                    className={styles.actionButton}
                                                    variant="danger"
                                                    size="sm"
                                                >
                                                    Deny
                                                </Button>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Test Student 4</td>
                                        <td>4</td>
                                        <td>
                                            <div style={{ display: 'flex', gap: '30px' }}>
                                                <Button
                                                    className={styles.actionButton}
                                                    variant="primary"
                                                    size="sm"
                                                >
                                                    Accept
                                                </Button>
                                                <Button
                                                    className={styles.actionButton}
                                                    variant="danger"
                                                    size="sm"
                                                >
                                                    Deny
                                                </Button>
                                            </div>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </Col>
                </Row >
            </Container>


            <div className="d-flex justify-content-center">
                <Button variant="primary" type="submit" block size="lg" className="mb-4 font-weight-normal text-center">
                    <Link to="/Login" style={{ color: 'white' }}>Logout </Link>
                </Button>
            </div>
        </>
    );
}

export default Company;
