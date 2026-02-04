import { Coins, Wallet, WalletCards } from "lucide-react";
import Dashboard from "../components/Dashboard";
import InfoCard from "../components/InfoCard";
import { useUser } from "../hooks/useUser";
import { addThousandsSeparator } from "../utils/util";
import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import axiosConfig from "../utils/axiosConfig";
import { API_ENDPOINTS } from "../utils/apiEndpoints";
import toast from "react-hot-toast";
import RecentTransaction from "../components/RecentTransaction";
import Transactions from "../components/Transactions";
import FinanceOverview from "../components/FinanceOverview";

const Home = () => {
  useUser();

  const navigate = useNavigate();
  const [dashboardData, setDashboardData] = useState(null);
  const [loading, setLoading] = useState(false);

  const fetchDashboardData = async () => {
    if (loading) {
      return; a
    }

    try {
      const response = await axiosConfig.get(API_ENDPOINTS.DASHBOARD_DATA);
      if (response.status === 200) {
        setDashboardData(response.data);
      }
    } catch (error) {
      console.error(
        "Somethings went wrong while fetching dashboard data",
        error
      );
      toast.error("something went wrong");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchDashboardData();
    return () => { };
  }, []);

  return (
    <div>
      <Dashboard activeMenu="Dashboard">
        <div className="my-5 mx-auto">
          <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
            {/*Diplay the card */}
            <InfoCard
              icon={<WalletCards />}
              label="Total Balance"
              value={addThousandsSeparator(dashboardData?.totalBalance || 0)}
              color="bg-purple-800"
            />
            <InfoCard
              icon={<Wallet />}
              label="Total Income"
              value={addThousandsSeparator(dashboardData?.totalIncome || 0)}
              color="bg-green-800"
            />
            <InfoCard
              icon={<Coins />}
              label="Total Expense"
              value={addThousandsSeparator(dashboardData?.totalExpense || 0)}
              color="bg-red-800"
            />
          </div>
          <div className="grid grid-cols-1 md:grid-cols-2 gap-6 mt-6">
            {/*Recent Transaction */}
            <RecentTransaction
              transactions={dashboardData?.recentTransaction}
            />

            {/* Finance Overview */}
            <FinanceOverview
              totalBalance={dashboardData?.totalBalance || 0}
              totalIncome={dashboardData?.totalIncome || 0}
              totalExpense={dashboardData?.totalExpense || 0}

            />
          </div>
          <div className="grid grid-cols-1 md:grid-cols-2 gap-6 mt-6">

            {/*Expense transaction */}
            <Transactions
              transactions={dashboardData?.recent5Expense || []}
              onMore={() => navigate("/expense")}
              type="expense"
              title="Recent Expenses"
            />

            {/*Income transaction */}
            <Transactions
              transactions={dashboardData?.recent5Income || []}
              onMore={() => navigate("/income")}
              type="income"
              title="Recent Incomes "
            />
          </div>
        </div>
      </Dashboard>
    </div>
  );
};

export default Home;
