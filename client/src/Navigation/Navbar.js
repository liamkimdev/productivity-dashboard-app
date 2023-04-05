import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import { useNavigate, NavLink } from 'react-router-dom';

function MyNavbar() {
  const navigate = useNavigate();

  return (
    <>
      <Navbar bg="light" expand="lg">
  
          <Navbar.Brand href="/home">Productivity Dashboard</Navbar.Brand>      
              <NavLink to="/home" className="nav-links"> Home </NavLink>
              <NavLink to="/login" className="nav-links"> Login </NavLink>
              <NavLink to="/logout" className="nav-links"> Logout </NavLink>
 
      </Navbar>
    </>
  );
}

export default MyNavbar;
