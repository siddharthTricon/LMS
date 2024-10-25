import React, { useState } from 'react';
import { addFineRequestLib } from '../utils/axiosInstance'; // Import the Axios function

const AddFine = () => {
  // State to store input values
  const [studentId, setStudentId] = useState('');
  const [fineAmount, setFineAmount] = useState('');
  const [message, setMessage] = useState('');

  // Handle form submission
  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      // Send request to add a fine to the student
      const response = await addFineRequestLib(studentId, fineAmount);
      setMessage(response); // Display success message from the response
      // Clear input fields after successful fine addition
      setStudentId('');
      setFineAmount('');
    } catch (error) {
      setMessage(error); // Display error message
    }
  };

  return (
    <div style={{ padding: '20px', maxWidth: '400px', margin: '0 auto', backgroundColor: '#f9fafb', borderRadius: '8px', border: '1px solid #d1d5db' }}>
      <h2 style={{ fontSize: '1.5rem', marginBottom: '16px', color: '#1f2937' }}>Add a Fine</h2>
      {message && <p style={{ color: message.includes('successfully') ? '#10b981' : '#ef4444' }}>{message}</p>}
      <form onSubmit={handleSubmit}>
        <div style={{ marginBottom: '12px' }}>
          <label style={{ display: 'block', marginBottom: '8px', color: '#374151' }}>Student ID:</label>
          <input
            type="text"
            value={studentId}
            onChange={(e) => setStudentId(e.target.value)}
            placeholder="Enter student ID"
            style={{ padding: '8px 12px', width: '100%', border: '1px solid #d1d5db', borderRadius: '4px', fontSize: '1rem' }}
            required
          />
        </div>

        <div style={{ marginBottom: '12px' }}>
          <label style={{ display: 'block', marginBottom: '8px', color: '#374151' }}>Fine Amount:</label>
          <input
            type="text"
            value={fineAmount}
            onChange={(e) => setFineAmount(e.target.value)}
            placeholder="Enter fine amount"
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
          Add Fine
        </button>
      </form>
    </div>
  );
};

export default AddFine;
