import React from 'react';
import { useState } from 'react';
import jwtDecode from 'jwt-decode';
import { Sidebar } from 'react-pro-sidebar';

import Navbar from '../src/Navigation/Navbar';

const LOCAL_STORAGE_TOKEN_KEY = 'dashboardToken';

function App() {
  // const [messages, setMessages] = useState([]);

  // const [auth, setAuth] = useState({
  //   currentUser: null,
  //   login,
  //   logout,
  // });

  // const [user, setUser] = useState([]);

  // function login(token) {
  //   const {
  //     sub: username,
  //     authorities: authoritiesString,
  //     user_id: userId,
  //   } = jwtDecode(token);

  //   localStorage.setItem(LOCAL_STORAGE_TOKEN_KEY, token);

  //   fetch(`http://localhost:8080/user/${username}`, {
  //     headers: {
  //       Authorization: 'Bearer ' + token,
  //     },
  //   })
  //     .then((response) => {
  //       if (response.status === 200) {
  //         return response.json();
  //       } else if (response.status === 403) {
  //         // setMessages([
  //         //   ...messages,
  //         //   {
  //         //     id: makeId(),
  //         //     type: "failure",
  //         //     text: "Car could not be registered.",
  //         //   },
  //         // ]);
  //       } else {
  //         // setMessages([
  //         //     ...messages,
  //         //     {
  //         //       id: makeId(),
  //         //       type: "failure",
  //         //       text: "Unexpected error occured.",
  //         //     },
  //         //   ]);
  //       }
  //     })
  //     .then((data) => {
  //       console.log(data);

  //       setUser(data.user);

  //       const roles = authoritiesString.split(',');

  //       const user = {
  //         username,
  //         userId,
  //         roles,
  //         token,
  //         hasRole(role) {
  //           return this.roles.includes(role);
  //         },
  //       };

  //       setAuth((auth) => {
  //         const updatedAuth = {
  //           ...auth,
  //           currentUser: user,
  //         };

  //         console.log('before login:', auth.currentUser?.widgets);
  //         console.log('after login:', updatedAuth.currentUser.widgets);

  //         return updatedAuth;
  //       });

  //       console.log('after set auth:', auth.currentUser?.widgets);

  //       return user;
  //     })
  //     .catch((error) => console.log(error));
  // }

  // function logout() {
  //   setAuth({
  //     ...auth,
  //     currentUser: null,
  //   });
  //   localStorage.removeItem(LOCAL_STORAGE_TOKEN_KEY);
  // }

  return (
    <div className="app">
      <div className="navbar-container">
        <Navbar />
        <Sidebar />
      </div>
    </div>
  );
}

export default App;
