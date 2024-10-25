// import React, { useState } from 'react';

// import { toast } from 'react-toastify';
// import axiosInstance from '../utils/axiosInstance;

// const ReturnBook = () => {
//   const [bookId, setBookId] = useState('');
//   const [studentId, setStudentId] = useState('');

//   const handleReturn = async (e) => {
//     e.preventDefault();
//     try {
//       await axiosInstance.post('/returnBook', { bookId, studentId });
//       toast.success('Book returned successfully!');
//       setBookId('');
//       setStudentId('');
//     } catch (error) {
//       toast.error('Error returning book.');
//       console.error('Return book error:', error);
//     }
//   };

//   return (
//     <form onSubmit={handleReturn} className="p-4 border rounded">
//       <h3 className="text-xl">Return a Book</h3>
//       <div className="mb-2">
//         <label className="block">Book ID:</label>
//         <input
//           type="text"
//           value={bookId}
//           onChange={(e) => setBookId(e.target.value)}
//           className="border p-2 w-full"
//           required
//         />
//       </div>
//       <div className="mb-2">
//         <label className="block">Student ID:</label>
//         <input
//           type="text"
//           value={studentId}
//           onChange={(e) => setStudentId(e.target.value)}
//           className="border p-2 w-full"
//           required
//         />
//       </div>
//       <button type="submit" className="bg-green-600 text-white p-2 rounded">
//         Return Book
//       </button>
//     </form>
//   );
// };

// export default ReturnBook;







import React, { useState } from 'react';
import { returnBookRequest } from '../utils/axiosInstance'; // Import the Axios function



const ReturnBook = () => {
  // State to store input values
  const [studentId, setStudentId] = useState('');
  const [bookId, setBookId] = useState('');
  const [message, setMessage] = useState('');

  // Handle form submission
  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      // Send request to return the book
      const response = await returnBookRequest(studentId, bookId);
      setMessage(response); // Display success message from the response
      // Clear input fields after successful return
      setStudentId('');
      setBookId('');
    } catch (error) {
      setMessage(error); // Display error message
    }
  };

  return (
    <div style={{ padding: '20px', maxWidth: '400px', margin: '0 auto', backgroundColor: '#f9fafb', borderRadius: '8px', border: '1px solid #d1d5db' }}>
      <h2 style={{ fontSize: '1.5rem', marginBottom: '16px', color: '#1f2937' }}>Return a Book</h2>
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
          <label style={{ display: 'block', marginBottom: '8px', color: '#374151' }}>Book ID:</label>
          <input
            type="text"
            value={bookId}
            onChange={(e) => setBookId(e.target.value)}
            placeholder="Enter book ID"
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
          Return Book
        </button>
      </form>
    </div>
  );
};

export default ReturnBook;
