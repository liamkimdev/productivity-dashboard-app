import React from 'react';
import { useState } from 'react';
import jwtDecode from 'jwt-decode';
import { Sidebar } from 'react-pro-sidebar';
import { Routes, Route } from 'react-router-dom';

import './Styles/App.css';

import AuthContext from './Contexts/AuthContext';
import Navbar from '../src/Navigation/Navbar';
import SideNavbar from './Navigation/SideNavbar';
import Home from './Components/Home';
import Login from './Utilities/Login';
import NotFound from './Utilities/NotFound';
import Register from './Utilities/Register';
import MessageFactory from './Utilities/MessageFactory';

function App() {
  const [messages, setMessages] = useState([]);
  return (
      <div className="app">
        <div className="navbar-container">
          <Navbar />
          <Sidebar>
            <SideNavbar />
          </Sidebar>

          <MessageFactory messages={messages} setMessages={setMessages} />

          <Routes>
            <Route path="/" element={<Home />} />
            <Route
              path="/login"
              element={<Login messages={messages} setMessages={setMessages} />}
            />
            <Route path="/register" element={<Register />} />
            <Route path="/notFound" element={<NotFound />} />
          </Routes>
        </div>
      </div>
  );
}

export default App;
