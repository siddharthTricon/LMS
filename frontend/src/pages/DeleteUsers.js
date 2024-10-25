import React, { useState } from 'react';
import axios from 'axios';

const DeleteUser = () => {
  const [id, setId] = useState('');  // State for ID
  const [role, setRole] = useState('admin');  // State for role (default is admin)
  const [message, setMessage] = useState('');  // State for feedback message

  // Function to handle the deletion
  const handleDelete = async () => {
    if (!id) {
      setMessage('Please enter a valid ID');
      return;
    }

    try {
      let url = '';
      
      // Set URL based on role
      switch (role) {
        case 'admin':
          url = `http://localhost:8080/admin/${id}`;
          break;
        case 'librarian':
          url = `http://localhost:8080/librarian/${id}`;
          break;
        case 'student':
          url = `http://localhost:8080/student/${id}`;
          break;
        default:
          setMessage('Invalid role');
          return;
      }

      // Send DELETE request
      const response = await axios.delete(url);
      setMessage(response.data || 'Deletion successful');
      setId('');  // Clear the input after deletion
    } catch (error) {
      setMessage(error.response ? error.response.data : 'Failed to delete the user');
    }
  };

  return (
    <div style={{ padding: '20px', maxWidth: '400px', margin: '0 auto', backgroundColor: '#f9fafb', borderRadius: '8px', border: '1px solid #d1d5db' }}>
      <h2 style={{ fontSize: '1.5rem', marginBottom: '16px', color: '#1f2937' }}>Delete User</h2>
      
      {message && <p style={{ color: message.includes('successful') ? '#10b981' : '#ef4444' }}>{message}</p>}

      <div style={{ marginBottom: '12px' }}>
        <label style={{ display: 'block', marginBottom: '8px', color: '#374151' }}>ID:</label>
        <input
          type="text"
          value={id}
          onChange={(e) => setId(e.target.value)}
          placeholder="Enter user ID"
          style={{ padding: '8px 12px', width: '100%', border: '1px solid #d1d5db', borderRadius: '4px', fontSize: '1rem' }}
          required
        />
      </div>

      <div style={{ marginBottom: '12px' }}>
        <label style={{ display: 'block', marginBottom: '8px', color: '#374151' }}>Role:</label>
        <select
          value={role}
          onChange={(e) => setRole(e.target.value)}
          style={{ padding: '8px 12px', width: '100%', border: '1px solid #d1d5db', borderRadius: '4px', fontSize: '1rem' }}
        >
          <option value="admin">Admin</option>
          <option value="librarian">Librarian</option>
          <option value="student">Student</option>
        </select>
      </div>

      <button
        onClick={handleDelete}
        style={{
          padding: '10px 16px',
          backgroundColor: '#ef4444',
          color: 'white',
          border: 'none',
          borderRadius: '4px',
          fontSize: '1rem',
          cursor: 'pointer',
          width: '100%',
        }}
      >
        Delete User
      </button>
    </div>
  );
};

export default DeleteUser;
