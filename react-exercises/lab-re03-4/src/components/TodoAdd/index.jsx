/*
ToDoAdd.jsx is used for creating new todo items.

It renders a form with input fields for entering the text, author, and due date of a new todo item. It uses React’s useState hook to manage the state of the input values. When the form is submitted, the onCreate function is called with an object containing the entered values.

The component exports the TodoAdd component using the export default statement, allowing it to be imported and used in other files.
*/
import React from "react";

// TodoAdd: accepts a function as a parameter that will deal with the new item data
const TodoAdd = ({ onCreate }) => {
    // Initialize all the fields of a Todo item
    // --> Fields must match the schema in your API
    // TODO: convert all of this into a useReducer
    const [task, setTask] = React.useState("");
    const [assignee, setAssignee] = React.useState("");
    const [avatar, setAvatar] = React.useState("");

    // Define the function that will handle the creation of a new item
    const handleSubmit = () => {
        onCreate({
            task: task,
            assignee: assignee,
            avatar: avatar,
            createdAt: Date.now(),
            completed: false
        });
    };

    // Rende the component
    return (
        <React.Fragment>
            <hr />
            <h2>Create new Todo</h2>
            <hr />
            {/** Create a form for the new Todo item */}
            <form onSubmit={handleSubmit}>
                <p>
                    <label>Task</label>
                    <br />
                    <input
                        type="text"
                        value={task}
                        onChange={(event) => setTask(event.target.value)}
                        placeholder="Enter task description"
                    />
                </p>
                <p>
                    <label>Assignee</label>
                    <br />
                    <input
                        type="text"
                        value={assignee}
                        onChange={(event) => setAssignee(event.target.value)}
                        placeholder="Enter assignee"
                    />
                </p>
                {/** TODO: Set avatar */}
                {/** CreatedAt and completed values are initialized on creation time */}
                <button type="submit">Add Todo</button>
                <br />
                <hr />
            </form>
        </React.Fragment>
    );
};

export default TodoAdd;