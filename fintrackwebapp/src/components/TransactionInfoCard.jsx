import { UtensilsCrossed, Trash2, TrendingUp, TrendingDown } from 'lucide-react'
import React from 'react'
import { addThousandSeparator } from '../utils/util'

const TransactionCard = ({
    icon,
    title,
    date,
    amount,
    type,
    hideDeleteBtn,
    onDelete
}) => {
    const amountStyle =
        type === 'income'
            ? 'bg-green-50 text-green-800'
            : 'bg-red-50 text-red-800'

    return (
        <div className='group relative flex items-center justify-between gap-4 mt-2 p-3 rounded-lg hover:bg-gray-100/60'>
            {/* Left side */}
            <div className='flex items-center gap-4'>
                <div className='w-12 h-12 flex items-center justify-center text-xl bg-gray-100 rounded-full'>
                    {icon ? (
                        <img src={icon} alt={title} className='w-6 h-6' />
                    ) : (
                        <UtensilsCrossed className='text-purple-800' />
                    )}
                </div>

                <div>
                    <p className='font-medium text-gray-800'>{title}</p>
                    <p className='text-sm text-gray-500'>{date}</p>
                </div>
            </div>

            {/* Right side */}
            <div className='flex items-center gap-3'>
                <span className={`px-3 py-1 rounded-full text-sm font-semibold ${amountStyle}`}>
                    {type === 'income' ? '+' : '-'} ${addThousandSeparator(amount)}
                </span>
                {type === 'income' ? (
                    <TrendingUp size={15} />
                ) : (
                    <TrendingDown size={15} />
                )}

                {!hideDeleteBtn && (
                    <button
                        onClick={onDelete}
                        className='opacity-0 group-hover:opacity-100 text-gray-400 hover:text-red-600 transition'
                    >
                        <Trash2 size={18} />
                    </button>
                )}
            </div>
        </div>
    )
}

export default TransactionCard;
