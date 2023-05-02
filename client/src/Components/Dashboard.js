import React from 'react';
import { useNavigate } from 'react-router-dom';

// Redux
import { useSelector, useDispatch } from 'react-redux';
import { login } from '../store/slices/AuthSlice.js';

function Dashboard() {
  const navigate = useNavigate();
  const dispatch = useDispatch();

  //Redux's State
  const authToken = useSelector((state) => state.auth.authToken);
  const authUserId = useSelector((state) => state.auth.userId);
  const authUsername = useSelector((state) => state.auth.username);
  const authAuthorities = useSelector((state) => state.auth.authorities);

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

  return <div>Dashboard</div>;
}

export default Dashboard;
