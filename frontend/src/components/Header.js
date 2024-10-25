import React from 'react';
import { Link } from 'react-router-dom';

const Header = () => {
  return (
    <header className="bg-blue-600 p-4">
      <h1 className="text-white text-2xl">Library System</h1>
      <nav>
        <ul className="flex space-x-4">
          <li><Link className="text-white" to="/">Home</Link></li>
          <li><Link className="text-white" to="/login">Login</Link></li>
          <li><Link className="text-white" to="/delete">Delete</Link></li>
          {/* <li><Link className="text-white" to="/logout">Logout</Link></li> */}
          {/* <li><Link className="text-white" to="/admin">Admin</Link></li>
          <li><Link className="text-white" to="/librarian">Librarian</Link></li>
          <li><Link className="text-white" to="/student">Student</Link></li> */}
        </ul>
      </nav>
    </header>
  );
};

export default Header;
