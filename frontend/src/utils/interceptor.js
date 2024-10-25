// // axiosInterceptor.js
// import axiosInstance from './axiosInstance';

// // Request Interceptor
// axiosInstance.interceptors.request.use(
//   (config) => {
//     // Add custom logic before the request is sent
//     // const token = localStorage.getItem('token'); // Assume token is stored in localStorage
//     // if (token) {
//     //   config.headers['Authorization'] = `Bearer ${token}`;
//     // }
//     return config;
//   },
//   (error) => {
//     // Handle request error
//     return Promise.reject(error);
//   }
// );

// // Response Interceptor
// axiosInstance.interceptors.response.use(
//   (response) => {
//     // Handle successful response
//     return response;
//   },
//   (error) => {
//     // Handle response error
//     if (error.response && error.response.status === 401) {
//       console.log('Unauthorized, redirecting to login...');
//       window.location.href = '/login'; // Redirect user to login page
//     }
//     return Promise.reject(error);
//   }
// );

// export default axiosInstance;
