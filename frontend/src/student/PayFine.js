import React, { useState } from 'react';
import { payFineRequest } from '../utils/axiosInstance';  // Import the axios request function

const PayFine = () => {
  // States to hold studentId, amountPaid, and result message
  const [studentId, setStudentId] = useState('');
  const [amountPaid, setAmountPaid] = useState('');
  const [message, setMessage] = useState('');

  // Function to handle fine payment request
  const handlePayFine = async () => {
    if (studentId === '' || amountPaid === '') {
      setMessage('Please provide both Student ID and the amount paid.');
      return;
    }

    try {
      // Call the function to pay the fine
      const response = await payFineRequest(studentId, amountPaid);
      setMessage(response);  // Display the response message

      // Clear the inputs after successful payment
      setStudentId('');
      setAmountPaid('');
    } catch (error) {
      // Display error message if something goes wrong
      setMessage(error);
    }
  };

  return (
    <div style={{ padding: '20px', maxWidth: '600px', margin: '0 auto', backgroundColor: '#f9fafb', borderRadius: '8px', border: '1px solid #d1d5db' }}>
      <h2 style={{ fontSize: '1.5rem', marginBottom: '16px', color: '#1f2937' }}>Pay Fine</h2>

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
          Amount Paid:
          <input
            type="number"
            value={amountPaid}
            onChange={(e) => setAmountPaid(e.target.value)}
            style={{ width: '100%', padding: '8px', borderRadius: '4px', border: '1px solid #d1d5db' }}
          />
        </label>

        <button
          onClick={handlePayFine}
          style={{ padding: '10px 20px', backgroundColor: '#3b82f6', color: '#fff', borderRadius: '4px', border: 'none', cursor: 'pointer' }}
        >
          Pay Fine
        </button>
      </div>

      {/* Display success or error message */}
      {message && <p style={{ color: message.includes('Fine paid in full') ? '#10b981' : message.includes('Partial fine payment') ? '#f59e0b' : '#ef4444' }}>{message}</p>}
    </div>
  );
};

export default PayFine;
