# 💰 FinTrack – Personal Finance Tracker

FinTrack is a full-stack personal finance tracking application that helps users manage **expenses and income**, categorize transactions, and track spending efficiently.

Built with **React + Tailwind CSS** on the frontend and **Spring Boot + MySQL** on the backend.

---

## 🚀 Features

### ✅ Authentication
- User registration & login
- Secure JWT-based authentication
- Protected routes

### ✅ Expense Management
- Add, view, and delete expenses
- Assign emoji icons to expenses
- Categorize expenses (Food, Rent, Travel, etc.)
- Date-based expense tracking

### ✅ Income Management
- Add and view income records
- Income categories (Salary, Bonus, Freelance, etc.)

### ✅ Categories
- Separate **Income** and **Expense** categories
- Dynamic category dropdown based on transaction type

### ✅ Dashboard
- Overview of total income and expenses
- Recent transactions display

---

## 🛠️ Tech Stack

### Frontend
- React
- Tailwind CSS
- Axios
- Moment.js
- Lucide Icons
- Emoji Picker

### Backend
- Spring Boot
- Spring Security
- JWT Authentication
- JPA / Hibernate
- MySQL

---

## 📂 Project Structure

### Frontend

src/
├── components/
│ ├── AddExpenseForm.jsx
│ ├── TransactionsInfoCard.jsx
│ ├── Input.jsx
│ └── EmojiPickerPopup.jsx
├── pages/
│ ├── Dashboard.jsx
│ ├── Expenses.jsx
│ └── Income.jsx
├── hooks/
├── util/
└── App.jsx

### Backend

src/main/java/com/project/fintrack
├── controller/
├── service/
├── repository/
├── dto/
├── entity/
├── security/
└── utils/


## ⚙️ Setup & Installation

### 🔹 Backend Setup

1. Clone the repository
git clone https://github.com/your-username/fintrack.git

2.Configure MySQL in application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/fintrack
spring.datasource.username=root
spring.datasource.password=your_password

3.Run the Spring Boot application
mvn spring-boot:run


