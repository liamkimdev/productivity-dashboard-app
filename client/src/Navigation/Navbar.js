import Navbar from 'react-bootstrap/Navbar';
import { useNavigate, NavLink } from 'react-router-dom';
import '../Navigation/Navbar.css';

function MyNavbar() {
  const navigate = useNavigate();

  return (
    <>
      <Navbar bg="light" expand="lg">
        <Navbar.Brand href="/home">Productivity Dashboard</Navbar.Brand>

        {/* You only see Home Page if you are NOT signed in */}
        <NavLink to="/" className="nav-links">
          Home
        </NavLink>

        <NavLink to="/login" className="nav-links">
          Login
        </NavLink>
        <NavLink to="/logout" className="nav-links">
          Logout
        </NavLink>
      </Navbar>
    </>
  );
}

export default MyNavbar;
