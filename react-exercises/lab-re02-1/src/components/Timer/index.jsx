/*
    Timer example: combination of useRef, useReducer, and useEffect
*/
import { useRef, useReducer, useEffect } from "react";

// Define an initialState of the timer
const initialTimerState = {
    isRunning: false,
    time: 0
}

// Define the reducer function that manages the features of the timer
function reducer(state, action) {
    switch (action.type) {
        case "start":
            // Change the 'isRunning' value to true
            // --> This will trigger the useEffect and set an interval task to update the time each second
            return {
                ...state,
                isRunning: true
            };
        case "stop":
            // Change the 'isRunning' value to false
            // --> This will trigger the useEffect again and clear the previously defined interval
            return {
                ...state,
                isRunning: false
            };
        case "reset":
            // Returns the initial state of the timer
            // --> This will trigger the useEffect if the isRunning state changes; else, it won't need
            return initialTimerState;
        case "tick":
            // Returns the updated state.time value
            // --> This WON'T trigger the useEffect as the isRunning value won't change
            return {
                ...state,
                time: state.time + 1
            };
        default:
            // Throw an error for any unexpected value
            throw new Error();
    }
}

// Define the Timer main function
export default function Timer() {
    // First off, initialize a timer state and its reducer function
    // --> The reducer will be the previously defined function that will manage the behaviour of the Timer
    // --> We also provide the desired initialTimerState
    const [state, dispatch] = useReducer(reducer, initialTimerState);
    // Then, define a reference value (idRef) using the `useRef` hook to handle the time value update
    const idRef = useRef(0);

    // Define the useEffect hook that updates the lifecycle of the timer
    useEffect(() => {
        // if 'state.isRunning' changes to false, then we have to clear the interval function that updates the timer value
        if (!state.isRunning) {
            clearInterval(idRef.current);
            idRef.current = 0;
            return;
        };

        // Set the idRef current value to the initialization of a new interval
        // --> This interval is the responsible of calling the useReducer hook with the 'tick' action type
        idRef.current = setInterval(
            () => dispatch(
                    {
                        type: "tick"
                    }), 1000); // Set the interval value to 1 sec
        
    }, [state.isRunning]); // useEffect will only run once on component creation/mount and each time the value of 'state.isRunning' changes
}
    
