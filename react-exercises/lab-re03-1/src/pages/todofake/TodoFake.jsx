import React from "react";
import TodoList from "../../components/todolist/TodoList";

function TodoFake() {
    const initialTodoState = [
        {
            id: 0,
            task: "task 1",
            assignee: "User 1",
            date: "2023-05-09",
            completed: true
        },
        {
            id: 1,
            task: "task 2",
            assignee: "User 2",
            date: "2023-04-09",
            completed: false
        },
        {
            id: 2,
            task: "task 3",
            assignee: "User 1",
            date: "2023-04-09",
            completed: false
        },
        {
            id: 3,
            task: "task 4",
            assignee: "User 4",
            date: "2023-04-09",
            completed: false
        },
        {
            id: 4,
            task: "task 5",
            assignee: "User 1",
            date: "2023-04-09",
            completed: true
        },
        {
            id: 5,
            task: "task 6",
            assignee: "User 2",
            date: "2023-04-09",
            completed: false
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