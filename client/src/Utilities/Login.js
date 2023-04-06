import { useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import { useForm } from 'react-hook-form';
import uuid from 'react-uuid';
import '../Styles/Login.css';
import AuthContext from '../Contexts/AuthContext';

function Login({ messages, setMessages }) {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  const navigate = useNavigate();
  const auth = useContext(AuthContext);

  const onSubmit = (userData) => {
    fetch('http://localhost:8080/user/authenticate', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(userData),
    })
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
        auth.login(data.jwt_token);
        navigate('/');
        setMessages([
          ...messages,
          {
            id: uuid(),
            type: 'success',
            text: 'Welcome to Your Dashboard!',
          },
        ]);
      })
      .catch((error) => {
        if (error.status === 403) {
          setMessages([
            ...messages,
            {
              id: uuid(),
              type: 'failure',
              text: 'Account could not be logged in at this time.',
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
          navigate('/notFound');
        }
      });
  };

  return (
    <div className="row login-container">
      <div className="col-lg-4 col-md-6">
        <h3>Login</h3>
        <form id="login-form" onSubmit={handleSubmit(onSubmit)}>
          <label className="form-label mt-3" htmlFor="user-username">
            Username
          </label>
          <input
            className="form-control"
            type="username"
            id="user-username"
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
            Login
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
            Not a member?{' '}
            <a href="#!" onClick={() => navigate('/register')}>
              {' '}
              Sign Up!{' '}
            </a>
          </p>
        </form>
      </div>
    </div>
  );
}

export default Login;
