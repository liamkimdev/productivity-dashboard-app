import { useNavigate } from 'react-router-dom';
import { useForm } from 'react-hook-form';
import uuid from 'react-uuid';
import '../Styles/Register.css';

// Redux
import { useSelector, useDispatch } from 'react-redux';
import { login } from '../store/slices/AuthSlice.js';
import { createDashboard } from '../store/slices/DashboardSlice';
import { setMessages } from '../store/slices/MessagesSlice';

function Register() {
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  // Redux's state.
  const authToken = useSelector((state) => state.auth.authToken);

  const onSubmit = async (userData) => {
    try {
      // Create account
      const createAccountResponse = await fetch(
        'http://localhost:8080/user/create_account',
        {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(userData),
        }
      );

      if (createAccountResponse.status === 201) {
        const messageData = {
          messageId: uuid(),
          messageType: 'success',
          message: 'Account successfully created. Please sign in.',
        };

        dispatch(setMessages(messageData));

        // setMessages([
        //   ...messages,
        //   {
        //     id: uuid(),
        //     type: 'success',
        //     text: 'Account successfully created. Please sign in.',
        //   },
        // ]);

        // Authenticate newly created account
        const authenticateResponse = await fetch(
          'http://localhost:8080/user/authenticate',
          {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json',
            },
            body: JSON.stringify(userData),
          }
        );

        if (authenticateResponse.status === 200) {
          const data = await authenticateResponse.json();

          // Setting the global state
          dispatch(login(data));

          // Plug in Dashboard Name & User ID
          const dashboardData = {
            dashboardName: data.username + "'s Dashboard",
            userId: data.userId,
          };

          // Setting the global state
          dispatch(createDashboard(dashboardData));

          // Create dashboard for the newly created user
          const createDashboardResponse = await fetch(
            'http://localhost:8080/dashboard',
            {
              method: 'POST',
              headers: {
                'Content-Type': 'application/json',
                Authorization: 'Bearer ' + authToken,
              },
              body: JSON.stringify(dashboardData),
            }
          );

          if (createDashboardResponse.status === 201) {
            console.log('Dashboard Created Successfully');
            navigate('/login');
          } else {
            console.log('Dashboard Creation Failed');
            return Promise.reject(createDashboardResponse);
          }
        } else {
          return Promise.reject(authenticateResponse);
        }
      } else {
        // setMessages([
        //   ...messages,
        //   {
        //     id: uuid(),
        //     type: 'failure',
        //     text: 'Account could not be created at this time.',
        //   },
        // ]);

         const messageData = {
          messageId: uuid(),
          messageType: 'failure',
          message: 'Account could not be created at this time.',
        };

        dispatch(setMessages(messageData));
      }
    } catch (error) {
      
      // setMessages([
      //   ...messages,
      //   { id: uuid(), type: 'failure', text: error.message },
      // ]);

          const messageData = {
          messageId: uuid(),
          messageType: 'failure',
          message: error.message,
        };

        dispatch(setMessages(messageData));
    }
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
