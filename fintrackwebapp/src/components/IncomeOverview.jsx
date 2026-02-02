import React, { useEffect, useState } from 'react'
import { prepareIncomeLineChartData } from "../utils/util"
import CustomLineChart from "../components/CustomLineChart"
import { Plus } from 'lucide-react';

function IncomeOverview({ transactions, onAddIncome }) {
    const [chartData, setChartData] = useState([]);

    useEffect(() => {
        const result = prepareIncomeLineChartData(transactions);
        // console.log(result);
        setChartData(result);
        return () => { };
    }, [transactions])

    return (
        <div className='card'>
            <div className="flex items-center justify-between">
                {/* Left side: title + description */}
                <div>
                    <h5 className="text-lg font-semibold">Income Overview</h5>
                    <p className="text-xs text-gray-400 mt-0.5">
                        Track your earnings over time and analyze your income trends.
                    </p>
                </div>

                {/* Right side: button */}
                <button
                    className="add-btn flex items-center gap-2"
                    onClick={onAddIncome}
                >
                    <Plus size={15} />
                    Add Income
                </button>
            </div>

            <div className="mt-10">
                <CustomLineChart data={chartData} />
            </div>

        </div>
    )
}

export default IncomeOverview