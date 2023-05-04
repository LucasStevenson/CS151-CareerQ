import React,{ useState, useEffect } from "react";
import { Container, Form, Button } from "react-bootstrap";
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

  const handleJoinQueue = async () => {
    const response = await fetch(`http://localhost:8080/companies/${selectedCompany}/queue`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        companyType: "companyType",
        name: "name",
        studentID: "studentID"
      })
    });
    const data = await response.json();
    setQueuePosition(data.position);
    setQueueLength(data.queueLength);
    setEstimatedWaitTime(data.estimatedWaitTime);
  };

  const handleLeaveQueue = async () => {
    const response = await fetch(`http://localhost:8080/company/${selectedCompany}/queue/123456`, {
      method: "DELETE"
    });
    const data = await response.json();
    setQueuePosition(null);
    setQueueLength(data.queueLength);
    setEstimatedWaitTime(data.estimatedWaitTime);
  };

  return (
    <Container>
    <div className="my-5"style={{ paddingTop: "5px" }}>
      <h1 className="mb-4 font-weight-normal text-center">Student Dashboard</h1>
      <h2 className="mb-4 font-weight-normal text-center">Browse Companies</h2>
      
      <div className="d-flex justify-content-center">
      <Form.Group controlId="formBasicUserType" >
        <Form.Label>Please select your Company where you want to Apply</Form.Label>
        <Form.Control
            as="select"
            value={companyType}
            onChange={(e) => setCompanyType(e.target.value)}
          >
          <option value="" disabled>
            Select company
          </option>
          <option value="Company">Netflix</option>
          <option value="Company">Nvidia</option>
          <option value="Company">Amazon</option>
          <option value="Company">Google</option> 
        </Form.Control>
      </Form.Group>
      </div>
      <ul>
        {companies.map((company) => (
          <li key={company.id}>
            <button onClick={() => setSelectedCompany(company.id)}>{company.name}</button>
          </li>
        ))}
      </ul>
      {selectedCompany && (
        <div>
          <h3>Check Queue Status{{ paddingTop: "5px" }}</h3>
          <p>Queue Length: {queueLength}</p>
          <p>Estimated Wait Time: {estimatedWaitTime}</p>
          {queuePosition !== null ? (
            <div>
              <p>Your queue position is: {queuePosition}</p>
              <Button onClick={handleLeaveQueue}>Leave Queue</Button>
            </div>
          ) : (
            <Button onClick={handleJoinQueue}>Join Queue</Button>
          )}
        </div>
      )}
      <div className="d-flex justify-content-center">
      <Button variant="primary" type="submit" block size="lg" className="mb-4 font-weight-normal text-center">
        <Link to="/Login" style={{ color: 'white' }}>Logout </Link>
      </Button>
      </div>
    </div>
    </Container>
  );
};

export default Student;