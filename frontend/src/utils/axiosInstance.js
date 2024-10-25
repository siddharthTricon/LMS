// import axios from 'axios';


// const axiosInstance = axios.post({
//   baseURL: 'http://localhost:8080', 
//   headers: {
//     'Content-Type': 'application/json',
//   },
// });

// export default axiosInstance;



import axios from 'axios';

// Base URL of the backend
const BASE_URL = 'http://localhost:8080';

// Function to send POST request to add a new book
export const addBookRequest = async (book) => {
  try {
    const response = await axios.post(`${BASE_URL}/admin/addBook`, book);
    return response.data;
  } catch (error) {
    console.error('Error adding book:', error);
    throw error;
  }
};



export const deleteBookRequest = async (bookId) => {
  try {
    const response = await axios.delete(`${BASE_URL}/admin/deleteBook/${bookId}`);
    return response.data.message; // Assuming the backend sends a success message
  } catch (error) {
    throw new Error('Failed to delete the book');
  }
};



export const addStudentRequest = async (student) => {
  try {
    const response = await axios.post(`${BASE_URL}/admin/addStudent`, student);
    return response.data; // Return the response data (success message)
  } catch (error) {
    throw new Error(error.response ? error.response.data : 'Failed to add student');
  }
};



export const deleteStudentRequest = async (studentId) => {
  try {
    const response = await axios.delete(`${BASE_URL}/admin/deleteStudent/${studentId}`);
    return response.data; // Return the response data (success message)
  } catch (error) {
    throw new Error(error.response ? error.response.data : 'Failed to delete student');
  }
};


export const addLibrarianRequest = async (librarianData) => {
  try {
    const response = await axios.post(`${BASE_URL}/admin/addLibrarian`, librarianData);
    return response.data; // Return the response data
  } catch (error) {
    console.error('Error adding librarian:', error);
    throw error.response ? error.response.data : 'An error occurred while adding the librarian.';
  }
};

export const deleteLibrarianRequest = async (librarianId) => {
  try {
    const response = await axios.delete(`${BASE_URL}/admin/deleteLibrarian/${librarianId}`);
    return response.data; // Return the response data
  } catch (error) {
    console.error('Error deleting librarian:', error);
    throw error.response ? error.response.data : 'An error occurred while deleting the librarian.';
  }
};


export const addFineRequest = async (studentId, fineAmount) => {
  try {
    const response = await axios.post(`${BASE_URL}/admin/addFine`, { studentId, fineAmount });
    return response.data; // Return the response data
  } catch (error) {
    console.error('Error adding fine:', error);
    throw error.response ? error.response.data : 'An error occurred while adding the fine.';
  }
};


export const issueBookRequest = async (studentId, bookId) => {
  try {
    const response = await axios.post(`${BASE_URL}/librarian/issueBook`, null, {
      params: { studentId, bookId },
    });
    return response.data; // Return the response data
  } catch (error) {
    console.error('Error issuing book:', error);
    throw error.response ? error.response.data : 'An error occurred while issuing the book.';
  }
};





export const returnBookRequest = async (studentId, bookId) => {
  try {
    const response = await axios.post(`${BASE_URL}/librarian/returnBook`, null, {
      params: { studentId, bookId },
    });
    return response.data; // Return the response data
  } catch (error) {
    console.error('Error returning book:', error);
    throw error.response ? error.response.data : 'An error occurred while returning the book.';
  }
};




export const addFineRequestLib = async (studentId, fineAmount) => {
  try {
    const response = await axios.post(`${BASE_URL}/librarian/addFine`, null, {
      params: { studentId, fineAmount },
    });
    return response.data; // Return the response data
  } catch (error) {
    console.error('Error adding fine:', error);
    throw error.response ? error.response.data : 'An error occurred while adding the fine.';
  }
};


export const getStudentsWithBooksRequest = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/librarian/studentsWithBooks`);
    return response.data; // Return the response data (list of students)
  } catch (error) {
    console.error('Error fetching students with books:', error);
    throw error.response ? error.response.data : 'An error occurred while fetching the students.';
  }
};




// export const getBooksBySearchRequest = async (params) => {
//   try {
   
//     const response = await axios.get(`${BASE_URL}/student/searchBook`, { params });
//     return response.data; // Return the book data from the response
//   } catch (error) {
//     console.error('Error fetching books:', error);
//     throw error.response ? error.response.data : 'An error occurred while seraching for books.';
  
//   }
// };


export const getBooksBySearchRequest = async (authorName, bookName) => {
  try {
    // Send request to search books by author name and book name
    const response = await axios.get(`${BASE_URL}/student/searchBook`, {
      params: { authorName, bookName },
    });
    return response.data; // Return the response data
  } catch (error) {
    console.error('Error fetching books:', error);
    // Handle error by either returning the response error data or a custom error message
    throw error.response ? error.response.data : 'An error occurred while fetching the books.';
  }
};


export const issueBookRequestStud = async (studentId, bookId) => {
  try {
    // Send POST request to issue the book
    const response = await axios.post(`${BASE_URL}/student/takeBook`, null, {
      params: { studentId, bookId },
    });
    return response.data;  // Return the success message
  } catch (error) {
    console.error('Error issuing book:', error);
    // Return the error message or custom error
    throw error.response ? error.response.data : 'An error occurred while issuing the book.';
  }
};



export const returnBookRequestStud = async (studentId, bookId) => {
  try {
    // Send POST request to return the book
    const response = await axios.post(`${BASE_URL}/student/returnBook`, null, {
      params: { studentId, bookId },
    });
    return response.data;  // Return the success message
  } catch (error) {
    console.error('Error returning book:', error);
    // Return the error message or custom error
    throw error.response ? error.response.data : 'An error occurred while returning the book.';
  }
};


export const payFineRequest = async (studentId, amountPaid) => {
  try {
    // Send POST request to pay the fine
    const response = await axios.post(`${BASE_URL}/student/payFine`, null, {
      params: { studentId, amountPaid },
    });
    return response.data;  // Return the success message
  } catch (error) {
    console.error('Error paying fine:', error);
    // Return the error message or custom error
    throw error.response ? error.response.data : 'An error occurred while paying the fine.';
  }
};



export const loginRequest = async (credentials) => {
  try {
    const response = await axios.post(`${BASE_URL}/login`, credentials);
    return response.data; // Returns success message
  } catch (error) {
    console.error('Error during login:', error);
    throw error.response ? error.response.data : 'Login failed.';
  }
};