import React, { useState } from 'react';
import { returnBookRequestStud } from '../utils/axiosInstance';

const ReturnBook = () => {
  // States to hold studentId, bookId, and result message
  const [studentId, setStudentId] = useState('');
  const [bookId, setBookId] = useState('');
  const [message, setMessage] = useState('');

  // Function to handle book return request
  const handleReturnBook = async () => {
    if (studentId === '' || bookId === '') {
      setMessage('Please provide both Student ID and Book ID.');
      return;
    }

    try {
      // Call the function to return the book
      const response = await returnBookRequestStud(studentId, bookId);

      setMessage(response);  // Display the response message

      // Clear the inputs after successful return
      setStudentId('');
      setBookId('');
    } catch (error) {
      // Display error message if something goes wrong
      setMessage(error);
    }
  };

  return (
    <div style={{ padding: '20px', maxWidth: '600px', margin: '0 auto', backgroundColor: '#f9fafb', borderRadius: '8px', border: '1px solid #d1d5db' }}>
      <h2 style={{ fontSize: '1.5rem', marginBottom: '16px', color: '#1f2937' }}>Return Book</h2>

      <div style={{ marginBottom: '16px' }}>
        <label style={{ display: 'block', marginBottom: '8px' }}>
          Student ID:
          <input
            type="number"
            value={studentId}
            onChange={(e) => setStudentId(e.target.value)}
            style={{ width: '100%', padding: '8px', borderRadius: '4px', border: '1px solid #d1d5db' }}
          />
        </label>

        <label style={{ display: 'block', marginBottom: '8px' }}>
          Book ID:
          <input
            type="number"
            value={bookId}
            onChange={(e) => setBookId(e.target.value)}
            style={{ width: '100%', padding: '8px', borderRadius: '4px', border: '1px solid #d1d5db' }}
          />
        </label>

        <button
          onClick={handleReturnBook}
          style={{ padding: '10px 20px', backgroundColor: '#3b82f6', color: '#fff', borderRadius: '4px', border: 'none', cursor: 'pointer' }}
        >
          Return Book
        </button>
      </div>

      {/* Display success or error message */}
      {message && <p style={{ color: message.includes('successfully') ? '#10b981' : '#ef4444' }}>{message}</p>}
    </div>
  );
};

export default ReturnBook;





