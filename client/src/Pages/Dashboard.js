import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { Sidebar } from 'react-pro-sidebar';
import '../Styles/Dashboard.css';

// Redux
import { useSelector, useDispatch } from 'react-redux';
import { login } from '../store/slices/AuthSlice.js';

// Components
import NoteWidget from '../Widgets/NoteWidget.js';
import SideNavbar from '../Navigation/SideNavbar.js';

function Dashboard() {
  const navigate = useNavigate();
  const dispatch = useDispatch();

  //Redux's State
  const authToken = useSelector((state) => state.auth.authToken);
  const authUserId = useSelector((state) => state.auth.userId);
  const authUsername = useSelector((state) => state.auth.username);
  const authAuthorities = useSelector((state) => state.auth.authorities);

  const [showNoteWidget, setShowNoteWidget] = useState(false);

  // const callDashboard = () => {
  // on load send fetch to find by id
  fetch(`http://localhost:8080/dashboard/user/${authUserId}`, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
      Authorization: 'Bearer ' + authToken,
    },
  })
    .then((response) => {
      if (response.status === 200) {
        return response.json();
      } else if (response.status === 404) {
        // if the id is not found, create a new Dashboard
        return fetch('http://localhost:8080/dashboard', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
            Authorization: 'Bearer ' + authToken,
          },
        }).then((response) => {
          if (response.status === 201) {
            return response.json();
          } else {
            throw new Error('Could not create Dashboard.');
          }
        });
      } else {
        throw new Error('An unexpected error occurred.');
      }
    })
    .then((data) => {
      if (data) {
        // render the data
        console.log(data);
      }
    })
    .catch((error) => console.log(error));

  const handleNoteButtonClick = () => {
    setShowNoteWidget(!showNoteWidget);
  };

  return (
    <div className="dashboard-container">
      <div>
        <Sidebar>
          <SideNavbar handleNoteButtonClick={handleNoteButtonClick} />
        </Sidebar>
      </div>

      <div>{showNoteWidget && <NoteWidget />}</div>
    </div>
  );
}

export default Dashboard;
