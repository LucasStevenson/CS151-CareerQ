import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import { Button } from "react-bootstrap";
import jwt_decode from "jwt-decode";

const SingleEvent = () => {
  const [eventInfo, setEventInfo] = useState(null);
  const [rerun, setRerun] = useState(false); // this is for re-running the useEffect() method, which fetches the latest data for a specific event
  const { id } = useParams();

  const token = localStorage.getItem("token");

  useEffect(() => {
    async function fetchData() {
      try {
        const response = await fetch(`http://localhost:8080/events/${id}`);
        const data = await response.json();
        console.log(data);
        setEventInfo(data);
      } catch (error) {
        console.error(error);
      }
    }
    fetchData();
  }, [rerun]);

  const joinCompanyWaitingList = async (email) => {
    let rawResponse = await fetch(`http://localhost:8080/joinCompanyQueue?companyEmail=${email}`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`
        }
    });
    let res = await rawResponse.text();
    setRerun(!rerun); // want to rerun the useEffect() method 
    alert(res);
  }

  const acceptCompanyFromWaitList = async (eventID, companyEmail) => {
    let rawResponse = await fetch(`http://localhost:8080/events/${eventID}/acceptCompany?companyEmail=${companyEmail}`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`
        }
    })
    // refetch the data from the backend so that the page updates live 
    setRerun(!rerun);  // by doing this, when a company is accepted from the waitlist, they automatically get moved from the waitlist list to the participating companies list on the user's screen without the need to refresh
  }

  if (!eventInfo) {
    return <div>Loading...</div>;
  }

  return (
    <div className="container my-5">
      <div className="row">
        <div className="col-md-6 mx-auto">
          <div className="card">
            <div className="card-header bg-dark text-white">Event Information</div>
            <div className="card-body">
              <p>
                <strong>Event ID:</strong> {eventInfo.eventID}
              </p>
              <p>
                <strong>Host:</strong> {eventInfo.host}
              </p>
              <p>
                <strong>Start Time:</strong> {eventInfo.startTime}
              </p>
              <p>
                <strong>End Time:</strong> {eventInfo.endTime}
              </p>
              <div className="mt-4">
                <h5>Participating Companies</h5>
                {eventInfo.participatingCompanies.length > 0 ? (
                  <ul>
                    {eventInfo.participatingCompanies.map((company) => (
                    <div>
                      <li key={company.id}>{company.name}</li>
                      {token !== null && jwt_decode(token).uType === "student" && (
                        <Button variant="primary" className="mx-3" onClick={() => joinCompanyWaitingList(company.email)}>
                        Join Queue
                        </Button>
                      )}
                    </div>
                    ))}
                  </ul>
                ) : (
                  <p>No participating companies</p>
                )}
              </div>
              <div className="mt-4">
                <h5>Waiting List</h5>
                {eventInfo.waitingList.length > 0 ? (
                  <ul>
                    {eventInfo.waitingList.map((company) => (
                        <div>
                      <li key={company.id}>{company.name}</li>
                      {token !== null && jwt_decode(token).email === eventInfo.host && (
                        <Button variant="primary" onClick={() => acceptCompanyFromWaitList(eventInfo.eventID, company.email)} >
                        Accept
                      </Button>
                      )}
                      </div>
                    ))}
                  </ul>
                ) : (
                  <p>No users on the waiting list</p>
                )}
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default SingleEvent;
