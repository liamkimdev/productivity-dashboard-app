import Navbar from 'react-bootstrap/Navbar';
import { NavLink } from 'react-router-dom';
import { useSelector, useDispatch } from 'react-redux';

import '../Navigation/Navbar.css';

import { logout } from '../store/slices/AuthSlice.js';

function MyNavbar() {
  const dispatch = useDispatch();

  //Redux's state.
  const authToken = useSelector((state) => state.auth.authToken);

  const handleLogout = () => {
    dispatch(logout());
  };

  return (
    <>
      <Navbar bg="light" expand="lg">
        <Navbar.Brand href="/home">Productivity Dashboard</Navbar.Brand>

        {/* You only see Home Page if you are NOT signed in */}{}
        <NavLink to="/" className="nav-links">
          Home
        </NavLink>

        {!authToken ? (
          <NavLink to="/login" className="nav-links">
            Login
          </NavLink>
        ) : null}

        {authToken ? (
          <NavLink to="/" className="nav-links" onClick={handleLogout}>
            Logout
          </NavLink>
        ) : null}
      </Navbar>
    </>
  );
}

export default MyNavbar;
