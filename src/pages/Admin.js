
import React from 'react';
import AddBook from '../admin/AddBook.js';
import DeleteBook from '../admin/DeleteBook.js';
import AddStudent from '../admin/AddStudent.js';
import DeleteStudent from '../admin/DeleteStudent.js';
import AddLibrarian from '../admin/AddLibrarian.js';
import DeleteLibrarian from '../admin/DeleteLibrarian.js';
import AddFine from '../admin/AddFine.js';
import { useNavigate } from 'react-router-dom';
// import ManageFines from '../components/ManageFines';
// import Notification from '../components/Notification';

const Admin = () => {

  const navigate = useNavigate();

  const handleLogout = () => {
    navigate('/logout');  // Redirect to the Logout component
  }

  return (
    <div className="p-4">
      <h2 className="text-2xl mb-4">Admin Dashboard</h2>
      <AddBook />
      {/* <Notification message="Book added successfully!" /> */}
      <DeleteBook />
      <AddStudent />
      <DeleteStudent />
      <AddLibrarian />
      <DeleteLibrarian />
      <AddFine />
      {/* <ManageFines /> */}
      <button
        onClick={handleLogout}
        style={{
          padding: '10px 16px',
          backgroundColor: '#ef4444',
          color: 'white',
          border: 'none',
          borderRadius: '4px',
          fontSize: '1rem',
          cursor: 'pointer',
          marginTop: '20px',
        }}
      >
        Logout
      </button>


    </div>
  );
};

export default Admin;
