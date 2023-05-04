import { useState, useEffect } from "react";

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
}

export default Events;
