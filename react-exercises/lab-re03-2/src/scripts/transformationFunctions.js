// Define a middleware function that tranforms the original data to compatible Highcharts component input
function getTodoCountsByAuthor(todoitems) {

    // First initialize an author map
    const authorMap = {};

    // Then, populate it based on the input data
    todoitems.forEach((item) => {
        // Aggregate data per Author
        // Distructure the item and only keep its author
        const { author } = item;
        if (authorMap[author]) {
            authorMap[author]++;
        } else {
            authorMap[author] = 1;
        }
    });

    // Compose the result object
    const result = Object.entries(authorMap)
        .map(([author, count]) => ({
            name: author,
            y: count
        }));

    return result;
};

export default getTodoCountsByAuthor;