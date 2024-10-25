import React from 'react';
import SearchBooks from '../student/SeacrhBook';
import TakeBook from '../student/TakeBook';
import ReturnBook from '../student/ReturnBook';
import PayFine from '../student/PayFine';
import { useNavigate } from 'react-router-dom';


const Student = () => {

  const navigate = useNavigate();

  const handleLogout = () => {
    navigate('/logout');  // Redirect to the Logout component
  }

  return (
    <div className="p-4">
      <h2 className="text-2xl">Student Dashboard</h2>
      <SearchBooks />
      <TakeBook />
      <ReturnBook />
      <PayFine />

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

export default Student;
