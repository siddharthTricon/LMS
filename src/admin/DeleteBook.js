// import React, { useState } from 'react';

// import { toast } from 'react-toastify';
// import axiosInstance from '../utils/axiosInstance';

// const DeleteBook = () => {
//   const [bookId, setBookId] = useState('');

//   const handleDelete = async (e) => {
//     e.preventDefault();
//     try {
//       await axiosInstance.delete(`/admin/deleteBook/${bookId}`);
//       toast.success('Book deleted successfully!');
//       setBookId('');
//     } catch (error) {
//       toast.error('Error deleting book.');
//       console.error('Delete book error:', error);
//     }
//   };

//   return (
//     <form onSubmit={handleDelete} className="p-4 border rounded">
//       <h3 className="text-xl">Delete a Book</h3>
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
//       <button type="submit" className="bg-red-600 text-white p-2 rounded">
//         Delete Book
//       </button>
//     </form>
//   );
// };

// export default DeleteBook;

// import React, { useState } from 'react';
// import { toast } from 'react-toastify';
// import { deleteBookRequest } from '../utils/axiosInstance';
// import 'react-toastify/dist/ReactToastify.css';

// // Initialize Toastify
// toast.configure();

// const DeleteBook = () => {
//   // State to store the book ID or title to delete
//   const [bookId, setBookId] = useState('');

//   // Handle form submission to delete book
//   const handleDelete = async (e) => {
//     e.preventDefault();

//     try {
//       // Send request to the backend to delete the book
//       const response = await deleteBookRequest(bookId);
      
//       // Notify success with the message from the response
//       toast.success(response, {
//         position: toast.POSITION.TOP_RIGHT,
//       });

//       // Clear input after submission
//       setBookId('');
//     } catch (error) {
//       // Notify error if the delete operation fails
//       toast.error('Failed to delete book. Try again!', {
//         position: toast.POSITION.TOP_RIGHT,
//       });
//     }
//   };

//   return (
//     <div className="p-6 max-w-lg mx-auto bg-red-50 rounded-lg border border-gray-200 shadow-md mt-10">
//       <h2 className="text-2xl font-bold text-gray-800 mb-6">Delete a Book</h2>
      
//       <form onSubmit={handleDelete}>
//         {/* Book ID Input */}
//         <div className="mb-4">
//           <label className="block text-gray-700 mb-2">Book ID or Title:</label>
//           <input
//             type="text"
//             value={bookId}
//             onChange={(e) => setBookId(e.target.value)}
//             placeholder="Enter book ID or title"
//             className="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-red-500"
//             required
//           />
//         </div>

//         {/* Delete Button */}
//         <button
//           type="submit"
//           className="w-full bg-red-600 text-white py-2 px-4 rounded-md hover:bg-red-700 focus:outline-none focus:ring-2 focus:ring-red-500"
//         >
//           Delete Book
//         </button>
//       </form>
//     </div>
//   );
// };

// export default DeleteBook;




import React, { useState } from 'react';
import { deleteBookRequest } from '../utils/axiosInstance';

const DeleteBook = () => {
  // State to store the book ID or title to delete
  const [bookId, setBookId] = useState('');
  const [message, setMessage] = useState('');

  // Handle form submission to delete book
  const handleDelete = async (e) => {
    e.preventDefault();

    try {
      // Send request to the backend to delete the book
      const response = await deleteBookRequest(bookId);
      setMessage(response); // Set message from the response
      // Clear input after submission
      setBookId('');
    } catch (error) {
      setMessage('Failed to delete book. Try again!');
    }
  };

  return (
    <div style={{ padding: '20px', maxWidth: '400px', margin: '0 auto', backgroundColor: '#f9fafb', borderRadius: '8px', border: '1px solid #d1d5db' }}>
      <h2 style={{ fontSize: '1.5rem', marginBottom: '16px', color: '#1f2937' }}>Delete a Book</h2>
      {message && <p style={{ color: '#ef4444' }}>{message}</p>}
      <form onSubmit={handleDelete}>
        <div style={{ marginBottom: '12px' }}>
          <label style={{ display: 'block', marginBottom: '8px', color: '#374151' }}>Book ID or Title:</label>
          <input
            type="text"
            value={bookId}
            onChange={(e) => setBookId(e.target.value)}
            placeholder="Enter book ID or title"
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
          Delete Book
        </button>
      </form>
    </div>
  );
};

export default DeleteBook;
