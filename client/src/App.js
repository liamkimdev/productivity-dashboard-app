import React from 'react';
import { useState, useEffect } from 'react';
import jwtDecode from 'jwt-decode';
import { Sidebar } from 'react-pro-sidebar';
import { Routes, Route, Navigate } from 'react-router-dom';

import './Styles/App.css';

import AuthContext from './Contexts/AuthContext';
import Navbar from '../src/Navigation/Navbar';
import SideNavbar from './Navigation/SideNavbar';
import Home from './Components/Home';
import Login from './Utilities/Login';
import NotFound from './Utilities/NotFound';
import Register from './Utilities/Register';
import MessageFactory from './Utilities/MessageFactory';
import Dashboard from './Components/Dashboard';

const LOCAL_STORAGE_TOKEN_KEY = 'dashbProdToken';

function App() {
  const [messages, setMessages] = useState([]);
  const [dashboard, setDashboard] = useState([]);
  const [currentUser, setCurrentUser] = useState(null);

  useEffect(() => {
    const token = localStorage.getItem(LOCAL_STORAGE_TOKEN_KEY);

    if (token) {
      login(token);
    }
  }, []);

  const login = (token) => {
    const {
      sub: username,
      authorities: authoritiesString,
      user_id: userId,
    } = jwtDecode(token);

    const roles = authoritiesString.split(',');

    localStorage.setItem(LOCAL_STORAGE_TOKEN_KEY, token);

    const user = {
      userId,
      username,
      roles,
      token,
      hasRole(role) {
        return this.roles.includes(role);
      },
    };

    setCurrentUser(user);

    return user;
  };

  const logout = () => {
    setCurrentUser(null);
    localStorage.removeItem(LOCAL_STORAGE_TOKEN_KEY);
  };

  const auth = {
    currentUser: currentUser ? { ...currentUser } : null,
    login,
    logout,
  };

  return (
    <AuthContext.Provider value={auth}>
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
            <Route
              path="/dashboard"
              element={
                currentUser ? (
                  <Dashboard
                    dashboard={dashboard}
                    setDashboard={setDashboard}
                    messages={messages}
                    setMessages={setMessages}
                  />
                ) : (
                  <Navigate to="/" />
                )
              }
            />
            <Route
              path="/register"
              element={
                <Register messages={messages} setMessages={setMessages} />
              }
            />
            <Route path="/notFound" element={<NotFound />} />
          </Routes>
        </div>
      </div>
    </AuthContext.Provider>
  );
}

export default App;
