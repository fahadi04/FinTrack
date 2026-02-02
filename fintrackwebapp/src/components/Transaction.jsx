import { ArrowRight } from "lucide-react";
import TransactionInfoCard from "../components/TransactionInfoCard";
import moment from "moment";

const Transactions = ({ transaction, onMore, type, title }) => {
    return (
        <div className="bg-white rounded-xl shadow-md p-4">
            <div className="flex items-center justify-between">
                <h5 className="text-lg font-semibold">{title}</h5>
                <button
                    className="flex items-center gap-1 text-sm font-medium text-blue-600 hover:text-blue-800 cursor-pointer"
                    onClick={onMore}
                >
                    More <ArrowRight className="text-base" size={15} />
                </button>
            </div>

            <div className="mt-6">
                {transaction?.slice(0, 5)?.map((item) => (
                    <TransactionInfoCard
                        key={item.id}
                        title={item.name}
                        icon={item.icon}
                        date={moment(item.date).format("Do MMM YYYY")}
                        amount={item.amount}
                        type={type}
                        hideDeleteBtn
                    />
                ))}
            </div>
        </div>
    );
};

export default Transactions;
