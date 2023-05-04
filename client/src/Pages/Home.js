import React from 'react';
import Button from 'react-bootstrap/Button';
import { useNavigate } from 'react-router-dom';
import '../Styles/Home.css';

function Home() {
  const navigate = useNavigate();

  return (
    <div className="text-center">
      <h1 className="display-3">Productivity Dashboard Home</h1>

      <div className="home-buttons">
        <Button onClick={() => navigate('/login')}>Login</Button>
        <Button onClick={() => navigate('/register')}>Register</Button>
      </div>
    </div>
  );
}

export default Home;
