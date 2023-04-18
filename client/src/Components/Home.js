import React from 'react';
import Button from 'react-bootstrap/Button';

function Home() {
  return (
    <div className="text-center">
      <h1 className="display-3">Productivity Dashboard Home</h1>
      {/* Button to create the dashboard - /dashboard */}
      <Button variant="primary">Create a Dashboard</Button>
    </div>
  );
}

export default Home;
