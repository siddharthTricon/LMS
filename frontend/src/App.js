import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import Header from './components/Header';
import Footer from './components/Footer';
import Home from './pages/Home';
import Admin from './pages/Admin';
import Librarian from './pages/Librarian';
import Student from './pages/Student';
import Login from './pages/LoginPage';
import Logout from './pages/LogoutPage';
import DeleteUser from './pages/DeleteUsers';
import { AuthProvider } from './context/AuthContext';


function App() {
  return (
    <AuthProvider>
    <Router>
      <Header />
      <div className="container mx-auto">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<Login />} />
          <Route path="/admin" element={<Admin />} />
          <Route path="/librarian" element={<Librarian />} />
          <Route path="/student" element={<Student />} />
          <Route path="/logout" element={<Logout />} />
          <Route path="/delete" element={<DeleteUser />} />
          <Route path="*" element={<Navigate to="/" />} />
        </Routes>
      </div>
      <Footer />
    </Router>
    </AuthProvider>
  );
}

export default App;
