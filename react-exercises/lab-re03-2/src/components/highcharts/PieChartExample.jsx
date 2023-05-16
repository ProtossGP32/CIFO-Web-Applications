// Import dependencies
import React from "react";
import Highcharts from "highcharts/highstock";
import PieChart from "highcharts-react-official";

// Import your example data
import grades from "../../data/pieChartData.json"

// Define your PieChart options
const options = {
    title: {
        text: "Students"
    },
    chart: {
        type: "pie"
    },
    series: [
        {
            // Include the example grades as a data series
            data: grades
        }
    ]
};

// Create the app that prints the PieChart examples
const App = () => {
    return (
        <div style={{ margin: "40px" }}>
            {/* First PieChart example: simple pie */}
            <h2>Simple PieChart of Student grades</h2>
            <PieChart highcharts={Highcharts} options={options} />
        </div>
    );
};

export default App;