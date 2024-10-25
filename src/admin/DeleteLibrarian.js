import React, { useState } from 'react';
import { deleteLibrarianRequest } from '../utils/axiosInstance';

const DeleteLibrarian = () => {
  // State to store the librarian ID
  const [librarianId, setLibrarianId] = useState('');
  const [message, setMessage] = useState('');

  // Handle form submission
  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      // Send request to the backend to delete the librarian
      const response = await deleteLibrarianRequest(librarianId);
      setMessage(response); // Set message from the response
      // Clear input after submission
      setLibrarianId('');
    } catch (error) {
      setMessage('Failed to delete librarian. Try again!');
    }
  };

  return (
    <div style={{ padding: '20px', maxWidth: '400px', margin: '0 auto', backgroundColor: '#f9fafb', borderRadius: '8px', border: '1px solid #d1d5db' }}>
      <h2 style={{ fontSize: '1.5rem', marginBottom: '16px', color: '#1f2937' }}>Delete a Librarian</h2>
      {message && <p style={{ color: '#ef4444' }}>{message}</p>}
      <form onSubmit={handleSubmit}>
        <div style={{ marginBottom: '12px' }}>
          <label style={{ display: 'block', marginBottom: '8px', color: '#374151' }}>Librarian ID:</label>
          <input
            type="text"
            value={librarianId}
            onChange={(e) => setLibrarianId(e.target.value)}
            placeholder="Enter librarian ID"
            style={{ padding: '8px 12px', width: '100%', border: '1px solid #d1d5db', borderRadius: '4px', fontSize: '1rem' }}
            required
          />
        </div>

        <button
          type="submit"
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
          Delete Librarian
        </button>
      </form>
    </div>
  );
};

export default DeleteLibrarian;
