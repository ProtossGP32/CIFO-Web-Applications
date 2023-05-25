/**
The ToDoList.jsx consists of two components:

    - TodoItem: this component renders a single todo item with its details such as text, id, due date, author, and completion status.
        It also provides options to delete the item and update its completion status.
    - TodoList: this component renders a list of todo items.
        It maps over the todos array and renders a TodoItem component for each item. It also handles the loading state when the todos array is null.

The components are exported using the export default statement, indicating that they can be imported and used in other files.
 */

import React from "react";
import { List, Image, Input, Checkbox, Button, Icon } from "semantic-ui-react";

// TodoItem: read and render item from list
const TodoItem = ({ todo, onDelete, onUpdate }) => {
    //Define handlers for each operation
    const handleDelete = () => {
        onDelete(todo.id);
    };
    const handleUpdate = () => {
        onUpdate(todo);
    };

    // Render the component
    return (
        <List.Item>
            {/** TODO: Add images to each entry */}
            <Image avatar src='https://react.semantic-ui.com/images/avatar/small/rachel.png'/>
            <List.Content>
                <List.Header as='a'><b>Task: </b><Input placeholder="Enter task" onChange={handleUpdate} value={todo.task} /></List.Header>
                <List.Description>
                    <b>ID: </b>{todo.id}
                    <b>Assignee: </b><Input placeholder="Enter assignee" onChange={handleUpdate} value={todo.assignee} />
                    <b>Date: </b><Input type="date" onChange={handleUpdate} value={todo.createdAt} />
                    <Checkbox label="Completed" onChange={handleUpdate} checked={todo.completed} />
                    <Button onClick={handleDelete} icon labelPosition="right">Delete
                        <Icon name="trash" />
                    </Button>
                </List.Description>
            </List.Content>
        </List.Item>
    );
};