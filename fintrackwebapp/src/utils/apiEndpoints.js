// export const BASE_URL = "https://fintrack-wiyb.onrender.com/api/v1.0";
export const BASE_URL = "http://localhost:8080/api/v1.0";
const CLOUDINARY_CLOUD_NAME = "dm6fuxjga";

export const API_ENDPOINTS = {
  LOGIN: `${BASE_URL}/login`,
  REGISTER: `${BASE_URL}/register`,
  GET_USER_INFO: "/profile",
  GET_ALL_CATEOGRIES: "/categories",
  ADD_CATEGORY: "/categories",
  UPDATE_CATEGORY: (categoryId) => `/categories/${categoryId}`,
  DELETE_CATEGORY: (categoryId) => `/categories/${categoryId}`,
  GET_ALL_INCOME: "/incomes",
  CATEGORY_BY_TYPE: (type) => `/categories/${type}`,
  ADD_INCOME: "/incomes",
  DELETE_INCOME:(incomeId)=>`/incomes/${incomeId}`,
  UPLOAD_IMAGE: `https://api.cloudinary.com/v1_1/${CLOUDINARY_CLOUD_NAME}/image/upload`,
};
