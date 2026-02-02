export const addThousandSeparator = (num) => {
  if (num == null || isNaN(num)) return "";

  //Convert number to string to handle decimal

  const numStr = num.toString();
  const parts = numStr.split(".");

  let integerPart = parts[0];
  let fractionalPart = parts[1];

  //Regex for Indian numbering system
  //It handles the first three digits, then every two digits

  const lastThree = integerPart.substring(integerPart.length - 3);
  const otherNumbers = integerPart.substring(0, integerPart.length - 3);

  if (otherNumbers !== "") {
    //Apply comma after every two digits for the 'otherNumbers' part
    const formattedOtherNumbers = otherNumbers.replace(
      /\B(?=(\d{2})+(?!\d))/g,
      ",",
    );
    integerPart = formattedOtherNumbers + "," + lastThree;
  } else {
    integerPart = lastThree; //No change if less than 4 digits
  }

  //Combine integer and fractionals part
  return fractionalPart ? `${integerPart}.${fractionalPart}` : integerPart;
};

// Convert income list into line chart data
export const prepareIncomeLineChartData = (incomes = []) => {
  return incomes.map((item) => ({
    date: new Date(item.date || item.createdAt).toLocaleString("default", {
      month: "short",
    }),
    amount: Number(item.amount) || 0,
  }));
};
