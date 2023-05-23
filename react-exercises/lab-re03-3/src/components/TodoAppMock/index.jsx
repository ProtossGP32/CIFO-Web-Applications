import React, { useState, useEffect, useContext } from "react";
import axios from "axios";

// API context
const ApiContext = React.createContext();

// Define the mockAPI URL
const API_URL = "https://64635bab7a9eead6fae45f33.mockapi.io/api/v1";

// Define a custom Hook to use the API context (we could also do it without it)
const useApiContext = () => useContext(ApiContext);

// Define the required ToDo components
// Main ToDo component
const Todos = () => {
    // Initialize the todos state (later on it can be refactored into a useReducer)
    const [todos, setTodos] = useState([]);
    
    // Define an async function to fetch all Todo items from the mock api
    const fetchTodos = async () => {
        try {
            // Request the full list of todo items
            const response = await axios.get(`${API_URL}/todoitems`);
            // Store the todoitems response into the todos state
            setTodos(response.data);
        } catch (error) {
            console.error(error);
        }
    };

    // Then, define a useEffect that only executes after the first page render
    // --> Main purpose: fetch the todo items from the mock api
    useEffect(() => {
        fetchTodos();
    }, []); // No dependencies, only execute after first render

    

};