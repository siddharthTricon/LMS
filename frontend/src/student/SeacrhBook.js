// import React, { useState } from 'react';
// import { getBooksBySearchRequest } from '../utils/axiosInstance';

// const SearchBooks = () => {
//   // States to hold search parameters and result data
//   const [authorName, setAuthorName] = useState('');
//   const [bookName, setBookName] = useState('');
//   const [books, setBooks] = useState([]);
//   const [message, setMessage] = useState('');

//   // Function to handle book search
//   const handleSearch = async () => {
//     if (authorName === '' && bookName === '') {
//       setMessage('Please provide author name or book name for the search.');
//       return;
//     }
    
//     try {
//       // Send request to search books using the getBooksBySearchRequest function
//       const response = await getBooksBySearchRequest(authorName, bookName);
//       setBooks(response); // Set the search results in state
//       setMessage(''); // Clear any previous messages
//     } catch (error) {
//       // Display error message if book search fails
//       setMessage(error);
//     }
//   };

//   return (
//     <div style={{ padding: '20px', maxWidth: '600px', margin: '0 auto', backgroundColor: '#f9fafb', borderRadius: '8px', border: '1px solid #d1d5db' }}>
//       <h2 style={{ fontSize: '1.5rem', marginBottom: '16px', color: '#1f2937' }}>Search Books</h2>

//       <div style={{ marginBottom: '16px' }}>
//         <label style={{ display: 'block', marginBottom: '8px' }}>
//           Author Name:
//           <input
//             type="text"
//             value={authorName}
//             onChange={(e) => setAuthorName(e.target.value)}
//             style={{ width: '100%', padding: '8px', borderRadius: '4px', border: '1px solid #d1d5db' }}
//           />
//         </label>

//         <label style={{ display: 'block', marginBottom: '8px' }}>
//           Book Name:
//           <input
//             type="text"
//             value={bookName}
//             onChange={(e) => setBookName(e.target.value)}
//             style={{ width: '100%', padding: '8px', borderRadius: '4px', border: '1px solid #d1d5db' }}
//           />
//         </label>

//         <button
//           onClick={handleSearch}
//           style={{ padding: '10px 20px', backgroundColor: '#3b82f6', color: '#fff', borderRadius: '4px', border: 'none', cursor: 'pointer' }}
//         >
//           Search
//         </button>
//       </div>

//       {/* Display any message */}
//       {message && <p style={{ color: '#ef4444' }}>{message}</p>}

//       {/* Display the search results */}
//       {books.length > 0 ? (
//         <ul style={{ listStyleType: 'none', padding: 0 }}>
//           {books.map((book) => (
//             <li key={book.id} style={{ padding: '12px', borderBottom: '1px solid #e5e7eb' }}>
//               <p><strong>Title:</strong> {book.title}</p>
//               <p><strong>Author:</strong> {book.author}</p>
//             </li>
//           ))}
//         </ul>
//       ) : (
//         <p>No books found.</p>
//       )}
//     </div>
//   );
// };

// export default SearchBooks;






import React, { useState } from 'react';
import { getBooksBySearchRequest } from '../utils/axiosInstance';

const SearchBooks = () => {
  // States to hold search parameters and result data
  const [authorName, setAuthorName] = useState('');
  const [bookName, setBookName] = useState('');
  const [books, setBooks] = useState([]);
  const [message, setMessage] = useState('');

  // Function to handle book search
  const handleSearch = async () => {
    if (authorName === '' && bookName === '') {
      setMessage('Please provide author name or book name for the search.');
      return;
    }

    try {
      // Send request to search books using the getBooksBySearchRequest function
      const response = await getBooksBySearchRequest(authorName, bookName);
      setBooks(response); // Set the search results in state

      // Clear author and book name input fields after search
      setAuthorName('');
      setBookName('');

      // Set message if books are found
      if (response.length > 0) {
        setMessage('Books found!');
      } else {
        setMessage('No books found.');
      }
    } catch (error) {
      // Display error message if book search fails
      setMessage(error);
    }
  };

  return (
    <div style={{ padding: '20px', maxWidth: '600px', margin: '0 auto', backgroundColor: '#f9fafb', borderRadius: '8px', border: '1px solid #d1d5db' }}>
      <h2 style={{ fontSize: '1.5rem', marginBottom: '16px', color: '#1f2937' }}>Search Books</h2>

      <div style={{ marginBottom: '16px' }}>
        <label style={{ display: 'block', marginBottom: '8px' }}>
          Author Name:
          <input
            type="text"
            value={authorName}
            onChange={(e) => setAuthorName(e.target.value)}
            style={{ width: '100%', padding: '8px', borderRadius: '4px', border: '1px solid #d1d5db' }}
          />
        </label>

        <label style={{ display: 'block', marginBottom: '8px' }}>
          Book Name:
          <input
            type="text"
            value={bookName}
            onChange={(e) => setBookName(e.target.value)}
            style={{ width: '100%', padding: '8px', borderRadius: '4px', border: '1px solid #d1d5db' }}
          />
        </label>

        <button
          onClick={handleSearch}
          style={{ padding: '10px 20px', backgroundColor: '#3b82f6', color: '#fff', borderRadius: '4px', border: 'none', cursor: 'pointer' }}
        >
          Search
        </button>
      </div>

      {/* Display any message */}
      {message && <p style={{ color: message === 'Books found!' ? '#10b981' : '#ef4444' }}>{message}</p>}

      {/* Display the search results */}
      {books.length > 0 ? (
        <ul style={{ listStyleType: 'none', padding: 0 }}>
          {books.map((book) => (
            <li key={book.id} style={{ padding: '12px', borderBottom: '1px solid #e5e7eb' }}>
              <p><strong>Title:</strong> {book.title}</p>
              <p><strong>Author:</strong> {book.author}</p>
            </li>
          ))}
        </ul>
      ) : (
        <p>No books found.</p>
      )}
    </div>
  );
};

export default SearchBooks;
