import { useNavigate } from 'react-router-dom';
import { useForm } from 'react-hook-form';
import uuid from 'react-uuid';
import '../Styles/Register.css';

// Redux
import { useSelector, useDispatch } from 'react-redux';
import { login } from '../store/slices/AuthSlice.js';
import { createDashboard } from '../store/slices/DashboardSlice';


function Register({ messages, setMessages }) {
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  // Redux's state.
  const authToken = useSelector((state) => state.auth.authToken);

  const onSubmit = (userData) => {

    fetch('http://localhost:8080/user/create_account', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(userData),
    })
      .then((response) => {
        if (response.status === 201) {

          setMessages([
            ...messages,
            {
              id: uuid(),
              type: 'success',
              text: 'Account successfully created. Please sign in.',
            },
          ]);

          //Authenticate newly created account
      fetch('http://localhost:8080/user/authenticate', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(userData),
    })
  }})
      .then((response) => {
        if (response.status === 200) {
          return response.json();
        } else {
          /*
            HTTP requests to force reject:
            400 Bad Request
            401 Unauthorized
            403 Forbidden
            404 Not Found
            500 Internal Server Error
          */
          return Promise.reject(response);
        }
      })
      .then((data) => {
        // auth.login(data.jwt_token);

        console.log(data);
        // setting the global state
        dispatch(login(data));

          // Plug in Dashboard Name & User ID
          const dashboardData = {
            dashboardName: data.username + "'s Dashboard",
            userId: data.userId,
          };
          
          // setting the global state
          dispatch(createDashboard(dashboardData));

          // create dashboard for the user
          fetch('http://localhost:8080/dashboard', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json',
              Authorization: 'Bearer ' + authToken
            },
            body: JSON.stringify(dashboardData),
          })
            .then((response) => {
              if (response.status === 201) {
                console.log('Dashboard created successfully');
              } else {
                console.log('Dashboard creation failed');
              }
            })
            .catch((error) => console.log(error));
            
          navigate('/login');

        } else  if(response.status === 400) {
          setMessages([
            ...messages,
            {
              id: uuid(),
              type: 'failure',
              text: 'Account could not be created at this time.',
            },
          ]);
        } else {
          setMessages([
            ...messages,
            {
              id: uuid(),
              type: 'failure',
              text: 'Unexpected error occurred.',
            },
          ]);
        }
      })
      .catch((error) =>
        setMessages([
          ...messages,
          { id: uuid(), type: 'failure', text: error.message },
        ])
      );
  };

  return (
    <div className="row register-container">
      <div className="col-lg-4 col-md-6">
        <h3>Register</h3>
        <form id="login-form" onSubmit={handleSubmit(onSubmit)}>
          <label className="form-label mt-3" htmlFor="username">
            Username
          </label>
          <input
            className="form-control"
            type="username"
            id="username"
            {...register('username', {
              required: 'Must have a valid username.',
              minLength: {
                value: 8,
                message: 'Must be 8 characters or more.',
              },
              maxLength: {
                value: 30,
                message: 'Must be 30 characters or less.',
              },
            })}
          />
          <p className="form-error-message">{errors.username?.message}</p>

          <label className="form-label mt-3" htmlFor="user-password">
            Password
          </label>
          <input
            className="form-control"
            type="password"
            id="user-password"
            {...register('password', {
              required: 'Must have a valid password.',
              minLength: {
                value: 8,
                message: 'Must have at least 8 characters.',
              },
            })}
          />
          <p className="form-error-message">{errors.password?.message}</p>

          <button className="btn btn-primary mt-3" type="submit">
            Create Account
          </button>
          <button
            className="btn btn-secondary mt-3 ms-2"
            type="button"
            onClick={() => navigate('/')}
          >
            Cancel
          </button>

          <br />
          <br />

          <p>
            Already a member?{' '}
            <a href="#!" onClick={() => navigate('/login')}>
              {' '}
              Sign In!{' '}
            </a>
          </p>
        </form>
      </div>
    </div>
  );
}

export default Register;
