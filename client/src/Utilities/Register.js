import { useNavigate } from 'react-router-dom';
import { useForm } from 'react-hook-form';
import uuid from 'react-uuid';

function Register({ messages, setMessages }) {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  const navigate = useNavigate();

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
          navigate('/login');
        } else if (response.status === 400) {
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
    <div className="row">
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
        </form>
      </div>
    </div>
  );
}

export default Register;
