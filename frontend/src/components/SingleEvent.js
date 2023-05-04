import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";

const SingleEvent = () => {
  const [eventInfo, setEventInfo] = useState(null);
  const { id } = useParams();

  useEffect(() => {
    async function fetchData() {
      try {
        const response = await fetch(`http://localhost:8080/events/${id}`);
        const data = await response.json();
        setEventInfo(data);
      } catch (error) {
        console.error(error);
      }
    }
    fetchData();
  }, [id]);

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
                <strong>Date:</strong> {eventInfo.day}
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
                      <li key={company}>{company}</li>
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
                    {eventInfo.waitingList.map((user) => (
                      <li key={user}>{user}</li>
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

