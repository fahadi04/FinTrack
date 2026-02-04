import React from 'react'
import { addThousandsSeparator } from '../utils/util'
import CustomPieChart from '../components/CustomPieChart'

const FinanceOverview = ({ totalBalance, totalExpense, totalIncome }) => {
    const COLORS = ["#59168B", "#a0090e", "#016630"]

    const balanceData = [
        { name: 'Total Balance', amount: totalBalance },
        { name: 'Total Expenses', amount: totalExpense },
        { name: 'Total Incomes', amount: totalIncome }
    ]

    return (
        <div className='card'>
            <div className='flex items-center justify-between'>
                <h5 className='text-lg'>Financial Overview</h5>
            </div>

            <CustomPieChart
                data={balanceData}
                label="Total Balance"
                totalAmount={`₹${addThousandsSeparator(totalBalance)}`}
                colors={COLORS}
                showTextAnchor
            />
        </div>
    )
}

export default FinanceOverview
