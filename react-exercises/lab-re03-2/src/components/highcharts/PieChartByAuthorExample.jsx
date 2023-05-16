// Import dependencies
import React from "react";
import Highcharts from "highcharts/highstock";
import PieChart from "highcharts-react-official";

// Import your example data
import todoitems from "../../data/initialTodos.json"

// Import a middleware function that tranforms the original data to compatible Highcharts component input
import getTodoCountsByAuthor from "../../scripts/transformationFunctions.js";

// Define your PieChart options
const options = {
    title: {
        text: "Number of To-Do items by Author"
    },
    chart: {
        type: "pie"
    },
    series: [
        {
            // The data series is the output of the transformation function
            data: getTodoCountsByAuthor(todoitems)
        }
    ]
};

// Create the app that prints the PieChart examples
const App = () => {
    return (
        <div style={{ margin: "40px" }}>
            {/* PieChart of a data aggregation by Author */}
            <h2>PieChart To-Do tasks grouped by Author</h2>
            <PieChart highcharts={Highcharts} options={options} />
        </div>
    );
};

export default App;