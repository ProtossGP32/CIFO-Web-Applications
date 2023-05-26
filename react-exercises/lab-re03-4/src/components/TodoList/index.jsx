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
                <List.Header as='a'><b>Task: </b><Input placeholder="Enter task" value={todo.task} /></List.Header>
                <List.Description>
                    <b>ID: </b>{todo.id}
                    <b>Assignee: </b><Input value={todo.assignee} />
                    <b>Date: </b><Input type="date" value={todo.createdAt} />
                    <Checkbox label="Completed" checked={todo.completed} />
                    <Button onClick={handleDelete} icon labelPosition="right">Delete
                        <Icon name="trash" />
                    </Button>
                    <Button onClick={handleUpdate} icon labelPosition="right">Update
                        <Icon name="refresh" />
                    </Button>
                </List.Description>
            </List.Content>
        </List.Item>
    );
};

// TodoList: read items and create the To-Do list
const TodoList = ({ todos, onDelete, onUpdate }) => {
    // Ensure that we show the Loading state while we don't have the 'todo' items
    if (todos === null) {
        return <p>Loading...</p>;
    }

    return (
        // Initialize a semantic List
        <List animated verticalAlign='middle'>
            {todos.map((todoEntry) => {
                return(
                    // Delegate the entries rendering to the TodoItem component
                    // Important!! Each unique item in a list must have its own 'key'
                    <TodoItem 
                        key={todoEntry.id}
                        todo={todoEntry}
                        onDelete={onDelete}
                        onUpdate={onUpdate}
                    />
                );
            })}    
        </List>
    );
};

export default TodoList;