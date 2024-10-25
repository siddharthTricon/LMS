import React, { useState } from 'react';
import { addLibrarianRequest } from '../utils/axiosInstance';

const AddLibrarian = () => {
  // State to store form inputs
  const [name, setName] = useState('');
  const [password, setPassword] = useState('');
  const [message, setMessage] = useState('');

  // Handle form submission
  const handleSubmit = async (e) => {
    e.preventDefault();

    const newLibrarian = {
      name,
      password,
    };

    try {
      // Send request to the backend to add the librarian
      const response = await addLibrarianRequest(newLibrarian);
      setMessage(response); // Set message from the response
      // Clear form after submission
      setName('');
      setPassword('');
    } catch (error) {
      setMessage('Failed to add librarian. Try again!');
    }
  };

  return (
    <div style={{ padding: '20px', maxWidth: '400px', margin: '0 auto', backgroundColor: '#f9fafb', borderRadius: '8px', border: '1px solid #d1d5db' }}>
      <h2 style={{ fontSize: '1.5rem', marginBottom: '16px', color: '#1f2937' }}>Add a New Librarian</h2>
      {message && <p style={{ color: '#ef4444' }}>{message}</p>}
      <form onSubmit={handleSubmit}>
        <div style={{ marginBottom: '12px' }}>
          <label style={{ display: 'block', marginBottom: '8px', color: '#374151' }}>Librarian Name:</label>
          <input
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
            placeholder="Enter librarian name"
            style={{ padding: '8px 12px', width: '100%', border: '1px solid #d1d5db', borderRadius: '4px', fontSize: '1rem' }}
            required
          />
        </div>

        <div style={{ marginBottom: '12px' }}>
          <label style={{ display: 'block', marginBottom: '8px', color: '#374151' }}>Password:</label>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            placeholder="Enter password"
            style={{ padding: '8px 12px', width: '100%', border: '1px solid #d1d5db', borderRadius: '4px', fontSize: '1rem' }}
            required
          />
        </div>

        <button
          type="submit"
          style={{
            padding: '10px 16px',
            backgroundColor: '#3b82f6',
            color: 'white',
            border: 'none',
            borderRadius: '4px',
            fontSize: '1rem',
            cursor: 'pointer',
            width: '100%',
          }}
        >
          Add Librarian
        </button>
      </form>
    </div>
  );
};

export default AddLibrarian;
