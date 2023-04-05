import React from 'react';
import './SideNavbar.css';
import { Menu, MenuItem, useProSidebar } from 'react-pro-sidebar';

import { AlignCenter } from 'react-feather';

function SideNavbar() {
  const { collapseSidebar } = useProSidebar();

  return (
    <div className="side-navbar ">
      <Menu>
        <MenuItem icon={<AlignCenter />} onClick={() => collapseSidebar()}>
          WIDGET
        </MenuItem>
      </Menu>
    </div>
  );
}

export default SideNavbar;
