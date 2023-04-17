import React from 'react';

function Dashboard({ messages, setMessages }) {
  // const onSubmit = (carData) => {
  //   let reviseCarData = {
  //     ...carData,
  //     userId: auth.currentUser.userId,
  //   };

  //   fetch('http://localhost:8080/dashboard', {
  //     method: 'POST',
  //     headers: {
  //       'Content-Type': 'application/json',
  //       Authorization: 'Bearer ' + auth.currentUser.token,
  //     },
  //     body: JSON.stringify(reviseCarData),
  //   })
  //     .then((response) => {
  //       if (response.status === 201) {
  //         setMessages([
  //           ...messages,
  //           {
  //             id: makeId(),
  //             type: 'success',
  //             text: 'Car successfully created.',
  //           },
  //         ]);
  //         return response.json();
  //       } else if (response.status === 403) {
  //         setMessages([
  //           ...messages,
  //           {
  //             id: makeId(),
  //             type: 'failure',
  //             text: 'Car could not be created.',
  //           },
  //         ]);
  //         navigate('/api/ride_on/car/form');
  //       } else {
  //         setMessages([
  //           ...messages,
  //           {
  //             id: makeId(),
  //             type: 'failure',
  //             text: 'An unexpected error occurred.',
  //           },
  //         ]);
  //         navigate('/');
  //       }
  //     })
  //     .then((data) => {
  //       if (data) {
  //         addCarToCurrentUser(data);
  //         navigate('/transport');
  //       }
  //     })
  //     .catch((error) => console.log(error));
  // };

  return <div>Dashboard</div>;
}

export default Dashboard;
