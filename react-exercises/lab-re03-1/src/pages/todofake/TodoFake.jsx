import React from "react";
import TodoList from "../../components/todolist/TodoList";

function TodoFake() {
    const initialTodoState = [
        {
            id: 0,
            task: "task 1",
            assignee: "User 1",
            date: "2023/05/09",
            completed: 0
        },
        {
            id: 1,
            task: "task 2",
            assignee: "User 2",
            date: "2023/04/09",
            completed: 1
        }
    ];

    return (
        <>
        <h1>To-Do Fake - hardcoded list of tasks</h1>
        {/*<TodoList initialTodoState/>*/}
        <TodoList receivedTodoState={initialTodoState}/>
        </>
    );
};

export default TodoFake;