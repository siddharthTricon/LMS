import React, { useEffect, useState } from 'react';
// import BookList from '../components/BookList';
import IssueBook from '../librarian/IssueBook';
import ReturnBook from '../librarian/ReturnBook';
import AddFine from '../librarian/AddFine';
import StudentsWithBooks from '../librarian/StudentWithBooks';
import { getStudentsWithBooksRequest } from '../utils/axiosInstance';
import { useNavigate } from 'react-router-dom';

const Librarian = () => {

  const navigate = useNavigate();

  const handleLogout = () => {
    navigate('/logout');  // Redirect to the Logout component
  }

  const [students, setStudents] = useState([]);
  const [message, setMessage] = useState('');

  // Fetch students with books when the component is mounted
  useEffect(() => {
    const fetchStudentsWithBooks = async () => {
      try {
        const response = await getStudentsWithBooksRequest();
        console.log(response); // Check the response to ensure it's what you expect
        if (Array.isArray(response)) {
          setStudents(response); // Set the fetched students data if it's an array
        } else {
          setMessage('No students found.'); // Set message if it's not an array
        }
      } catch (error) {
        setMessage('Failed to fetch students with books.');
      }
    };

    fetchStudentsWithBooks();
  }, []);

  // Function to handle updating the students list after issuing or returning books
  const updateStudentsList = async () => {
    const response = await getStudentsWithBooksRequest();
    if (Array.isArray(response)) {
      setStudents(response); // Update the students state
    }
  };


  return (
    <div className="p-4">
      <h2 className="text-2xl">Librarian Dashboard</h2>
      {/* <BookList /> */}
      {/* <IssueBook />
      <ReturnBook /> */}
      <IssueBook onUpdate={updateStudentsList} />
      <ReturnBook onUpdate={updateStudentsList} />
      <AddFine />
      {/* <StudentsWithBooks /> */}
      <StudentsWithBooks students={students} />

      <button
        onClick={handleLogout}
        style={{
          padding: '10px 16px',
          backgroundColor: '#ef4444',
          color: 'white',
          border: 'none',
          borderRadius: '4px',
          fontSize: '1rem',
          cursor: 'pointer',
          marginTop: '20px',
        }}
      >
        Logout
      </button>


    </div>
  );
};

export default Librarian;
