import React from 'react';
import { useState } from 'react';
import { Sidebar } from 'react-pro-sidebar';
import { Routes, Route, Navigate } from 'react-router-dom';
import { useSelector } from 'react-redux';

import './Styles/App.css';

import Navbar from '../src/Navigation/Navbar';
import SideNavbar from './Navigation/SideNavbar';
import Home from './Components/Home';
import Login from './Utilities/Login';
import NotFound from './Utilities/NotFound';
import Register from './Utilities/Register';
import Message from './Utilities/Message';
import Dashboard from './Components/Dashboard';

function App() {
  const [dashboard, setDashboard] = useState([]);

  //Redux's state.
  const authToken = useSelector((state) => state.auth.authToken);
  const userId = useSelector((state) => state.auth.userId);

  return (
    <div className="app">
      <div className="navbar-container">
        <Navbar />

        <div className="">
          <Sidebar>
            <SideNavbar />
          </Sidebar>
        </div>

        <Message />

        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<Login />} />
          <Route
            path={`/dashboard/user/${userId}`}
            element={
              <Dashboard dashboard={dashboard} setDashboard={setDashboard} />
            }
          />
          <Route path="/register" element={<Register />} />
          <Route path="/notFound" element={<NotFound />} />
        </Routes>
      </div>
    </div>
  );
}

export default App;
