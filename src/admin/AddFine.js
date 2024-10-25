import React, { useState } from 'react';
import { addFineRequest } from '../utils/axiosInstance';

const AddFine = () => {
  // State to store form inputs
  const [studentId, setStudentId] = useState('');
  const [fineAmount, setFineAmount] = useState(0);
  const [message, setMessage] = useState('');

  // Handle form submission
  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      // Send request to the backend to add fine
      const response = await addFineRequest(studentId, fineAmount);
      setMessage(response); // Set message from the response
      // Clear form after submission
      setStudentId('');
      setFineAmount(0);
    } catch (error) {
      setMessage('Failed to add fine. Try again!');
    }
  };

  return (
    <div style={{ padding: '20px', maxWidth: '400px', margin: '0 auto', backgroundColor: '#f9fafb', borderRadius: '8px', border: '1px solid #d1d5db' }}>
      <h2 style={{ fontSize: '1.5rem', marginBottom: '16px', color: '#1f2937' }}>Add Fine to Student</h2>
      {message && <p style={{ color: '#ef4444' }}>{message}</p>}
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
            type="number"
            value={fineAmount}
            onChange={(e) => setFineAmount(parseFloat(e.target.value))}
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
