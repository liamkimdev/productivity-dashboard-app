import { useNavigate } from 'react-router-dom';
import { useForm } from 'react-hook-form';

function Register({ messages, setMessages, makeId, isPasswordComplex }) {

    const { 
        register, 
        handleSubmit,
        formState: { errors } 
    } = useForm();

    const navigate = useNavigate();

    const onSubmit = (userData) => {
        fetch("http://localhost:8080/create_account", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(userData)
        })
        .then(response => {
            if (response.status === 201) {
                navigate("/login");
            } else if (response.status === 400) {
                setMessages([...messages, { id: makeId(), type: "failure", text: "Account could not be created at this time." }]);
                navigate("/");
            } else {
                setMessages([...messages, { id: makeId(), type: "failure", text: "Unexpected error occurred." }]);
            };
        })
        .catch(error => setMessages([...messages, { id: makeId(), type: "failure", text: error.message }]));
    };

    return (
        <div className="row">
            <div className="col-lg-4 col-md-6">
                <h3>Register</h3>
                <form id="login-form" onSubmit={handleSubmit(onSubmit)}>
                    <label className="form-label mt-3" htmlFor="user-email">Email</label>
                    <input
                        className="form-control"
                        type="email"
                        id="user-email" 
                        {...register("username", { 
                            required: "Must have a valid email address.",
                            maxLength: {value: 50, message: "Must be 50 characters or less."},
                            pattern: {
                                value: /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i, // RegEx for valid characters for an email
                                message: "Invalid characters, please input a valid email address."
                            }
                        })}
                    />
                    <p className="form-error-message">{errors.username?.message}</p>

                    <label className="form-label mt-3" htmlFor="user-password">Password</label>
                    <input 
                        className="form-control" 
                        type="password" 
                        id="user-password" 
                        {...register("password", { 
                            required: "Must have a valid password.",
                            minLength: {value: 8, message: "Must have at least 8 characters."},
                            validate: v => isPasswordComplex(v)
                            || "Must contain at least one of the following: an uppercase letter, a lowercase letter, a number, and a special character."
                        })}
                    />
                    <p className="form-error-message">{errors.password?.message}</p>

                    <button className="btn btn-primary mt-3" type="submit">Create Account</button>
                    <button className="btn btn-secondary mt-3 ms-2" type="button" onClick={() => navigate("/")}>Cancel</button>
                </form>
            </div>
        </div>
    );
};

export default Register;