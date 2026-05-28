import axios from "axios";

const API = axios.create({

  // BACKEND URL
  baseURL: "http://localhost:8080",

  // JSON HEADERS
  headers: {
    "Content-Type": "application/json",
  },
});

// ADD TOKEN AUTOMATICALLY
API.interceptors.request.use(

  (config) => {

    const token =
      localStorage.getItem("token");

    // ATTACH JWT TOKEN
    if (token) {

      config.headers.Authorization =
        `Bearer ${token}`;
    }

    return config;
  },

  (error) => {

    return Promise.reject(error);
  }
);

export default API;