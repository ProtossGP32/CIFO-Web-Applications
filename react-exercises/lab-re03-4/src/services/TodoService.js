import axios from "axios";

// TODO: parametrize the API_BASE_URL value
const API_BASE_URL = "https://64635bab7a9eead6fae45f33.mockapi.io/api/v1/";

// Define the Service layer of our Todo application here
// Logical object: each key can be a function or a prop
const TodoService = {

    // Define the logic to retrieve all Todo's from the API
    getAllTodos: async () => {
        try {
            // Retrieve data from the API using the GET method
            const response = await axios.get(`${API_BASE_URL}/todoitems`);
            // Return only the JSON containing the actual todo items data
            return response.data;
        } catch (error) {
            // Inform that data couldn't be retrieved
            console.error("Error retrieving todos:", error);
            throw error;
        }
    },
    // Define the logic to create a new Todo item
    // --> Input params: already composed 'todo' item
    createTodo: async (todo) => {
        try {
            // Send the 'todo' to the API using the POST method
            const response = await axios.post(`${API_BASE_URL}/todoitems`)
            // Return the response of the operation
            return response.data
        } catch (error) {
            // Inform that data couldn't be created
            console.error("Error creating todo:", error);
            throw error;
        }
    },
    // Define the logic to update a 'todo' item
    // --> Input param: update and already composed 'todo' item
    updateTodo: async (todo) => {
        try {
            // Send the updated 'todo' to the API using the PUT method
            // --> We need to provide the ID of the updated 'todo' in the URL
            const response = await axios.put(`${API_BASE_URL}/todoitems/${todo.id}`);
            // Return the response of the operation
            return response.data;
        } catch (error) {
            // Inform that data couldn't be updated
            console.error("Error updating todo:", error);
            throw error;
        }
    },
    // Define the logic to delete a 'todo' item
    // --> Input param: we only need the 'todo' ID
    deleteTodo: async (todoId) => {
        try {
            // Send the 'todo' ID to be deleted to the API using the DELETE method
            // --> We just need to provide an ID
            const response = await axios.delete(`${API_BASE_URL}/todoitems/${todoId}`);
            // Return the response of the operation
            return response.data;
        } catch (error) {
            // Inform that data couldn't be deleted
            console.error("Error deleting todo:", error);
            throw error;
        }
    }
};