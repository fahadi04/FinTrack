// export const BASE_URL = "https://fintrack-wiyb.onrender.com/api/v1.0";
export const BASE_URL = "http://localhost:8080/api/v1.0";
const CLOUDINARY_CLOUD_NAME = "dm6fuxjga";

export const API_ENDPOINTS = {
  LOGIN: `${BASE_URL}/login`,
  REGISTER: `${BASE_URL}/register`,
  GET_USER_INFO: "/profile",
  GET_ALL_CATEGORIES: "/categories",
  ADD_CATEGORY: "/categories",
  UPDATE_CATEGORY: (categoryId) => `/categories/${categoryId}`,
  DELETE_CATEGORY: (categoryId) => `/categories/${categoryId}`,
  GET_ALL_INCOMES: "/incomes",
  GET_ALL_EXPENSES: "/expenses",
  CATEGORY_BY_TYPE: (type) => `/categories/${type}`,
  ADD_INCOME: "/incomes",
  ADD_EXPENSES: "/expenses",
  DELETE_INCOME: (incomeId) => `/incomes/${incomeId}`,
  INCOME_EXCEL_DOWNLOAD: "/excel/download/income",
  EMAIL_INCOME: "/email/income-excel",
  EXPENSE_EXCEL_DOWNLOAD: "/excel/download/expense",
  EMAIL_EXPENSE: "/excel/expense-excel",
  APPLY_FILTERS: "/filters",
  DASHBOARD_DATA: "/dashboard",
  UPLOAD_IMAGE: `https://api.cloudinary.com/v1_1/${CLOUDINARY_CLOUD_NAME}/image/upload`,
};
