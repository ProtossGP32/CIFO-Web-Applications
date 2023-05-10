import { React, useContext, useReducer } from "react";
import { List, Image, Input, Checkbox, Button, Icon } from "semantic-ui-react";

// Define the function to generate list items
function TodoItem({todoEntry}) {
    //const todoDispatch = useContext(TodoContext)
    return (
        <List.Item>
            {/** TODO: Add images to each entry */}
            <Image avatar src='https://react.semantic-ui.com/images/avatar/small/rachel.png'/>
            <List.Content>
                <List.Header as='a'><b>Task: </b><Input placeholder="Enter task" value={todoEntry.task} /></List.Header>
                <List.Description>
                    <b>Assignee: </b><Input placeholder="Enter assignee" value={todoEntry.assignee} />
                    <br/>
                    <b>Date: </b><Input type="date" value={todoEntry.date} />
                    <br/>
                    <Checkbox label="Completed" value={todoEntry.completed} />
                </List.Description>
            </List.Content>
            <Button onClick={() => {
                //todoDispatch({ type: 'delete' })
                return;
            }} icon labelPosition="right">
                Delete
                <Icon name="trash" />
            </Button>
        </List.Item>
    );
}

function newTask() {
    return {
        id: Date.now(),
        task: "",
        assignee: "",
        date: Date.now(),
        completed: false
    }
}

// Define the reducer function
function todoReducer(todoState, todoAction) {
    switch(todoAction.type) {
        case "add": {
            return [
                ...todoState,
                // Add a new task at the end of the array
                newTask()
            ];
        }
        case "delete": {
            // TODO: return the todoState without the current entry
            return todoState;
        }
        default: {
            return todoState;    
        }
    };
}

export default function TodoList({receivedTodoState}) {
    // Define a context for this To-Do app
    //const TodoContext = useContext(null);
    // Define the useReducer to manage the entries of our To-Do list
    const [todoState, todoDispatch] = useReducer(todoReducer, receivedTodoState);

    return (
        //<TodoContext.Provider value={todoDispatch} >
        <>
            {/* Create an "New To-Do entry" to add entries to the todoState */}
            <button onClick={() => {
                todoDispatch({ type: 'add' })
            }}>New To-Do entry</button>
            {/* Use the Semantic List component to compose our To-Do list*/}
            <List>
                {todoState.map((todoEntry) => {
                    return(
                        // Delegate the entries rendering to the TodoItem component
                        <TodoItem todoEntry={todoEntry} />
                    );
                })}    
            </List>
        </>
    );
}