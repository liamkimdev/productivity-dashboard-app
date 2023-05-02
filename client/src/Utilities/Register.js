import { useNavigate } from 'react-router-dom';
import { useForm } from 'react-hook-form';
import uuid from 'react-uuid';
import '../Styles/Register.css';

// Redux
import { useSelector, useDispatch } from 'react-redux';
import { login } from '../store/slices/AuthSlice.js';
import { createDashboard } from '../store/slices/DashboardSlice';
import { setMessages } from '../store/slices/MessagesSlice';

// function Register() {
//   const navigate = useNavigate();
//   const dispatch = useDispatch();

//   const {
//     register,
//     handleSubmit,
//     formState: { errors },
//   } = useForm();

//   // Redux's state.
//   const authToken = useSelector((state) => state.auth.authToken);

//   const onSubmit = async (userData) => {
//     try {
//       // Create account
//       const createAccountResponse = await fetch(
//         'http://localhost:8080/user/create_account',
//         {
//           method: 'POST',
//           headers: {
//             'Content-Type': 'application/json',
//           },
//           body: JSON.stringify(userData),
//         }
//       )

//       .then( (response) => {
//         if (response.status === 201) {
//         const messageData = {
//           messageId: uuid(),
//           messageType: 'success',
//           message: 'Account successfully created. Please sign in.',
//         };
//         dispatch(setMessages(messageData));

//         } else {
//           /*
//             HTTP requests to force reject:
//             400 Bad Request
//             401 Unauthorized
//             403 Forbidden
//             404 Not Found
//             500 Internal Server Error
//           */
//           return Promise.reject(response);
//         }
//         })

//         // Authenticate newly created account
//         .then((data) => {
//           fetch(
//           'http://localhost:8080/user/authenticate',
//           {
//             method: 'POST',
//             headers: {
//               'Content-Type': 'application/json',
//             },
//             body: JSON.stringify(userData),
//           }

//         )
//         .then((response) => {
//         if (response === 200) {
//           const data = response.json();
//           console.log(data);

//           // Setting the global state
//           dispatch(login(data));

//           // Plug in Dashboard Name & User ID
//           const dashboardData = {
//             dashboardName: data.username + "'s Dashboard",
//             userId: data.userId,
//           };
//           console.log(dashboardData);

//           // Create dashboard for the newly created user
//           const authToken = data.jwt_token;

//           console.log(authToken);
//         }
//       })

//           .then(() => {
//             fetch(
//             'http://localhost:8080/dashboard',
//             {
//               method: 'POST',
//               headers: {
//                 'Content-Type': 'application/json',
//                 Authorization: 'Bearer ' + authToken,
//               },
//               body: JSON.stringify(dashboardData),
//             }
//           )
//           .then((response) => {
//           if (response === 201) {
//             console.log('Dashboard Created Successfully');
//             navigate('/login');
//           } else {
//             console.log('Dashboard Creation Failed');
//             return Promise.reject(createDashboardResponse);
//           }
//         })

//         } else {
//           return Promise.reject(response);
//         }
//       } else {
//         const messageData = {
//           messageId: uuid(),
//           messageType: 'failure',
//           message: 'Account could not be created at this time.',
//         };

//         dispatch(setMessages(messageData));
//       }
//     } catch (error) {
//       const messageData = {
//         messageId: uuid(),
//         messageType: 'failure',
//         message: error.message,
//       };

//       dispatch(setMessages(messageData));
//     }
//   };
function Register() {
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  const onSubmit = async (userData) => {
    try {
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

      if (createAccountResponse.ok) {
        const messageData = {
          messageId: uuid(),
          messageType: 'success',
          message: 'Account successfully created. Please sign in.',
        };
        dispatch(setMessages(messageData));

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

        if (authenticateResponse.ok) {
          const data = await authenticateResponse.json();

          dispatch(login(data));

          const dashboardData = {
            dashboardName: `${data.username}'s Dashboard`,
            userId: data.userId,
          };

          const authToken = data.jwt_token;

          const createDashboardResponse = await fetch(
            'http://localhost:8080/dashboard',
            {
              method: 'POST',
              headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${authToken}`,
              },
              body: JSON.stringify(dashboardData),
            }
          );

          if (createDashboardResponse.ok) {
            console.log('Dashboard Created Successfully');
            navigate('/login');
          } else {
            console.log('Dashboard Creation Failed');
            throw new Error('Dashboard Creation Failed');
          }
        } else {
          const messageData = {
            messageId: uuid(),
            messageType: 'failure',
            message: 'Authentication failed.',
          };
          dispatch(setMessages(messageData));
        }
      } else {
        const messageData = {
          messageId: uuid(),
          messageType: 'failure',
          message: 'Account could not be created at this time.',
        };
        dispatch(setMessages(messageData));
      }
    } catch (error) {
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
