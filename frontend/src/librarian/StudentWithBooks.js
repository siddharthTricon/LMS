import React, { useState, useEffect } from 'react';
import { getStudentsWithBooksRequest } from '../utils/axiosInstance'; // Axios function

const StudentsWithBooks = ({ students }) => { // Accept students as a prop
  const [message, setMessage] = useState('');

  // Check if students array is provided
  if (!Array.isArray(students)) {
    return <p>Loading...</p>; // Show loading state while fetching data
  }

  return (
    <div style={{ padding: '20px', maxWidth: '600px', margin: '0 auto', backgroundColor: '#f9fafb', borderRadius: '8px', border: '1px solid #d1d5db' }}>
      <h2 style={{ fontSize: '1.5rem', marginBottom: '16px', color: '#1f2937' }}>Students with Issued Books</h2>
      {message && <p style={{ color: '#ef4444' }}>{message}</p>}
      
      {students.length > 0 ? (
        <ul style={{ listStyleType: 'none', padding: 0 }}>
          {students.map((student) => (
            <li key={student.id} style={{ padding: '12px', borderBottom: '1px solid #e5e7eb' }}>
              <p><strong>ID:</strong> {student.id}</p>
              <p><strong>Name:</strong> {student.name}</p>
              <p><strong>Has Issued Books:</strong> {student.hasIssuedBooks ? 'Yes' : 'No'}</p>
              <p><strong>Issued Books:</strong></p>
              <ul>
                {student.issuedBooks && student.issuedBooks.length > 0 ? (
                  student.issuedBooks.map((book) => (
                    <li key={book.id} style={{ marginLeft: '20px' }}>
                      {book.title} by {book.author}
                    </li>
                  ))
                ) : (
                  <li style={{ marginLeft: '20px' }}>No books issued</li>
                )}
              </ul>
            </li>
          ))}
        </ul>
      ) : (
        <p>No students with issued books found.</p>
      )}
    </div>
  );
};

export default StudentsWithBooks;

// import React, { useState, useEffect } from 'react';
// import { getStudentsWithBooksRequest } from '../utils/axiosInstance'; // Axios function

// const StudentsWithBooks = () => {
//   // State to store students data
//   const [students, setStudents] = useState([]);
//   const [message, setMessage] = useState('');

//   // Fetch students with books when the component is mounted
//   useEffect(() => {
//     const fetchStudentsWithBooks = async () => {
//       try {
//         const response = await getStudentsWithBooksRequest();
//         console.log(response); // Check the response to ensure it's what you expect
//         if (Array.isArray(response)) {
//           setStudents(response); // Set the fetched students data if it's an array
//         } else {
//           setMessage('No students found.'); // Set message if it's not an array
//         }
//       } catch (error) {
//         setMessage('Failed to fetch students with books.');
//       }
//     };
    
//     fetchStudentsWithBooks();
//   }, []);

//   return (
//     <div style={{ padding: '20px', maxWidth: '600px', margin: '0 auto', backgroundColor: '#f9fafb', borderRadius: '8px', border: '1px solid #d1d5db' }}>
//       <h2 style={{ fontSize: '1.5rem', marginBottom: '16px', color: '#1f2937' }}>Students with Issued Books</h2>
//       {message && <p style={{ color: '#ef4444' }}>{message}</p>}
      
//       {Array.isArray(students) && students.length > 0 ? (
//         <ul style={{ listStyleType: 'none', padding: 0 }}>
//           {students.map((student) => (
//             <li key={student.id} style={{ padding: '12px', borderBottom: '1px solid #e5e7eb' }}>
//               <p><strong>ID:</strong> {student.id}</p>
//               <p><strong>Name:</strong> {student.name}</p>
              
//               {/* Add the line for hasIssuedBooks */}
//               <p><strong>Has Issued Books:</strong> {student.hasIssuedBooks ? 'Yes' : 'No'}</p>
              
//               <p><strong>Issued Books:</strong></p>
//               <ul>
//                 {student.issuedBooks.length > 0 ? (
//                   student.issuedBooks.map((book) => (
//                     <li key={book.id} style={{ marginLeft: '20px' }}>
//                       {book.title} by {book.author}
//                     </li>
//                   ))
//                 ) : (
//                   <li>No books issued</li>
//                 )}
//               </ul>
//             </li>
//           ))}
//         </ul>
//       ) : (
//         <p>No students with issued books found.</p>
//       )}
//     </div>
//   );
// };

// export default StudentsWithBooks;





// import React, { useState, useEffect } from 'react';
// import { getStudentsWithBooksRequest } from '../utils/axiosInstance'; // Axios function

// const StudentsWithBooks = ({ onBookIssued, onBookReturned }) => {
//   // State to store students data
//   const [students, setStudents] = useState([]);
//   const [message, setMessage] = useState('');
  
//   // State to manage loading state
//   const [loading, setLoading] = useState(true);

//   // Fetch students with books when the component is mounted or when book is issued/returned
//   useEffect(() => {
//     const fetchStudentsWithBooks = async () => {
//       setLoading(true); // Start loading
//       try {
//         const response = await getStudentsWithBooksRequest();
//         console.log(response); // Check the response to ensure it's what you expect
//         if (Array.isArray(response)) {
//           setStudents(response); // Set the fetched students data if it's an array
//         } else {
//           setMessage('No students found.'); // Set message if it's not an array
//         }
//       } catch (error) {
//         setMessage('Failed to fetch students with books.');
//       } finally {
//         setLoading(false); // End loading
//       }
//     };

//     fetchStudentsWithBooks();
//   }, [onBookIssued, onBookReturned]); // Dependency array updated to include callbacks

//   return (
//     <div style={{ padding: '20px', maxWidth: '600px', margin: '0 auto', backgroundColor: '#f9fafb', borderRadius: '8px', border: '1px solid #d1d5db' }}>
//       <h2 style={{ fontSize: '1.5rem', marginBottom: '16px', color: '#1f2937' }}>Students with Issued Books</h2>
//       {message && <p style={{ color: '#ef4444' }}>{message}</p>}
      
//       {loading ? (
//         <p>Loading...</p> // Show loading state while fetching data
//       ) : (
//         Array.isArray(students) && students.length > 0 ? (
//           <ul style={{ listStyleType: 'none', padding: 0 }}>
//             {students.map((student) => (
//               <li key={student.id} style={{ padding: '12px', borderBottom: '1px solid #e5e7eb' }}>
//                 <p><strong>ID:</strong> {student.id}</p>
//                 <p><strong>Name:</strong> {student.name}</p>
                
//                 {/* Add the line for hasIssuedBooks */}
//                 <p><strong>Has Issued Books:</strong> {student.hasIssuedBooks ? 'Yes' : 'No'}</p>
                
//                 <p><strong>Issued Books:</strong></p>
//                 <ul>
//                   {student.issuedBooks.length > 0 ? (
//                     student.issuedBooks.map((book) => (
//                       <li key={book.id} style={{ marginLeft: '20px' }}>
//                         {book.title} by {book.author}
//                       </li>
//                     ))
//                   ) : (
//                     <li>No books issued</li>
//                   )}
//                 </ul>
//               </li>
//             ))}
//           </ul>
//         ) : (
//           <p>No students with issued books found.</p>
//         )
//       )}
//     </div>
//   );
// };

// export default StudentsWithBooks;
