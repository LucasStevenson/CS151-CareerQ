import React, { useState, useEffect } from "react";
import { Container, Form, Button } from "react-bootstrap";
import styles from "../Module/EventManagement.module.css";
import { Link, useNavigate } from "react-router-dom";

const Student = () => {
  const [companies, setCompanies] = useState([]);
  const [companyType, setCompanyType] = useState("");
  const [selectedCompany, setSelectedCompany] = useState(null);
  const [studentID, setStudentID] = useState(null);
  const [queuePosition, setQueuePosition] = useState(null);
  const [queueLength, setQueueLength] = useState(null);
  const [estimatedWaitTime, setEstimatedWaitTime] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    async function fetchCompanies() {
      const response = await fetch("http://localhost:8080/student");
      const data = await response.json();
      setCompanies(data);
    }
    fetchCompanies();
  }, []);

  useEffect(() => {
    async function fetchQueueStatus() {
      if (selectedCompany) {
        const response = await fetch(
          `http://localhost:8080/companies/${selectedCompany}/queue/status`
        );
        const data = await response.json();
        setQueueLength(data.queueLength);
        setEstimatedWaitTime(data.estimatedWaitTime);
      }
    }
    fetchQueueStatus();
  }, [selectedCompany]);

  const handleJoinQueue = async () => {
    const response = await fetch(
      `http://localhost:8080/companies/${selectedCompany}/queue`,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          companyType: companyType,
          name: "name",
          studentID: studentID,
        }),
      }
    );
    const data = await response.json();
    setQueuePosition(data.position);
    setQueueLength(data.queueLength);
    setEstimatedWaitTime(data.estimatedWaitTime);
  };

  const handleLeaveQueue = async () => {
    const response = await fetch(
      `http://localhost:8080/company/${selectedCompany}/queue/${queuePosition}`,
      {
        method: "DELETE",
      }
    );
    const data = await response.json();
    setQueuePosition(null);
    setQueueLength(data.queueLength);
    setEstimatedWaitTime(data.estimatedWaitTime);
  };

  return (
    <Container>
      <div className="my-5" style={{ paddingTop: "5px" }}>
        <h1 className="mb-4 font-weight-normal text-center">Student Dashboard</h1>
        <h2 className="mb-4 font-weight-normal text-center">Browse Companies</h2>
        <div className="d-flex justify-content-center">
          <Form.Group controlId="formBasicUserType">
            <Form.Label>
              Please select the company where you want to apply
            </Form.Label>
            <Form.Control
              as="select"
              value={companyType}
              onChange={(e) => setCompanyType(e.target.value)}
            >
              <option value="" disabled>
                Select company
              </option>
              <option value="Netflix">Netflix</option>
              <option value="Nvidia">Nvidia</option>
              <option value="Amazon">Amazon</option>
              <option value="Google">Google</option>
            </Form.Control>
          </Form.Group>
        </div>

        <ul>
          {companies.map((company) => (
            <li key={company.id}>
              <button onClick={() => setSelectedCompany(company.id)}>
                {company.name}
              </button>
              <span>Waiting: {company.waiting}</span>
              <span>Estimated Wait Time: {company.estimatedWaitTime}</span>
            </li>
          ))}
        </ul>

        <div className="my-5" style={{ paddingTop: "5px" }}>
          <h3 className="mb-4 font-weight-normal text-center">Check Queue Status</h3>
          <div className="d-flex justify-content-center">
            <p>Queue Length: {queueLength}</p>
          </div>
          <div className="d-flex justify-content-center">
            <p>Estimated Wait Time: {estimatedWaitTime}</p>
          </div>
            <div className="d-flex justify-content-center">
                <p>Your queue position is: {queuePosition}</p>
                </div>
          <div className="d-flex justify-content-center" style={{ display: 'flex', gap: '100px' }}>
            <Button onClick={handleJoinQueue}>Join Queue</Button>
            <Button 
                    variant="danger"
                    onClick={handleLeaveQueue}>Leave Queue</Button>
          </div>
          </div>

        <div className="d-flex justify-content-center">
          <Button
            variant="primary"
            type="submit"
            block
            size="lg"
            className="mb-4 font-weight-normal text-center"
          >
            <Link to="/Login" style={{ color: "white" }}>
              Logout{" "}
            </Link>
          </Button>
        </div>
      </div>
    </Container>
  )
}
export default Student;

