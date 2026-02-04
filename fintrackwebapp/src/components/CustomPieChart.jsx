import React from "react";
import {
    PieChart,
    Pie,
    Cell,
    Tooltip,
    ResponsiveContainer
} from "recharts";

const CustomPieChart = ({
    data,
    label,
    totalAmount,
    colors,
    showTextAnchor = false
}) => {
    const chartData = data.map((item) => ({
        name: item.name,
        value: item.amount
    }));

    return (
        <>
            {/* PIE */}
            <div className="relative w-full h-[240px] mt-25">
                <ResponsiveContainer width="100%" height="100%">
                    <PieChart>
                        <Pie
                            data={chartData}
                            dataKey="value"
                            nameKey="name"
                            innerRadius={80}
                            outerRadius={115}
                            paddingAngle={4}
                            cornerRadius={14}
                            isAnimationActive
                            animationDuration={900}
                        >
                            {chartData.map((_, index) => (
                                <Cell
                                    key={index}
                                    fill={colors[index % colors.length]}
                                />
                            ))}
                        </Pie>

                        <Tooltip formatter={(v) => `₹${v}`} />
                    </PieChart>
                </ResponsiveContainer>

                {showTextAnchor && (
                    <div className="absolute inset-0 flex flex-col items-center justify-center text-center pointer-events-none">
                        <p className="text-sm text-gray-500">{label}</p>
                        <h3 className="text-xl font-semibold">{totalAmount}</h3>
                    </div>
                )}
            </div>

            <div className="mt-25 flex flex-wrap justify-center gap-6">
                {data.map((item, index) => (
                    <div
                        key={item.name}
                        className="flex items-center gap-2 text-sm"
                    >
                        <span
                            className="w-3 h-3 rounded-full"
                            style={{ backgroundColor: colors[index % colors.length] }}
                        />

                        <span className="text-gray-600 whitespace-nowrap">
                            {item.name}
                        </span>
                    </div>
                ))}
            </div>

        </>
    );
};

export default CustomPieChart;
