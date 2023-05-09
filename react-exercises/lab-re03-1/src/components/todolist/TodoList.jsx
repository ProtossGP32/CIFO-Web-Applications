import { React, useReducer } from "react";
import { List, Image, Input, Checkbox } from "semantic-ui-react";

// Define the function to generate list items
function TodoItem({todoEntry}) {
    return (
        <List.Item>
            {/** TODO: Add images to each entry */}
            <Image avatar src='https://react.semantic-ui.com/images/avatar/small/rachel.png'/>
            <List.Content>
                <List.Header as='a'><b>Task: </b>{todoEntry.task}</List.Header>
                <List.Description>
                    <b>Assignee: </b><Input placeholder="Enter assignee">{todoEntry.assignee}</Input>
                    <br/>
                    <b>Date: </b><Input placeholder="Enter date">{todoEntry.date}</Input>
                    <br/>
                    <Checkbox label="Completed" value={todoEntry.completed} />
                </List.Description>
            </List.Content>
        </List.Item>
    );
}

// Define the reducer function
function todoReducer(todoState, todoAction) {
    switch(todoAction.type) {
        case "add": {
            return [
                ...todoState,
                {
                    id: Date.now(),
                    task: "",
                    assignee: "",
                    date: Date.now(),
                    completed: false
                }];
        }
        
        default: {
            return todoState;    
        }
    };
}

export default function TodoList({receivedTodoState}) {
    const [todoState, todoDispatch] = useReducer(todoReducer, receivedTodoState);
    return (
        <>
            <button onClick={() => {
                todoDispatch({ type: 'add' })
            }}>New To-Do entry</button>
            <List>
                {todoState.map((todoEntry) => {
                    return(
                        <TodoItem todoEntry={todoEntry} />
                    );
                })}    
            </List>
        </>
    );
}