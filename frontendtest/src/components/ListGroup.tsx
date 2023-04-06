import { Fragment } from "react";

function ListGroup() {
  let items = ["New York", "SF", "LA", "Tokyo"];
  items = [];

  const getMessage = () => {
    return items.length === 0 ? <p>No item found</p> : null;
  };

  return (
    <Fragment>
      <h1>List</h1>
      {getMessage()}
      <ul className="list-group">
        {items.map((item) => (
          <li key={item}>{item}</li>
        ))}
      </ul>
    </Fragment>
  );
}

export default ListGroup;
