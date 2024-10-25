

// import React, { useState } from 'react';
// import { loginRequest } from '../utils/axiosInstance';  // Axios request function for login

// const Login = () => {
//   // State to store form inputs
//   const [name, setName] = useState('');
//   const [password, setPassword] = useState('');
//   const [role, setRole] = useState('admin'); // Default role is admin
//   const [message, setMessage] = useState('');

//   // Handle form submission
//   const handleSubmit = async (e) => {
//     e.preventDefault();

//     const credentials = {
//       name,
//       password,
//       role,  // Pass the selected role to the backend
//     };

//     try {
//       // Send request to backend to log in
//       const response = await loginRequest(credentials);
//       setMessage(response.message || 'Login successful');
//       // Clear form after submission
//       setName('');
//       setPassword('');
//       setRole('admin');  // Reset role to default
//     } catch (error) {
//       setMessage('Failed to login. Please check your credentials!');
//     }
//   };

//   return (
//     <div style={{ padding: '20px', maxWidth: '400px', margin: '0 auto', backgroundColor: '#f9fafb', borderRadius: '8px', border: '1px solid #d1d5db' }}>
//       <h2 style={{ fontSize: '1.5rem', marginBottom: '16px', color: '#1f2937' }}>Login</h2>
//       {message && <p style={{ color: '#ef4444' }}>{message}</p>}
//       <form onSubmit={handleSubmit}>
//         <div style={{ marginBottom: '12px' }}>
//           <label style={{ display: 'block', marginBottom: '8px', color: '#374151' }}>Name:</label>
//           <input
//             type="text"
//             value={name}
//             onChange={(e) => setName(e.target.value)}
//             placeholder="Enter your name"
//             style={{ padding: '8px 12px', width: '100%', border: '1px solid #d1d5db', borderRadius: '4px', fontSize: '1rem' }}
//             required
//           />
//         </div>

//         <div style={{ marginBottom: '12px' }}>
//           <label style={{ display: 'block', marginBottom: '8px', color: '#374151' }}>Password:</label>
//           <input
//             type="password"
//             value={password}
//             onChange={(e) => setPassword(e.target.value)}
//             placeholder="Enter your password"
//             style={{ padding: '8px 12px', width: '100%', border: '1px solid #d1d5db', borderRadius: '4px', fontSize: '1rem' }}
//             required
//           />
//         </div>

//         <div style={{ marginBottom: '12px' }}>
//           <label style={{ display: 'block', marginBottom: '8px', color: '#374151' }}>Role:</label>
//           <select
//             value={role}
//             onChange={(e) => setRole(e.target.value)}
//             style={{ padding: '8px 12px', width: '100%', border: '1px solid #d1d5db', borderRadius: '4px', fontSize: '1rem' }}
//           >
//             <option value="admin">Admin</option>
//             <option value="librarian">Librarian</option>
//             <option value="student">Student</option>
//           </select>
//         </div>

//         <button
//           type="submit"
//           style={{
//             padding: '10px 16px',
//             backgroundColor: '#3b82f6',
//             color: 'white',
//             border: 'none',
//             borderRadius: '4px',
//             fontSize: '1rem',
//             cursor: 'pointer',
//             width: '100%',
//           }}
//         >
//           Login
//         </button>
//       </form>
//     </div>
//   );
// };

// export default Login;






import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';  // Import useNavigate for redirection
import { loginRequest } from '../utils/axiosInstance';  // Axios request function for login

const Login = () => {
  // State to store form inputs
  const [name, setName] = useState('');
  const [password, setPassword] = useState('');
  const [role, setRole] = useState('admin'); // Default role is admin
  const [message, setMessage] = useState('');
  
  const navigate = useNavigate();  // Create a navigate function for redirection

  // Handle form submission
  const handleSubmit = async (e) => {
    e.preventDefault();

    const credentials = {
      name,
      password,
      role,  // Pass the selected role to the backend
    };

    try {
      // Send request to backend to log in
      const response = await loginRequest(credentials);
      setMessage(response.message || 'Login successful');
      
      // Clear form after submission
      setName('');
      setPassword('');
      setRole('admin');  // Reset role to default
      
      // Redirect based on role
      if (role === 'admin') {
        navigate('/admin');  // Navigate to the admin dashboard page
      } else if (role === 'librarian') {
        navigate('/librarian');  // Navigate to the librarian dashboard page
      } else if (role === 'student') {
        navigate('/student');  // Navigate to the student dashboard page
      }
      
    } catch (error) {
      setMessage('Failed to login. Please check your credentials!');
    }
  };

  return (
    <div style={{ padding: '20px', maxWidth: '400px', margin: '0 auto', backgroundColor: '#f9fafb', borderRadius: '8px', border: '1px solid #d1d5db' }}>
      <h2 style={{ fontSize: '1.5rem', marginBottom: '16px', color: '#1f2937' }}>Login</h2>
      {message && <p style={{ color: '#ef4444' }}>{message}</p>}
      <form onSubmit={handleSubmit}>
        <div style={{ marginBottom: '12px' }}>
          <label style={{ display: 'block', marginBottom: '8px', color: '#374151' }}>Name:</label>
          <input
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
            placeholder="Enter your name"
            style={{ padding: '8px 12px', width: '100%', border: '1px solid #d1d5db', borderRadius: '4px', fontSize: '1rem' }}
            required
          />
        </div>

        <div style={{ marginBottom: '12px' }}>
          <label style={{ display: 'block', marginBottom: '8px', color: '#374151' }}>Password:</label>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            placeholder="Enter your password"
            style={{ padding: '8px 12px', width: '100%', border: '1px solid #d1d5db', borderRadius: '4px', fontSize: '1rem' }}
            required
          />
        </div>

        <div style={{ marginBottom: '12px' }}>
          <label style={{ display: 'block', marginBottom: '8px', color: '#374151' }}>Role:</label>
          <select
            value={role}
            onChange={(e) => setRole(e.target.value)}
            style={{ padding: '8px 12px', width: '100%', border: '1px solid #d1d5db', borderRadius: '4px', fontSize: '1rem' }}
          >
            <option value="admin">Admin</option>
            <option value="librarian">Librarian</option>
            <option value="student">Student</option>
          </select>
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
          Login
        </button>
      </form>
    </div>
  );
};

export default Login;
