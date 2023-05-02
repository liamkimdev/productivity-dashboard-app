import React from 'react';
import './SideNavbar.css';
import { Menu, MenuItem, useProSidebar } from 'react-pro-sidebar';
import { AlignCenter } from 'react-feather';

// // Redux
// import { useSelector, useDispatch } from 'react-redux';
// import { login } from '../store/slices/AuthSlice.js';


function SideNavbar() {
  const { collapseSidebar } = useProSidebar();

  // const authToken = useSelector((state) => state.auth.authToken);

  return (
    <div className="side-navbar">
      <Menu>
        <MenuItem icon={<AlignCenter />} onClick={() => collapseSidebar()}>
          Note
        </MenuItem>
      </Menu>
    </div>
  );
}

export default SideNavbar;
