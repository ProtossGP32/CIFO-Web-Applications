/*  The TodoContext is a React context created using the createContext function
    It serves as:
    - a repository of data related to TODO items in the React domain
    - a provider access to the TodoService functions, which utilize Axios to interact with
    an external API for data operations
    - a state manager: the TodoProvider component is responsible for managing the state of the
    TODO items using the useState hook
*/
import React, { createContext, useState, useEffect } from "react";
import TodoService from "./TodoService";

// Initialize a Todo context
const TodoContext = createContext();

// Define a provider for the Todo application
// --> Input param: children (TODO: further explanation on this)
const TodoProvider = ({ children }) => {
    // Define a state for the Todo items
    const [ todos, setTodos ] = useState([]);

    // Use a useEffect to fetch the list of ToDo items on first render only!
    useEffect(() => {
        fetchTodos();
    }, []);

    // Define a function to fetch all the Todo items
    // --> async function, as we don't want to block the execution of the parent thread
    const fetchTodos = async () => {
        // Retrieve all the Todo items from the API (URL hardcoded in TodoService.js)
        try {
            const receivedTodos = await TodoService.getAllTodos();
            // Set the 'todos' state to the received ones
            setTodos(receivedTodos);
        } catch (error) {
            // Notify an error in the operation
            // TODO: don't shadow the try-catch within the TodoService.getAllTodos() function
            console.error("Error fetching todos:", error);
        }
    };
    // Define a function to create a new Todo entry
    const createTodo = async (todo) => {
        try {
            // Send the already created 'todo' item to the API
            console.log("TodoContext: Creating new todo item...")
            const createdTodo = await TodoService.createTodo(todo);
            // Update the 'todos' state with the new entry
            // TODO: should it be better to call the fetchTodos instead?
            // TODO: Where does the prevTodos come from?
            // prevTodos: It is an updater function. Convention dictates that
            // the previous state should be named after the first letter of the state variable name
            // or as 'prev${state_variable_name}'
            console.log("TodoContext: Created todo item ", createdTodo);
            console.log("TodoContext: Setting local todo list");
            setTodos((prevTodos) => [ ...prevTodos, createdTodo ]);
            console.log("CreateTodo: new state --> ", todos);
        } catch (error) {
            // Notify an error in the operation
            // TODO: don't shadow the try-catch within the TodoService.createTodo() function
            console.error("Error creating a todo:", error);
        }
    };
    // Define a function to update an existing Todo entry
    const updateTodo = async (updatedTodo) => {
        try {
            // Send the already updated 'todo' item to the API
            // Ignore the returned result of the operation
            await TodoService.updateTodo(updatedTodo);
            // Set the next state of todos using again the updater function
            setTodos((prevTodos) => {
                // Initialized an updated todos variable
                const updatedTodos = [...prevTodos];
                // Look for the index of the updated item within the current todo items
                // findIndex: implicit method of arrays
                const todoIndex = updatedTodos.findIndex((todo) => todo.id === updatedTodo.id);
                // Replace the found index position of the array with the updated value
                updatedTodos[todoIndex] = updatedTodo;
                // Return the updated array of todo items
                return updatedTodos;
            });
        } catch (error) {
            // Notify an error in the operation
            // TODO: don't shadow the try-catch within the TodoService.updateTodo() function
            console.error("Error updating todo item:", error);
        }
    };
    // Define a function to delete a todo item
    const deleteTodo = async (todoId) => {
        try {
            // Send the ID of the item to delete to the API
            // Ignore the returned result of the operation
            const deleteResponse = await TodoService.deleteTodo(todoId);
            console.log("TodoContext.deleteTodo: after delete --> ", deleteResponse);
            // Filter that same entry from the todo state variable
            // Again, use an updater function
            setTodos((prevTodos) => prevTodos.filter((todo) => todo.id !== todoId));
        } catch (error) {
            // Notify an error in the operation
            // TODO: don't shadow the try-catch within the TodoService.deleteTodo() function
            console.error("Error deleteing todo item", error);
        }
    };

    // Render of the TodoContext component
    return (
        <TodoContext.Provider value = {{ todos, createTodo, updateTodo, deleteTodo }}>
            {/* TODO: Explain how to understand this children */}
            {children}
        </TodoContext.Provider>
    );
};

// TODO: Explain the difference between an 'export default function' and this
export { TodoContext, TodoProvider };