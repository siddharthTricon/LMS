// import React, { useState } from 'react';

// import { toast } from 'react-toastify';
// import axiosInstance from '../utils/axiosInstance';

// const AddBook = () => {
//   const [title, setTitle] = useState('');
//   const [author, setAuthor] = useState('');

//   const handleSubmit = async (e) => {
//     e.preventDefault();
//     try {
//       await axiosInstance.post('/admin/addBook', { title, author });
//       toast.success('Book added successfully!');
//       setTitle('');
//       setAuthor('');
//     } catch (error) {
//       toast.error('Error adding book.');
//       console.error('Add book error:', error);
//     }
//   };

//   return (
//     <form onSubmit={handleSubmit} className="p-4 border rounded">
//       <h3 className="text-xl">Add a New Book</h3>
//       <div className="mb-2">
//         <label className="block">Title:</label>
//         <input
//           type="text"
//           value={title}
//           onChange={(e) => setTitle(e.target.value)}
//           className="border p-2 w-full"
//           required
//         />
//       </div>
//       <div className="mb-2">
//         <label className="block">Author:</label>
//         <input
//           type="text"
//           value={author}
//           onChange={(e) => setAuthor(e.target.value)}
//           className="border p-2 w-full"
//           required
//         />
//       </div>
//       <button type="submit" className="bg-blue-600 text-white p-2 rounded">
//         Add Book
//       </button>
//     </form>
//   );
// };

// export default AddBook;






// import React, { useState } from 'react';
// import { addBookRequest } from '../utils/axiosInstance';

// const AddBook = () => {
//   // State to store form inputs
//   const [title, setTitle] = useState('');
//   const [author, setAuthor] = useState('');
//   const [message, setMessage] = useState('');

//   // Handle form submission
//   const handleSubmit = async (e) => {
//     e.preventDefault();

//     const newBook = {
//       title,
//       author,
//     };

//     try {
//       // Send request to the backend to add the book
//       const response = await addBookRequest(newBook);
//       setMessage(response);
//       // Clear form after submission
//       setTitle('');
//       setAuthor('');
//     } catch (error) {
//       setMessage('Failed to add book. Try again!');
//     }
//   };

//   return (
//     <div>
//       <h2>Add a New Book</h2>
//       {message && <p>{message}</p>}
//       <form onSubmit={handleSubmit}>
//         <div>
//           <label>Book Title:</label>
//           <input
//             type="text"
//             value={title}
//             onChange={(e) => setTitle(e.target.value)}
//             placeholder="Enter book title"
//             required
//           />
//         </div>

//         <div>
//           <label>Author:</label>
//           <input
//             type="text"
//             value={author}
//             onChange={(e) => setAuthor(e.target.value)}
//             placeholder="Enter author name"
//             required
//           />
//         </div>

//         <button type="submit">Add Book</button>
//       </form>
//     </div>
//   );
// };

// export default AddBook;





import React, { useState } from 'react';
import { addBookRequest } from '../utils/axiosInstance';

const AddBook = () => {
  // State to store form inputs
  const [title, setTitle] = useState('');
  const [author, setAuthor] = useState('');
  const [message, setMessage] = useState('');

  // Handle form submission
  const handleSubmit = async (e) => {
    e.preventDefault();

    const newBook = {
      title,
      author,
    };

    try {
      // Send request to the backend to add the book
      const response = await addBookRequest(newBook);
      setMessage(response);
      // Clear form after submission
      setTitle('');
      setAuthor('');
    } catch (error) {
      setMessage('Failed to add book. Try again!');
    }
  };

  return (
    <div style={{ padding: '20px', maxWidth: '400px', margin: '0 auto', backgroundColor: '#f9fafb', borderRadius: '8px', border: '1px solid #d1d5db' }}>
      <h2 style={{ fontSize: '1.5rem', marginBottom: '16px', color: '#1f2937' }}>Add a New Book</h2>
      {message && <p style={{ color: '#ef4444' }}>{message}</p>}
      <form onSubmit={handleSubmit}>
        <div style={{ marginBottom: '12px' }}>
          <label style={{ display: 'block', marginBottom: '8px', color: '#374151' }}>Book Title:</label>
          <input
            type="text"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            placeholder="Enter book title"
            style={{ padding: '8px 12px', width: '100%', border: '1px solid #d1d5db', borderRadius: '4px', fontSize: '1rem' }}
            required
          />
        </div>

        <div style={{ marginBottom: '12px' }}>
          <label style={{ display: 'block', marginBottom: '8px', color: '#374151' }}>Author:</label>
          <input
            type="text"
            value={author}
            onChange={(e) => setAuthor(e.target.value)}
            placeholder="Enter author name"
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
          Add Book
        </button>
      </form>
    </div>
  );
};

export default AddBook;




















// import React, { useState } from 'react';
// import { toast } from 'react-toastify';
// import { addBookRequest } from '../utils/axiosInstance';
// import 'react-toastify/dist/ReactToastify.css';

// // Initialize Toastify
// toast.configure();

// const AddBook = () => {
//   // State to store form inputs
//   const [title, setTitle] = useState('');
//   const [author, setAuthor] = useState('');

//   // Handle form submission
//   const handleSubmit = async (e) => {
//     e.preventDefault();

//     const newBook = { title, author };

//     try {
//       // Send request to the backend to add the book
//       const response = await addBookRequest(newBook);
//       toast.success('Book added successfully!', {
//         position: toast.POSITION.TOP_RIGHT,
//       });
//       // Clear form after submission
//       setTitle('');
//       setAuthor('');
//     } catch (error) {
//       toast.error('Failed to add book. Try again!', {
//         position: toast.POSITION.TOP_RIGHT,
//       });
//     }
//   };

//   return (
//     <div className="p-6 max-w-lg mx-auto bg-gray-50 rounded-lg border border-gray-200 shadow-md mt-10">
//       <h2 className="text-2xl font-bold text-gray-800 mb-6">Add a New Book</h2>
      
//       <form onSubmit={handleSubmit}>
//         {/* Title Input */}
//         <div className="mb-4">
//           <label className="block text-gray-700 mb-2">Book Title:</label>
//           <input
//             type="text"
//             value={title}
//             onChange={(e) => setTitle(e.target.value)}
//             placeholder="Enter book title"
//             className="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
//             required
//           />
//         </div>

//         {/* Author Input */}
//         <div className="mb-4">
//           <label className="block text-gray-700 mb-2">Author:</label>
//           <input
//             type="text"
//             value={author}
//             onChange={(e) => setAuthor(e.target.value)}
//             placeholder="Enter author name"
//             className="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
//             required
//           />
//         </div>

//         {/* Submit Button */}
//         <button
//           type="submit"
//           className="w-full bg-blue-600 text-white py-2 px-4 rounded-md hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500"
//         >
//           Add Book
//         </button>
//       </form>
//     </div>
//   );
// };

// export default AddBook;
