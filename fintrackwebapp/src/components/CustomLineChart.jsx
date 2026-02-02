import {
    LineChart,
    Line,
    XAxis,
    YAxis,
    CartesianGrid,
    Tooltip,
    ResponsiveContainer
} from 'recharts';

const CustomLineChart = ({ data }) => {
    if (!data || data.length === 0) {
        return (
            <p className="text-sm text-gray-400 text-center">
                No income data available
            </p>
        );
    }

    return (
        <div className="w-full h-72">
            <ResponsiveContainer width="100%" height="100%">
                <LineChart data={data}>
                    <CartesianGrid strokeDasharray="3 3" />
                    <XAxis dataKey="date" />
                    <YAxis />
                    <Tooltip />
                    <Line
                        type="monotone"
                        dataKey="amount"
                        stroke="#4f46e5"
                        strokeWidth={2}
                        dot={{ r: 3 }}
                    />
                </LineChart>
            </ResponsiveContainer>
        </div>
    );
};

export default CustomLineChart;
