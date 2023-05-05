import React, { useState, useEffect } from 'react';
import './SideNavbar.css';
import { Menu, MenuItem, useProSidebar } from 'react-pro-sidebar';
import { Menu as MenuIcon, Paperclip } from 'react-feather';

function SideNavbar({ handleNoteButtonClick }) {
  const { collapseSidebar } = useProSidebar();

  return (
    <div className="side-navbar">
      <Menu>
        <MenuItem icon={<MenuIcon />} onClick={() => collapseSidebar()}>
          Widgets
        </MenuItem>

        <MenuItem icon={<Paperclip />} onClick={handleNoteButtonClick}>
          Notes
        </MenuItem>
      </Menu>
    </div>
  );
}

export default SideNavbar;
