import { Link } from "react-router-dom";
import FinTrack_logo from '../assets/FinTrack_LOGO.png'

export default function LandingPage() {
  return (
    <div className="min-h-screen flex flex-col bg-white">
      {/* Navbar */}
      <nav className="px-4 sm:px-6 md:px-10 py-4 md:py-6">
        <div className="max-w-7xl mx-auto flex justify-between items-center">
          <Link to="/" className="flex items-center gap-2 sm:gap-3 group">
            <img
              src={FinTrack_logo}
              alt="FinTrack Logo"
              className="h-8 w-8 sm:h-9 sm:w-9 rounded-full border border-gray-200 p-1 object-contain shadow-sm group-hover:border-purple-500 transition"
            />

            <span className="text-base sm:text-lg font-semibold text-black whitespace-nowrap">
              FinTrack
            </span>
          </Link>
          <div className="flex items-center space-x-2 sm:space-x-4">
            <Link
              to="/login"
              className="text-gray-700 font-medium text-sm sm:text-base hover:text-purple-600 transition"
            >
              Login
            </Link>
            <Link
              to="/signup"
              className="bg-purple-600 text-white px-4 sm:px-5 py-2 rounded-lg font-semibold text-sm sm:text-base hover:bg-purple-700 transition shadow-md"
            >
              Get Started
            </Link>
          </div>
        </div>
      </nav>

      {/* Hero Section */}
      <main className="flex flex-col items-center justify-center text-center flex-1 px-4">
        <h1 className="text-3xl sm:text-4xl md:text-5xl font-extrabold mb-4 sm:mb-6 leading-snug">
          FinTrack with Confidence
        </h1>

        <p className="text-gray-600 mb-6 sm:mb-8 max-w-2xl text-base sm:text-lg">
          Simplify your financial journey with smart, secure money management.
          Track income, expenses, and stay ahead of your goals.
        </p>

        <div className="flex flex-col sm:flex-row gap-3 sm:gap-4">
          <Link
            to="/signup"
            className="bg-purple-600 text-white px-6 py-3 rounded-md font-medium hover:bg-purple-700 transition shadow-lg"
          >
            Start for Free
          </Link>
          <Link
            to="/about"
            className="bg-gray-200 px-6 py-3 rounded-md font-medium hover:bg-gray-300 transition"
          >
            Discover More →
          </Link>
        </div>
      </main>
    </div>
  );
}
