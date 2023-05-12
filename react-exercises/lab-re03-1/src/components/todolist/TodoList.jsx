import { React, useContext, useReducer, createContext } from "react";
import { List, Image, Input, Checkbox, Button, Icon } from "semantic-ui-react";

// Define a Context Provider to deal with initial states and reducer dispatch functions
const TodoContext = createContext();

// Define a function that handle fields update
function handleFieldUpdate(todoDispatch, itemId, itemField, itemValue) {
    // This function shall call the reducer dispatches
    todoDispatch({
        type: "updateField",
        payload: {
            "id": itemId,
            "field": itemField,
            "value": itemValue 
        }
    });

}

// Define the function to generate list items
function TodoItem({todoEntry}) {
    const todoDispatch = useContext(TodoContext)
    return (
        <List.Item>
            {/** TODO: Add images to each entry */}
            <Image avatar src='https://react.semantic-ui.com/images/avatar/small/rachel.png'/>
            <List.Content>
                <List.Header as='a'><b>Task: </b><Input placeholder="Enter task" onChange={(event) => handleFieldUpdate(todoDispatch, todoEntry.id, "task", event.target.value)} value={todoEntry.task} /></List.Header>
                <List.Description>
                    <b>Assignee: </b><Input placeholder="Enter assignee" onChange={(event) => handleFieldUpdate(todoDispatch, todoEntry.id, "assignee", event.target.value)} value={todoEntry.assignee} />
                    <b>Date: </b><Input type="date" onChange={(event) => handleFieldUpdate(todoDispatch, todoEntry.id, "date", event.target.value)} value={todoEntry.date} />
                    <Checkbox label="Completed" onChange={() => todoDispatch({ type: "completed", payload: todoEntry.id })} checked={todoEntry.completed} />
                    <Button onClick={() => {
                        todoDispatch({ type: 'delete', payload: todoEntry.id })
                        return;
                    }} icon labelPosition="right">
                        Delete
                        <Icon name="trash" />
                    </Button>
                </List.Description>
            </List.Content>

        </List.Item>
    );
}

function newTask() {
    return {
        id: Date.now(),
        task: "",
        assignee: "",
        date: Intl.DateTimeFormat("az", {
            day: "2-digit",
            month: "2-digit",
            year: "numeric"
        }).format((Date.now())),
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
            // Filter the todoState map by the provided todoAction.payload
            return todoState.filter((todoEntry) => todoEntry.id !== todoAction.payload);
        }
        case "completed": {
            // TODO: change the completed value of the provided ID item from true to false
            return todoState.map((item) => {
                // If the item ID matches the payload, change its completed value and return it
                if (item.id === todoAction.payload) {
                    return {
                        ...item,
                        completed: !item.completed
                    }
                }
                // Else, return the item without changes
                return item;
            });
        }
        case "updateField": {
            // Distructure the payload into the expected values
            const { id, field, value } = todoAction.payload;
            // Return all of the todoState items
            return todoState.map((item) => {
                // If the item ID matches the received ID in the payload, change its defined value and return it
                if (item.id === id) {
                    return {
                        ...item,
                        //https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Operators/Object_initializer#computed_property_names
                        [field]: value
                    };
                }
                // Else, return the item without changes
                return item;
            });
            
        }
        case "reset": {
            // TODO: reset the state to the initially provided To-Do
            return [
                // Empty string
            ];
        }
        // TODO: add the text fields update cases (task, assignee, date, etc...)
        default: {
            return todoState;    
        }
    };
}

export default function TodoList({receivedTodoState}) {
    // Define the useReducer to manage the entries of our To-Do list
    const [todoState, todoDispatch] = useReducer(todoReducer, receivedTodoState);

    return (
        <TodoContext.Provider value={todoDispatch} >
            {/* Create an "New To-Do entry" to add entries to the todoState */}
            <button onClick={() => { todoDispatch({ type: 'add' }) }}>New To-Do entry</button>
            {/* Create an "New To-Do entry" to add entries to the todoState */}
            <button onClick={() => { todoDispatch({ type: 'reset' }) }}>Reset To-Do list</button>
            {/* Use the Semantic List component to compose our To-Do list*/}
            <List animated verticalAlign='middle'>
                {todoState.map((todoEntry) => {
                    return(
                        // Delegate the entries rendering to the TodoItem component
                        // Important!! Each unique item in a list must have its own 'key'
                        <TodoItem  key={todoEntry.id} todoEntry={todoEntry} />
                    );
                })}    
            </List>
        </TodoContext.Provider>
    );
}