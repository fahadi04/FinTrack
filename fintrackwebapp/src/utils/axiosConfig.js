import axios from "axios";
import { BASE_URL } from "./apiEndpoints";

const axiosConfig = axios.create({
  // baseURL: "https://fintrack-wiyb.onrender.com/api/v1.0",
  baseURL: `${BASE_URL}`,
  headers: {
    "Content-Type": "application/json",
    Accept: "application/json",
  },
});

//List of endpoints that do not require authorization
const excludeEndpoints = [
  "/login",
  "/register",
  "/status",
  "/activate",
  "/health",
];

//Request Interceptor
axiosConfig.interceptors.request.use(
  (config) => {
    const shouldSkipToken = excludeEndpoints.some((endpoint) => {
      return config.url?.includes(endpoint);
    });

    if (!shouldSkipToken) {
      const accessToken = localStorage.getItem("token");
      if (accessToken) {
        config.headers.Authorization = `Bearer ${accessToken}`;
      }
    }

    return config;
  },
  (error) => {
    return Promise.reject(error);
  },
);

//Response Interceptor
axiosConfig.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response) {
      if (error.response.status === 401) {
        window.location.href = "/login";
      } else if (error.response.status === 500) {
        console.error("Server error. Please try again later");
      }
    } else if (error.code === "ECONNABORTED") {
      console.log("Request timeout. Please try again later");
    } else {
      console.log("Network error or server not reachable");
    }
    return Promise.reject(error);
  },
);

export default axiosConfig;
