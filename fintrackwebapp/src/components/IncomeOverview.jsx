import { TrendingUp, IndianRupee } from "lucide-react";

const IncomeOverview = ({ incomes = [] }) => {
    const totalIncome = incomes.reduce(
        (sum, income) => sum + Number(income.amount || 0),
        0
    );

    const latestIncome = incomes[0];

    return (
        <div className="grid grid-cols-1 sm:grid-cols-3 gap-4">
            {/* Total Income */}
            <div className="bg-white rounded-xl shadow p-5 flex items-center gap-4">
                <div className="p-3 bg-green-100 rounded-full">
                    <IndianRupee className="text-green-600" size={24} />
                </div>
                <div>
                    <p className="text-sm text-gray-500">Total Income</p>
                    <h2 className="text-xl font-semibold text-gray-800">
                        ₹ {totalIncome.toLocaleString("en-IN")}
                    </h2>
                </div>
            </div>

            {/* Total Transactions */}
            <div className="bg-white rounded-xl shadow p-5 flex items-center gap-4">
                <div className="p-3 bg-blue-100 rounded-full">
                    <TrendingUp className="text-blue-600" size={24} />
                </div>
                <div>
                    <p className="text-sm text-gray-500">Total Entries</p>
                    <h2 className="text-xl font-semibold text-gray-800">
                        {incomes.length}
                    </h2>
                </div>
            </div>

            {/* Latest Income */}
            <div className="bg-white rounded-xl shadow p-5">
                <p className="text-sm text-gray-500 mb-1">Latest Income</p>
                {latestIncome ? (
                    <>
                        <h2 className="text-lg font-semibold text-gray-800">
                            ₹ {Number(latestIncome.amount).toLocaleString("en-IN")}
                        </h2>
                        <p className="text-sm text-gray-500 truncate">
                            {latestIncome.name}
                        </p>
                        <p className="text-xs text-gray-400">
                            {new Date(latestIncome.date).toLocaleDateString()}
                        </p>
                    </>
                ) : (
                    <p className="text-sm text-gray-400">No income added yet</p>
                )}
            </div>
        </div>
    );
};

export default IncomeOverview;
