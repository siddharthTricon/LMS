import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

const Logout = () => {
  const navigate = useNavigate();

  useEffect(() => {
    // Clear any authentication tokens or session data
    localStorage.removeItem('authToken');  // Assuming you store the token in localStorage
    localStorage.removeItem('role');  // If you store the role
    // You can also clear other session data here if needed
    
    // Redirect to login page
    navigate('/');
  }, [navigate]);

  return (
    <div>
      <h2>Logging you out...</h2>
    </div>
  );
};

export default Logout;
