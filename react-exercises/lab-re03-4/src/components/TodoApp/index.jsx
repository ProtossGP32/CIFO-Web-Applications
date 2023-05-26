/**
 * TodoApp: main component
 * It renders:
 * - `header`
 * - a `TodoAdd` component for creating new TODO items
 * - a `TodoList` component for displaying the list of TODO items
 * Within TodoApp, the `useContext` hook is used to access the `TodoContext`
 * and retrieve the `todos`, `createTodo`, `updateTodo` and `deleteTodo` functions
 * from the context
 * */
import React, { useContext } from "react";
import TodoAdd from "../TodoAdd";
import TodoList from "../TodoList";
import { TodoContext, TodoProvider } from "../../services/TodoContext";

// TodoApp
const TodoApp = () => {
    // distructure the content of the TodoContext
    const { todos, createTodo, updateTodo, deleteTodo } = useContext(TodoContext);

    // Create a handler function for the `createTodo`
    const handleCreateTodo = (todo) => {
        createTodo(todo);
    };

    // Create a handler function for the `updateTodo`
    const handleUpdateTodo = (todo) => {
        updateTodo(todo);
    };

    // Create a handler function for the `deleteTodo`
    const handleDeleteTodo = (todoId) => {
        deleteTodo(todoId);
    };

    // Render the TodoApp component
    return (
        <div>
            <h1>Todo App</h1>
            <TodoAdd onCreate={handleCreateTodo} />
            <TodoList
                todos={todos}
                onDelete={handleDeleteTodo}
                onUpdate={handleUpdateTodo}
            />
        </div>
    );
};

// Now define the default App
const App = () => {
    // Simply render the TodoApp component within the TodoProvider context
    return (
        <TodoProvider>
            <TodoApp />
        </TodoProvider>
    );
};

export default App;