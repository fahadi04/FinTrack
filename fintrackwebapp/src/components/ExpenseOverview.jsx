import { IndianRupee, TrendingDown, CalendarDays, List } from "lucide-react";
import moment from "moment";

const ExpenseOverview = ({ transactions = [] }) => {
    const totalExpense = transactions.reduce(
        (sum, tx) => sum + Number(tx.amount || 0),
        0
    );

    const currentMonth = moment().format("MM-YYYY");

    const monthlyExpense = transactions
        .filter(
            (tx) => moment(tx.date).format("MM-YYYY") === currentMonth
        )
        .reduce((sum, tx) => sum + Number(tx.amount || 0), 0);

    const highestExpense =
        transactions.length > 0
            ? Math.max(...transactions.map((tx) => Number(tx.amount || 0)))
            : 0;

    const cards = [
        {
            title: "Total Expense",
            value: `₹ ${totalExpense.toLocaleString()}`,
            icon: IndianRupee,
            color: "bg-red-600",
        },
        {
            title: "This Month",
            value: `₹ ${monthlyExpense.toLocaleString()}`,
            icon: CalendarDays,
            color: "bg-blue-500",
        },
        {
            title: "Transactions",
            value: transactions.length,
            icon: List,
            color: "bg-green-500",
        },
    ];

    return (
        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-3 mb-4">
            {cards.map(({ title, value, icon: Icon, color }) => (
                <div
                    key={title}
                    className="rounded-2xl bg-white shadow-md p-4 flex items-center gap-4"
                >
                    <div className={`p-3 rounded-xl text-white ${color}`}>
                        <Icon size={22} />
                    </div>

                    <div>
                        <p className="text-sm text-gray-500">{title}</p>
                        <h3 className="text-lg font-semibold">{value}</h3>
                    </div>
                </div>
            ))}
        </div>
    );
};

export default ExpenseOverview;
