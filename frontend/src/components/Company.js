import React, { useState } from 'react';
import { Button, Form, Table } from 'react-bootstrap';

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

    const handlePrioritizeStudent = (id) => {
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

    // prioritize a student in the queue
    const prioritizeStudent = async (index) => {
        let updatedQueue = [...queue];
        let student = updatedQueue.splice(index, 1)[0];
        updatedQueue.unshift(student);

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
            alert("Company registered!");
        }
    };

    return (
        <>
            <h1>Company Dashboard</h1>
            <div>
                <h3>Register for the Career Fair</h3>
                <Form onSubmit={handleRegister}>
                    <Form.Group controlId="companyName">
                        <Form.Label>Company Name</Form.Label>
                        <Form.Control type="text" placeholder="Enter company name" required onChange={(e) => setCompanyName(e.target.value)} />
                    </Form.Group>
                    <Form.Group controlId="email">
                        <Form.Label>Email</Form.Label>
                        <Form.Control type="email" placeholder="Enter email" required onChange={(e) => setEmail(e.target.value)} />
                    </Form.Group>
                    <Form.Group controlId="password">
                        <Form.Label>Password</Form.Label>
                        <Form.Control type="password" placeholder="Password" required onChange={(e) => setPassword(e.target.value)} />
                    </Form.Group>
                    <Form.Group controlId="companyDescription">
                        <Form.Label>Company Description</Form.Label>
                        <Form.Control as="textarea" rows={3} placeholder="Enter company description" required onChange={(e) => setCompanyDescription(e.target.value)} />
                    </Form.Group>
                    <Button variant="primary" type="submit">
                        Register
                    </Button>
                </Form>
            </div>
            <div>
                <h3>Queue Status</h3>
                <p>Number of students in queue: {queue.length}</p>
                <Button variant="primary" onClick={handleAcceptStudent}>Accept First Student in Queue</Button>
                <Form onSubmit={handleSetThreshold}>
                    <Form.Group controlId="queueThreshold">
                        <Form.Label>Queue Threshold</Form.Label>
                        <Form.Control type="number" placeholder="Enter maximum number of students in queue" required onChange={(e) => setQueueThreshold(e.target.value)} />
                    </Form.Group>
                    <Button variant="primary" type="submit">
                        Set Threshold
                    </Button>
                </Form>
            </div>

            <div>
                <h3>Queue Management</h3>
                <Table striped bordered hover>
                    <thead>
                        <tr>
                            <th>Student Name</th>
                            <th>Position in Queue</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        {queue.map((student, index) => {
                            return (
                                <tr key={index}>
                                    <td>{student.name}</td>
                                    <td>{index + 1}</td>
                                    <td>
                                        <Button variant="primary" onClick={() => handleRemoveStudent(index)}>Remove</Button>
                                        <Button variant="secondary" onClick={() => handlePrioritizeStudent(index)}>Prioritize</Button>
                                    </td>
                                </tr>
                            );
                        })}
                    </tbody>
                </Table>
            </div>
        </>
    );
}

export default Company;